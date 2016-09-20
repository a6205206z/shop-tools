package com.xingmima.dpfx.inter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DItemNum;
import com.xingmima.dpfx.entity.DItems;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.tags.EmTag;
import com.xingmima.dpfx.tags.StrongTag;
import com.xingmima.dpfx.util.GuidUtils;
import com.xingmima.dpfx.util.RegexUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TaobaoParser, v 0.1
 * @date 2016 /9/3 14:54
 */
public class TaobaoParser extends DPFXJsonParserImpl {
    /**
     * 自定义参数
     */
    protected String param = null;
    /**
     * 轮次
     **/
    private Long runid = null;
    /**
     * 店铺ID
     */
    private Long shopid = null;

    /**
     * 商品详情URL
     */
    protected String producturl = "";

    /**
     * Gets param.
     *
     * @return the param
     */
    public String getParam() {
        return this.param;
    }

    /**
     * Gets runid.
     *
     * @return the runid
     */
    public Long getRunid() {
        return runid;
    }

    /**
     * Gets shopid.
     *
     * @return the shopid
     */
    public Long getShopid() {
        return shopid;
    }

    /**
     * Gets producturl.
     *
     * @return the producturl
     */
    public String getProducturl() {
        return producturl;
    }

    /**
     * Instantiates a new Taobao parser.
     *
     * @param resource the resource
     */
    public TaobaoParser(String resource) {
        super(resource);
    }

    /**
     * 初始化原始数据
     *
     * @return boolean boolean
     */
    public boolean initSpiderShop() {
        StringTokenizer st = new StringTokenizer(this.resource, "|");
        try {
            while (st.hasMoreTokens()) {
                this.param = st.nextToken();
                break;
            }
        } finally {
        }
        if (StringUtils.isEmpty(this.param)) {
            return false;
        }

        log.info(this.param);
        try {
            JSONObject obj = (JSONObject) JSON.parse(this.param);
            if (null != obj) {
                try {
                    this.runid = obj.getLong("runid");
                    this.shopid = obj.getLong("shopid");
                } catch (Exception e) {
                    log.error("{} {}", obj.toJSONString(), e);
                }
            }
        } catch (Exception e) {
            log.error("init spider shop:", e);
        }
        if (null == runid || null == shopid) {
            return false;
        }

        /*整理html数据*/
        int s = (this.param + "|").length();
        this.resource = this.resource.substring(s);
        return true;
    }

    /**
     * 添加url前缀
     *
     * @param url the url
     * @return string string
     */
    public String httpPrefix(String url) {
        if (url != null && !url.startsWith("http:")) {
            return "http:" + url;
        }
        return url;
    }

    //天猫-淘宝详情页公共方法区域=======================start============

    /**
     * Handel dsr
     *
     * @return the d dsr
     * @throws ParserException the parser exception
     */
    public DDsr handelDsr() throws ParserException {
        /*检查模块是否存在*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_ID, "dsr");
        if (list == null || list.size() <= 0) {
            log.info("===not found dsr!==={}", this.getShopid());
            return null;
        }

        DDsr dsr = this.getDefaultDsr();
        //店铺评分
//        NodeList self = list.extractAllNodesThatMatch(new HasAttributeFilter(FILTER_CLASS, "count"), true);
//        if (self != null || self.size() >= 3) {
//            if (log.isDebugEnabled()) {
//                log.debug("count:{}", self.toHtml());
//            }
//
//            dsr.setDetail(new BigDecimal(RegexUtils.getDecimal(((TagNode) self.elementAt(0)).getAttribute("title"))));
//            dsr.setSeller(new BigDecimal(RegexUtils.getDecimal(((TagNode) self.elementAt(1)).getAttribute("title"))));
//            dsr.setRating(new BigDecimal(RegexUtils.getDecimal(((TagNode) self.elementAt(2)).getAttribute("title"))));
//        } else {
//            log.info("===not found dsr count!==={}", this.getShopid());
//        }

        //行业值
        NodeList percent = list.extractAllNodesThatMatch(new NodeClassFilter(StrongTag.class), true);
        if (log.isDebugEnabled()) {
            log.debug("percent:{}", percent.toHtml());
        }
        if (percent != null && percent.size() == 3) {
            dsr.setdHy(new BigDecimal(RegexUtils.getDecimal(percent.elementAt(0).toPlainTextString())));
            dsr.setdCss(((TagNode) percent.elementAt(0)).getAttribute("class").replace("percent", "").trim());

            dsr.setsHy(new BigDecimal(RegexUtils.getDecimal(percent.elementAt(1).toPlainTextString())));
            dsr.setsCss(((TagNode) percent.elementAt(1)).getAttribute("class").replace("percent", "").trim());

            dsr.setrHy(new BigDecimal(RegexUtils.getDecimal(percent.elementAt(2).toPlainTextString())));
            dsr.setrCss(((TagNode) percent.elementAt(2)).getAttribute("class").replace("percent", "").trim());

        } else {
            log.info("===percent==={}", this.getShopid());
            return null;
        }

        //店铺评分明细
        NodeList wrap = list.extractAllNodesThatMatch(new HasAttributeFilter(FILTER_CLASS, "dsr-info-box"), true);
        if (wrap == null || wrap.size() != 3) {
            log.info("===dsr-info-box==={}", this.getShopid());
            return null;
        }

        JSONObject obj = this.getDsrPf(wrap.elementAt(0));
        if (obj == null) {
            log.info("===dsr-info-box==={}", this.getShopid());
            return null;
        }
        dsr.setDetail(obj.getBigDecimal("dsr"));
        dsr.setdJson(obj.toJSONString());

        obj = this.getDsrPf(wrap.elementAt(1));
        dsr.setSeller(obj.getBigDecimal("dsr"));
        dsr.setsJson(obj.toJSONString());

        obj = this.getDsrPf(wrap.elementAt(2));
        dsr.setRating(obj.getBigDecimal("dsr"));
        dsr.setrJson(obj.toJSONString());

        log.info(dsr.toString());
        return dsr;
    }

    /**
     * DSR明细评分
     *
     * @param node
     * @return
     */
    private JSONObject getDsrPf(Node node) {
        String text = node.toPlainTextString();
        JSONObject info = new JSONObject();
        info.put("cnt", RegexUtils.findText(text, "共.*?(?=人)").replace("共", "").trim());
        NodeList box = new NodeList();
        node.collectInto(box, new NodeClassFilter(EmTag.class));
        if (log.isDebugEnabled()) {
            log.debug("dsr-info-box-{}", box.toHtml());
        }
        //共6个子节点
        if (box != null && box.size() == 6) {
            BigDecimal dsr = new BigDecimal(RegexUtils.getDecimal(((EmTag) box.elementAt(0)).getAttribute("title")));
            JSONObject obj = new JSONObject();
            for (int i = 1, k = 5; i < box.size(); i++, k--) {
                obj.put("c_" + k, RegexUtils.getDecimal(box.elementAt(i).toPlainTextString()));
            }
            info.put("pf", obj);
            info.put("dsr", dsr);
        } else {
            return null;
        }
        if (log.isDebugEnabled()) {
            log.debug(info.toJSONString());
        }
        return info;
    }


    /**
     * Gets default shop.
     *
     * @return the default shop
     */
    protected DShop getDefaultShop() {
        DShop info = new DShop();
        info.setId(GuidUtils.getGuid32());
        info.setDate(this.getRunid());
        info.setShopid(this.getShopid());
        info.setUpdated(new Date());
        return info;
    }

    /**
     * Gets default dsr.
     *
     * @return the default dsr
     */
    protected DDsr getDefaultDsr() {
        DDsr dsr = new DDsr();
        dsr.setId(GuidUtils.getGuid32());
        dsr.setDate(this.getRunid());
        dsr.setShopid(this.getShopid());
        dsr.setCreated(new Date());
        return dsr;
    }


    /**
     * Gets default ditem.
     *
     * @param numiid the numiid
     * @return the default ditem
     */
    public DItems getDefaultDitem(Long numiid) {
        DItems item = new DItems();
        item.setId(GuidUtils.getGuid32());
        item.setDate(this.getRunid());
        item.setShopid(this.getShopid());
        item.setNumiid(numiid);
        item.setItemUrl(producturl);
        item.setUpdated(new Date());
        return item;
    }

    /**
     * Handle images.
     *
     * @param item the item
     * @param f    the f
     * @throws ParserException the parser exception
     */
    public void handleImages(DItems item, boolean f) throws ParserException {
        /*商品主图*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_ID, "J_ImgBooth");
        if (null != list && list.size() > 0) {
            ImageTag img = (ImageTag) list.elementAt(0);
            if (null != img && null != img.getAttribute("data-src")) {
                item.setPicUrl(this.httpPrefix(img.getAttribute("data-src").trim()));
            } else {
                item.setPicUrl(this.httpPrefix(img.getAttribute("src").trim()));
            }
            if (f) {
             /*商品标题*/
                item.setTitle(img.getAttribute("alt").trim());
            }
        } else {
            log.info("item images is null:{}", this.producturl);
        }

    }

    /**
     * Handle dbst.
     *
     * @param item  the item
     * @param regex the regex
     */
    public void handleDbst(DItems item, String regex) {
        String dbst = RegexUtils.findText(this.resource, regex);
        if (!StringUtils.isEmpty(dbst)) {
            Long dt = Long.parseLong(RegexUtils.getInteger(dbst.trim()));
            Long d = (System.currentTimeMillis() - dt) / 604800000L; //7天
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(dt));
            //最近上架时间
            c.add(Calendar.DAY_OF_YEAR, d.intValue() * 7);
            item.setListTime(c.getTime());
            //最近下架时间
            c.add(Calendar.DAY_OF_YEAR, 7);
            item.setDelistTime(c.getTime());
        } else {
            log.info("item dbst is null:{}", this.producturl);
        }
    }

    /**
     * 重置店铺ID
     *
     * @param item
     * @param regex
     */
    public void resetShopId(DItems item, String regex) {
        String shopid = RegexUtils.findText(this.resource, regex);
        if (!StringUtils.isEmpty(shopid)) {
            item.setShopid(Long.parseLong(RegexUtils.getInteger(shopid.trim())));
        } else {
            log.info("item shopid is null:{}", this.producturl);
        }
    }

    /**
     * 卖光了，或商品下架
     *
     * @param key the key
     * @return boolean boolean
     * @throws ParserException the parser exception
     */
    public boolean checkSaleout(String key) throws ParserException {
        NodeList dlist = this.extractAllNodesThatMatch(FILTER_CLASS, key);
        if (dlist != null && dlist.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Handel item num d item num.
     *
     * @param numiid the numiid
     * @return the d item num
     * @throws NullPointerException         the null pointer exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public DItemNum handelItemNum(Long numiid) throws NullPointerException, UnsupportedEncodingException {
        String counterapi = "";
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        try {
            //商品访问详情部分
            if (null != obj.getString("counter")) {
                counterapi = (URLDecoder.decode(obj.getString("counter"), UTF8));
            }
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        if (StringUtils.isEmpty(counterapi)) {
            log.error("===counter api is null.====");
            return null;
        } else {
            if (log.isDebugEnabled()) {
                log.debug(counterapi);
            }
        }

        DItemNum item = new DItemNum();
        item.setId(GuidUtils.getGuid32());
        item.setDate(this.getRunid());
        item.setShopid(this.getShopid());
        item.setNumiid(numiid);
        item.setUpdated(new Date());

        String c[] = counterapi.split(",");
        if (null != c && c.length > 0) {
            for (int i = 0; i < c.length; i++) {
                if (c[i].indexOf("DFX_") >= 0) { //商品分享数
                    item.setiShareNum(formatInteger(c[i]));
                } else if (c[i].indexOf("ICVT_") >= 0) {//商品浏览量
                    item.setiPv(formatInteger(c[i]));
                } else if (c[i].indexOf("SCCP_") >= 0) {//店铺收藏数
                    item.setsFavorite(formatInteger(c[i]));
                } else if (c[i].indexOf("ICCP_") >= 0) {//商品收藏数
                    item.setiFavoriteNum(formatInteger(c[i]));
                }
            }
        }
        log.info(item.toString());
        return item;
    }

    private Integer formatInteger(String tmp) throws NullPointerException {
        String[] c = tmp.split(":");
        if (c != null && c.length == 2) {
            return Integer.parseInt(RegexUtils.getInteger(c[1]));
        }
        throw new NullPointerException();
    }

    //天猫-淘宝详情页公共方法区域=======================end==============

    /**
     * Read file file string.
     *
     * @param filePath    the file path
     * @param charsetName the charset name
     * @return the string
     */
    public static String readHtmlFile(String filePath, String charsetName) {
        StringBuffer str = new StringBuffer();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), charsetName);//考虑到编码格式
                BufferedReader br = new BufferedReader(read);
                String line = null;
                while ((line = br.readLine()) != null) {
                    str.append(line);
                }
                read.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
