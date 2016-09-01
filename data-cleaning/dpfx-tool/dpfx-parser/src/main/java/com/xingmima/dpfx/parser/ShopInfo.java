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
    public DShop handleShopInfo() {
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
            log.error(e.getMessage());
        }

        /*卖家信用*/
        try {
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "sep");
        } catch (ParserException e) {
            log.error(e.getMessage());
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
        /*评价数量*/
        Integer a = 0, b = 0, c = 0, e = 0, f = 0, g = 0;
        try {
            //好评
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "rateok");
            if (null != list && list.size() > 0) {
                a = Integer.parseInt(list.elementAt(2).toPlainTextString().trim());
                e = Integer.parseInt(list.elementAt(3).toPlainTextString().trim());
            }
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "ratenormal");
            if (null != list && list.size() > 0) {
                b = Integer.parseInt(list.elementAt(2).toPlainTextString().trim());
                f = Integer.parseInt(list.elementAt(3).toPlainTextString().trim());
            }
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "ratebad");
            if (null != list && list.size() > 0) {
                c = Integer.parseInt(list.elementAt(2).toPlainTextString().trim());
                g = Integer.parseInt(list.elementAt(3).toPlainTextString().trim());
            }
        } catch (ParserException e1) {
            log.error(e1.getMessage());
        }
        info.setCreditTotalNum(a + b + c + e + f + g);
        info.setCreditGoodNum(a + e);

        /*好评率*/
        try {
            list = this.extractAllNodesThatMatch(FILTER_CLASS, "tb-rate-ico-bg ico-seller");
            if (null != list && list.size() > 0) {
                info.setRating(new BigDecimal(RegexUtils.getNumbers(list.elementAt(0).toPlainTextString().trim())));
            }
        } catch (ParserException e2) {
            log.error(e2.getMessage());
        }
        list = null;
        this.reset();

        log.info(info.toString());
        return info;
    }

    public static void main(String[] args) {
//        try {
//            String res = HttpUtil.get4("https://rate.taobao.com/user-rate-f07ef4944ee3876030f6f5b4186767b6.htm?spm=2013.1.1000126.2.pUZ5CK", "GBK");
//            res = "11111111111|20027371|西红柿|bbb|" + res;
        String res = readHtmlFile("d://file//shopinfo.html", "GBK");
        DShop obj = new ShopInfo(res).call().handleShopInfo();
        try {
        new ShopDao().insert(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
