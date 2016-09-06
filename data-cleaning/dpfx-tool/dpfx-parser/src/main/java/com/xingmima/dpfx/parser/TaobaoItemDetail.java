package com.xingmima.dpfx.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.dao.DItemsDao;
import com.xingmima.dpfx.dao.DItemsNumDao;
import com.xingmima.dpfx.entity.DItemNum;
import com.xingmima.dpfx.entity.DItems;
import com.xingmima.dpfx.inter.TaobaoParser;
import com.xingmima.dpfx.util.Constant;
import com.xingmima.dpfx.util.GuidUtils;
import com.xingmima.dpfx.util.RegexUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TaobaoItemDetail, v 0.1
 * @date 2016 /9/5 13:58
 */
public class TaobaoItemDetail extends TaobaoParser {
    /**
     * Instantiates a new Taobao item detail.
     *
     * @param resource the resource
     */
    public TaobaoItemDetail(String resource) {
        super(resource);
        if (!StringUtils.isEmpty(resource) && resource.length() > 200) {
            log.info(resource.substring(0, 100));
        } else {
            log.info(resource);
        }
    }

    /**
     * Call taobao item detail.
     *
     * @return the taobao item detail
     */
    public TaobaoItemDetail call() {
        boolean isOk = this.initSpiderShop();
        if (!isOk) {
            log.error("===format error===", Constant.HTML_FORMAT_ERROR);
            return null;
        }
        return this;
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
        }

        DItemNum item = new DItemNum();
        item.setId(GuidUtils.getGuid32());
        item.setDate(this.getRunid());
        item.setNumiid(numiid);
        item.setUpdated(new Date());
        log.info(counterapi);

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
        if (tmp != null && tmp.length() > 1) {
            return Integer.parseInt(RegexUtils.getInteger(c[1]));
        }
        throw new NullPointerException();
    }

    /**
     * Handel item info.
     *
     * @return the d items
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws ParserException              the parser exception
     */
    public DItems handelItemInfo() throws UnsupportedEncodingException, ParserException {
        Long numiid = null;
        String producturl = "", detailCount = "", sib = "";
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        try {
            if (null != obj.getString("producturl")) {
                producturl = (URLDecoder.decode(obj.getString("producturl"), UTF8));
                numiid = RegexUtils.getTaobaoId(producturl);
            }
            if (null == numiid) {
                log.error("===not found item id==={}", producturl);
                return null;
            }
            //累计评论量
            if (null != obj.getString("comment_count")) {
                detailCount = (URLDecoder.decode(obj.getString("comment_count"), UTF8));
            }
            //Json串
            if (null != obj.getString("sib")) {
                sib = (URLDecoder.decode(obj.getString("sib"), UTF8)).replace("onSibRequestSuccess(", "").replace(");", "");
            }
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        DItems item = new DItems();
        item.setId(GuidUtils.getGuid32());
        item.setDate(this.getRunid());
        item.setNumiid(numiid);
        item.setItemUrl(producturl);
        item.setUpdated(new Date());

        /*商品标题*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS,
                "tb-main-title");
        if (null != list && list.size() > 0) {
            item.setTitle(((HeadingTag) list.elementAt(0)).getAttribute("data-title"));
        }

        /*商品主图*/
        list = this.extractAllNodesThatMatch(FILTER_ID, "J_ImgBooth");
        if (null != list && list.size() > 0) {
            ImageTag img = (ImageTag) list.elementAt(0);
            if (null != img && null != img.getAttribute("data-src")) {
                item.setPicUrl(this.httpPrefix(img.getAttribute("data-src").trim()));
            } else {
                item.setPicUrl(this.httpPrefix(img.getAttribute("src").trim()));
            }
        }

        //上下架时间
        String dbst = RegexUtils.findText(this.getResource(), "dbst.*?(?=,)");
        if (!StringUtils.isEmpty(dbst)) {
            Date listtime = new Date(Long.parseLong(RegexUtils.getInteger(dbst.trim())));
            Calendar c = Calendar.getInstance();
            c.setTime(listtime);
            c.add(Calendar.DAY_OF_YEAR, 7);
            item.setListTime(listtime);
            item.setDelistTime(c.getTime());
        }

        //类目
        String rcid = RegexUtils.findText(this.getResource(), "[\\r\\s\\n]rcid.*?(?=,)");
        if (!StringUtils.isEmpty(rcid)) {
            item.setRcid(Long.parseLong(RegexUtils.getInteger(rcid.trim())));
        }
        String cid = RegexUtils.findText(this.getResource(), "[\\r\\s\\n]cid.*?(?=,)");
        if (!StringUtils.isEmpty(cid)) {
            item.setCid(Long.parseLong(RegexUtils.getInteger(cid.trim())));
        }

        //是否卖光或手动下架
        item.setIsDelisting(this.checkSaleout());

        if (!StringUtils.isEmpty(sib)) {
            if (log.isDebugEnabled()) {
                log.debug(sib);
            }

            //HashMap m = JSON.parseObject(sib, LinkedHashMap.class, Feature.OrderedField);

            JSONObject data = null;
            try {
                JSONObject j = JSON.parseObject(sib);
                if (log.isDebugEnabled()) {
                    log.debug("data:===" + j.get("data"));
                }
                data = (JSONObject) j.get("data");
                if (data == null) {
                    log.error("===item sib data is null===");
                    return null;
                }
            } catch (Exception e) {
                throw e;
            }

            if (null != data.getString("price")) {
                log.debug("price={}", data.getString("price"));
                item.setMarkerPrice(data.getString("price").replaceAll("\\r\\s\\n", ""));
            } else {
                item.setMarkerPrice(BigDecimal.ZERO.toString());
            }

            //库存
            JSONObject dynStock = (JSONObject) data.get("dynStock");
            if (null != dynStock) {
                log.info(dynStock.toJSONString());
                if (log.isDebugEnabled()) {
                    log.debug("stock={}", dynStock.getInteger("stock").toString());
                    log.debug("sellableQuantity={}", dynStock.getInteger("sellableQuantity").toString());
                    log.debug("sku={}", dynStock.get("sku").toString());
                }
                item.setStock(dynStock.getInteger("stock"));
                item.setSellablequantity(dynStock.getInteger("sellableQuantity"));
                item.setSkuStock(dynStock.get("sku").toString());
            }

            //销售量
            JSONObject soldQuantity = (JSONObject) data.getJSONObject("soldQuantity");
            if (null != soldQuantity) {
                if (log.isDebugEnabled()) {
                    log.debug("soldTotalCount={}", soldQuantity.getInteger("soldTotalCount").toString());
                    log.debug("confirmGoodsCount={}", soldQuantity.getInteger("confirmGoodsCount").toString());
                }

                item.setSoldTotalCount(soldQuantity.getInteger("soldTotalCount"));
                item.setConfirmGoodsCount(soldQuantity.getInteger("confirmGoodsCount"));
            }

            //获取优惠信息及价格
            BigDecimal price = null;
            JSONObject promotion = (JSONObject) data.getJSONObject("promotion");
            if (null != promotion) {
                JSONObject promoData = promotion.getJSONObject("promoData");
                if (null != promoData) {
                    JSONArray def = promoData.getJSONArray("def");
                    if (null != def && def.size() > 0) {
                        JSONObject defObj = (JSONObject) def.get(0);
                        if (null != defObj && null != defObj.get("price")) {
                            price = new BigDecimal(defObj.get("price").toString());
                        }
                        if (null != defObj && null != defObj.get("type")) {
                            item.setPromoInfo(defObj.get("type").toString());
                        }
                    }
                }
            }
            if (price == null) {
                //区间价
                if (item.getMarkerPrice().indexOf("-") > 0) {
                    price = new BigDecimal(item.getMarkerPrice().split("-")[0].trim());
                } else {
                    price = new BigDecimal(item.getMarkerPrice());
                }
            }
            //销售金额
            item.setPrice(price);
            item.setTotalSales(item.getPrice().multiply(new BigDecimal(item.getSoldTotalCount())));

            //邮费
            item.setPostFee(this.getPostFee(data));
        } else {
            log.error("===sib data is null===", numiid);
            return null;  //数据不全，返回
        }

        //处理商品统计数据
        if (!StringUtils.isEmpty(detailCount)) {
            String dl = RegexUtils.findText(detailCount, ":.*(?=})");
            if (!StringUtils.isEmpty(dl)) {
                item.setTotalRatedCount(Integer.parseInt(RegexUtils.getInteger(dl)));
            } else {
                item.setTotalRatedCount(0);
            }
        }
        log.info(item.toString());
        return item;
    }

    /**
     * 获取邮费
     *
     * @param data
     * @return
     */
    private BigDecimal getPostFee(JSONObject data) {
        BigDecimal postfee = BigDecimal.ZERO;
        JSONObject deliveryFee = (JSONObject) data.getJSONObject("deliveryFee");
        if (null != deliveryFee) {
            JSONObject dfdata = deliveryFee.getJSONObject("data");
            if (null != dfdata) {
                JSONObject serviceInfo = dfdata.getJSONObject("serviceInfo");
                if (null != serviceInfo) {
                    JSONArray list = serviceInfo.getJSONArray("list");
                    if (null != list && list.size() > 0) {
                        JSONObject defObj = (JSONObject) list.get(0);
                        if (null != defObj && !StringUtils.isEmpty(defObj.get("info").toString())) {
                            String str = RegexUtils.getDecimal(defObj.get("info").toString());
                            if (!StringUtils.isEmpty(str)) {
                                postfee = new BigDecimal(str);
                            }
                        }
                    }
                }
            }
        }
        return postfee;
    }

    /**
     * 卖光了，或商品下架
     *
     * @return boolean boolean
     * @throws ParserException the parser exception
     */
    public boolean checkSaleout() throws ParserException {
        NodeList dlist = this.extractAllNodesThatMatch(FILTER_CLASS, "tb-hint");
        if (dlist != null && dlist.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            String res = readHtmlFile("d://file//detail.html", "GBK");
            TaobaoItemDetail dox = new TaobaoItemDetail(res).call();

            DItems obj = dox.handelItemInfo();
            if (null != obj) {
                new DItemsDao().insert(obj);

                DItemNum diobj = dox.handelItemNum(obj.getNumiid());
                new DItemsNumDao().insert(diobj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
