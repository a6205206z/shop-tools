package com.xingmima.dpfx.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.dao.DdsrDao;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DRated;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.inter.TaobaoParser;
import com.xingmima.dpfx.util.Constant;
import com.xingmima.dpfx.util.GuidUtils;
import com.xingmima.dpfx.util.RegexUtils;
import org.apache.commons.lang.ArrayUtils;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TaobaoShopInfo, v 0.1
 * @date 2016 /8/30 13:58
 */
public class TaobaoShopInfo extends TaobaoParser {
    /**
     * Instantiates a new Taobao shop info.
     *
     * @param resource the resource
     */
    public TaobaoShopInfo(String resource) {
        super(resource);
    }

    /**
     * Call taobao shop info.
     *
     * @return the taobao shop info
     */
    public TaobaoShopInfo call() {
        boolean isOk = this.initSpiderShop();
        if (!isOk) {
            log.error("===format error===", Constant.HTML_FORMAT_ERROR);
            return null;
        }
        return this;
    }

    /**
     * 店铺信息处理
     *
     * @return the d shop
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws ParserException              the parser exception
     */
    public DShop handleShopInfo() throws UnsupportedEncodingException, ParserException {
        DShop info = this.getDefaultShop();
        NodeList list = null;

        /*店名*/
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        try {
            info.setTitle(URLDecoder.decode(obj.getString("shopname"), UTF8));
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
                    if (log.isDebugEnabled()) {
                        log.debug(sell.toHtml());
                    }
                    info.setCreditScore(Long.parseLong(RegexUtils.getInteger(sell.elementAt(j).toPlainTextString())));

                    /*信用图标*/
                    NodeList tmp = new NodeList();
                    sell.elementAt(j).collectInto(tmp, new NodeClassFilter(ImageTag.class));
                    if (null != tmp && tmp.size() > 0) {
                        info.setCreditLevel(this.httpPrefix(((ImageTag) tmp.elementAt(0)).getAttribute("src")));
                    }
                }
            }
        } else {
            log.info("not found seller '{}' credit info!", this.getShopid());
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

        try {
            info.setRating(this.getRating());
        } catch (ParserException e2) {
            throw e2;
        }
        list = null;

        log.info(info.toString());
        return info;
    }

    /**
     * 好评率
     *
     * @return rating rating
     * @throws ParserException the parser exception
     */
    public BigDecimal getRating() throws ParserException {
        /*好评率*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS, "tb-rate-ico-bg ico-seller");
        if (null != list && list.size() > 0) {
            return new BigDecimal(RegexUtils.getDecimal(list.elementAt(0).toPlainTextString().trim()));
        } else {
            log.info("not found seller '{}' rating info!", this.getShopid());
        }
        return BigDecimal.ZERO;
    }

    /**
     * 获得中差评数量
     *
     * @param val the val
     * @return integer [ ]
     * @throws ParserException the parser exception
     */
    public Integer[] getRating(String val) throws ParserException {
        Integer[] back = new Integer[]{0, 0};
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS, val);
        if (null != list && list.size() > 0) {
            back[0] = Integer.parseInt(list.elementAt(2).toPlainTextString().trim());
            back[1] = Integer.parseInt(list.elementAt(3).toPlainTextString().trim());
        }
        if (log.isDebugEnabled()) {
            log.debug("rating:{}", ArrayUtils.toString(back));
        }
        list = null;
        return back;
    }

    /**
     * Handle rating
     *
     * @return the d rated
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws ParserException              the parser exception
     */
    public DRated handleRating() throws UnsupportedEncodingException, ParserException {
        /*检查模块是否存在*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS, "rate-box box-his-rate");
        if (list == null || list.size() <= 0) {
            log.info("===not found box-his-rate!==={}", this.getShopid());
            return null;
        }

        DRated rate = new DRated();
        rate.setId(GuidUtils.getGuid32());
        rate.setDate(this.getRunid());
        rate.setShopid(this.getShopid());
        rate.setUpdated(new Date());

        try {
            //好评
            Integer[] rateok = this.getRating2("rateok");
            //中评
            Integer[] ratenormal = this.getRating2("ratenormal");
            //差评
            Integer[] ratebad = this.getRating2("ratebad");

            rate.setWeekGood(rateok[0]);
            rate.setMonthGood(rateok[1]);
            rate.setHalfyearGood(rateok[2]);
            rate.setAgoGood(rateok[3]);

            rate.setWeekNeutral(ratenormal[0]);
            rate.setMonthNeutral(ratenormal[1]);
            rate.setHalfyearNeutral(ratenormal[2]);
            rate.setAgoNeutral(ratenormal[3]);

            rate.setWeekBad(ratebad[0]);
            rate.setMonthBad(ratebad[1]);
            rate.setHalfyearBad(ratebad[2]);
            rate.setAgoBad(ratebad[3]);
        } catch (ParserException e) {
            throw e;
        }

        /*好评率*/
        try {
            rate.setRating(this.getRating());
        } catch (ParserException e) {
            throw e;
        }

        list = null;
        log.info(rate.toString());

        return rate;
    }

    /**
     * 评价
     *
     * @param val
     * @return
     * @throws ParserException
     */
    private Integer[] getRating2(String val) throws ParserException {
        Integer[] back = new Integer[]{0, 0, 0, 0};
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS, val);
        if (null != list && list.size() > 0) {
            back[0] = Integer.parseInt(list.elementAt(0).toPlainTextString().trim());
            back[1] = Integer.parseInt(list.elementAt(1).toPlainTextString().trim());
            back[2] = Integer.parseInt(list.elementAt(2).toPlainTextString().trim());
            back[3] = Integer.parseInt(list.elementAt(3).toPlainTextString().trim());
        }
        if (log.isDebugEnabled()) {
            log.debug("rating:{}", ArrayUtils.toString(back));
        }
        list = null;
        return back;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
//            String res = HttpUtil.get4("https://rate.taobao.com/user-rate-f07ef4944ee3876030f6f5b4186767b6.htm?spm=2013.1.1000126.2.pUZ5CK", "GBK");
//            res = "11111111111|20027371|西红柿|bbb|" + res;
            String res = readHtmlFile(TaobaoShopInfo.class.getClassLoader().getResource("").getPath() + "/file/shopinfo.html", "GBK");

            TaobaoShopInfo dox = new TaobaoShopInfo(res).call();

//            DShop obj = dox.handleShopInfo();
//            new ShopDao().insert(obj);

//            DRated obj2 = dox.handleRating();
//            new RateDao().insert(obj2);

            DDsr obj3 = dox.handelDsr();
            new DdsrDao().insert(obj3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
