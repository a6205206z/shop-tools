package com.xingmima.dpfx.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.inter.DPFXParserImpl;
import com.xingmima.dpfx.util.Constant;
import com.xingmima.dpfx.util.GuidUtils;
import com.xingmima.dpfx.util.RegexUtils;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopInfo, v 0.1
 * @date 2016/8/30 13:58
 */
public class ShopInfo extends DPFXParserImpl {

    private final static Logger log = LoggerFactory.getLogger(ShopInfo.class);

    public ShopInfo(String resource) {
        super(resource);
    }

    public ShopInfo call() {
        boolean isOk = this.initSpiderShop();
        if (!isOk) {
            log.error("format error==", Constant.HTML_FORMAT_ERROR);
            return null;
        }
        return this;
    }

    /**
     * 店铺信息处理
     */
    public DShop handleShopInfo() throws UnsupportedEncodingException, ParserException {
        DShop info = new DShop();
        info.setId(GuidUtils.getGuid32());
        info.setDate(this.getRunid());
        info.setShopid(this.getShopid());
        info.setUpdated(new Date());

        NodeList list = null;

        /*店名*/
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        try {
            info.setTitle(URLDecoder.decode(obj.getString("shopname"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        /*卖家信用*/
        try {
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "sep");
        } catch (ParserException e) {
            throw e;
        }
        if (null != list && list.size() > 0) {
            NodeList sell = list.elementAt(0).getChildren();
            for (int j = 0; j < sell.size(); j++) {
                if (sell.elementAt(j).toPlainTextString().indexOf("卖家") >= 0) {
                    info.setCreditScore(Long.parseLong(RegexUtils.getNumbers(sell.elementAt(j).toPlainTextString())));

                    /*信用图标*/
                    NodeList tmp = new NodeList();
                    sell.elementAt(j).collectInto(tmp, new NodeClassFilter(ImageTag.class));
                    if (null != tmp && tmp.size() > 0) {
                        info.setCreditLevel(this.httpPrefix(((ImageTag) tmp.elementAt(0)).getAttribute("src")));
                    }
                }
                if (log.isDebugEnabled()) {
                    log.debug(sell.elementAt(j).toHtml());
                }
            }
        } else {
            log.info("not found seller {} credit info!", this.getShopid());
        }

        try {
            //好评
            Integer[] rateok = this.getRating("rateok");
            //中评
            Integer[] ratenormal = this.getRating("ratenormal");
            //差评
            Integer[] ratebad = this.getRating("ratebad");
            info.setCreditTotalNum(rateok[0] + ratenormal[0] + ratebad[0] + rateok[1] + ratenormal[1] + ratebad[1]);
            info.setCreditGoodNum(rateok[0] + rateok[1]);
        } catch (ParserException e1) {
            throw e1;
        }

        /*好评率*/
        try {
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "tb-rate-ico-bg ico-seller");
            if (null != list && list.size() > 0) {
                info.setRating(new BigDecimal(RegexUtils.getNumbers(list.elementAt(0).toPlainTextString().trim())));
            }
        } catch (ParserException e2) {
            throw e2;
        }
        list = null;
        this.reset();

        log.info(info.toString());
        return info;
    }

    /**
     * 获得中差评数量
     *
     * @param val
     * @return
     * @throws ParserException
     */
    private Integer[] getRating(String val) throws ParserException {
        Integer[] back = new Integer[]{0, 0};
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS, val);
        if (null != list && list.size() > 0) {
            back[0] = Integer.parseInt(list.elementAt(2).toPlainTextString().trim());
            back[1] = Integer.parseInt(list.elementAt(3).toPlainTextString().trim());
        }
        list = null;
        return back;
    }

    public static void main(String[] args) {
//        try {
//            String res = HttpUtil.get4("https://rate.taobao.com/user-rate-f07ef4944ee3876030f6f5b4186767b6.htm?spm=2013.1.1000126.2.pUZ5CK", "GBK");
//            res = "11111111111|20027371|西红柿|bbb|" + res;
        String res = readHtmlFile("d://file//shopinfo.html", "GBK");
        try {
            DShop obj = new ShopInfo(res).call().handleShopInfo();
            new ShopDao().insert(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
