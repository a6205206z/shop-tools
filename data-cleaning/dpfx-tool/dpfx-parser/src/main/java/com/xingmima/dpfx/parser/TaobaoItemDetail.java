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
import com.xingmima.dpfx.util.RegexUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;

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
     * Handel item info.
     *
     * @return the d items
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws ParserException              the parser exception
     */
    public DItems handelItemInfo(boolean isTejia) throws UnsupportedEncodingException, ParserException {
        Long numiid = null;
        String detailCount = "", sib = "";
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        try {
            if (null != obj.getString("producturl")) {
                this.producturl = (URLDecoder.decode(obj.getString("producturl"), UTF8));
                numiid = RegexUtils.getTaobaoId(this.producturl);
            }

            if (null == numiid) {
                log.error("===not found item id==={}", this.producturl);
                return null;
            }

            //累计评论量
            if (null != obj.getString("comment_count")) {
                detailCount = (URLDecoder.decode(obj.getString("comment_count"), UTF8));
            } else {
                log.info("===not found item detail count==={}", this.producturl);
                return null;
            }

            //Json串
            if (null != obj.getString("sib")) {
                sib = (URLDecoder.decode(obj.getString("sib"), UTF8)).replace("onSibRequestSuccess(", "").replace(");", "");
            } else {
                log.info("===not found item sib-api==={}", this.producturl);
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        DItems item = this.getDefaultDitem(numiid);

        //特价商品发布类目
        if (isTejia && null != obj.getString("catalog")) {
            item.setPublishCategory(obj.getString("catalog"));
        }

        /*商品标题*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_CLASS,
                "tb-main-title");
        if (null != list && list.size() > 0) {
            item.setTitle(((HeadingTag) list.elementAt(0)).getAttribute("data-title"));
        } else {
            log.info("item title is null:{}", this.producturl);
        }

        /*商品主图*/
        this.handleImages(item, false);

        //上下架时间
        this.handleDbst(item, "dbst.*?(?=,)");

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
            JSONObject data = null;
            try {
                JSONObject j = JSON.parseObject(sib);
                data = (JSONObject) j.get("data");
                if (data == null) {
                    log.error("===item sib->data is null==={}", this.producturl);
                    return null;
                }
                if (log.isDebugEnabled()) {
                    log.debug("data:==={}", j.get("data"));
                }
            } catch (Exception e) {
                throw e;
            }
            this.handelPrices(item, data, isTejia);
        } else {
            log.error("===sib->data is null==={}", this.producturl);
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

        //品牌名称
        item.setBrandName(this.transfHtml());

        log.info(item.toString());
        return item;
    }

    /**
     * @param item
     * @param data
     * @param isTejia
     */

    private void handelPrices(DItems item, JSONObject data, boolean isTejia) {
        //HashMap m = JSON.parseObject(sib, LinkedHashMap.class, Feature.OrderedField);
        if (null != data.getString("price")) {
            log.debug("price={}", data.getString("price"));
            item.setMarkerPrice(data.getString("price").replaceAll("\\r\\s\\n", ""));
        } else {
            item.setMarkerPrice(BigDecimal.ZERO.toString());
        }

        //库存
        JSONObject dynStock = (JSONObject) data.get("dynStock");
        if (null != dynStock) {
            if (log.isDebugEnabled()) {
                log.debug(dynStock.toJSONString());
                log.debug("stock={}", dynStock.getInteger("stock").toString());
                log.debug("sellableQuantity={}", dynStock.getInteger("sellableQuantity").toString());
            }
            item.setStock(dynStock.getInteger("stock"));
            item.setSellableQuantity(dynStock.getInteger("sellableQuantity"));
            //库存明细
            if (null != dynStock.get("sku")) {
                item.setSkuStock(dynStock.get("sku").toString());
            }
        }

        //销售量
        JSONObject soldQuantity = data.getJSONObject("soldQuantity");
        if (null != soldQuantity) {
            if (log.isDebugEnabled()) {
                log.debug("soldTotalCount={}", soldQuantity.getInteger("soldTotalCount").toString());
                log.debug("confirmGoodsCount={}", soldQuantity.getInteger("confirmGoodsCount").toString());
            }

            item.setSoldTotalCount(soldQuantity.getInteger("soldTotalCount"));
            item.setConfirmGoodsCount(soldQuantity.getInteger("confirmGoodsCount"));
        }

        //特价优惠信息
        if (isTejia) {
            BigDecimal promoPrice = null;
            JSONObject promotion = (JSONObject) data.getJSONObject("promotion");
            if (null != promotion) {
                JSONObject promoData = promotion.getJSONObject("promoData");
                if (null != promoData) {
                    JSONArray def = promoData.getJSONArray("def");
                    if (null != def && def.size() > 0) {
                        if (log.isDebugEnabled()) {
                            log.debug(def.toJSONString());
                        }
                        for (Object obj : def) {
                            JSONObject defObj = (JSONObject) obj;
                            if (null != defObj && null != defObj.get("type")) {
                                if ("天天特价".equals(defObj.get("type")) && null != defObj.get("price")) {
                                    promoPrice = new BigDecimal(defObj.get("price").toString());
                                    item.setPromoPrice(promoPrice);
                                    break;
                                }
                            }
                        }
                    }

                }
            }

            //重置真实店铺ID与类别
            this.resetShopId(item, "shopId=.*?(?=;)");
        }

        BigDecimal price = null;
//        if (price == null) {
        //区间价
        if (item.getMarkerPrice().indexOf("-") > 0) {
            price = new BigDecimal(item.getMarkerPrice().split("-")[0].trim());
        } else {
            price = new BigDecimal(item.getMarkerPrice());
        }
//        }

        //销售金额
        item.setPrice(price);
        item.setTotalSales(item.getPrice().multiply(new BigDecimal(item.getSoldTotalCount())));

        //邮费
        item.setPostFee(this.getPostFee(data));
    }

    /**
     * Transf file string.
     *
     * @return the string
     * @throws ParserException the parser exception
     */
    public String transfHtml() throws ParserException {
        String brandName = "";

        NodeList params = this.extractAllNodesThatMatch(FILTER_CLASS,
                "attributes-list");
        if (params != null && params.size() > 0) {
            params = params.elementAt(0).getChildren();
        } else {
            log.info("not found attributes-list,{}", this.producturl);
            return brandName;
        }

        for (int i = 0; i < params.size(); i++) {
            if (params.elementAt(i) instanceof Tag) {

                Bullet li = null;
                if (params.elementAt(i) instanceof Bullet) {
                    li = (Bullet) params.elementAt(i);
                }
                if (null == li)
                    continue;

                String[] value = RegexUtils.valueParam(li.toPlainTextString());

                String key = value[0];
                String val = RegexUtils.charDecoder(li.getAttribute("title"));

                if (key.equals("品牌")) {
                    brandName = val;
                    break;
                }
            }
        }
        return brandName;
    }

    /**
     * 获取邮费
     *
     * @param data
     * @return
     */
    private BigDecimal getPostFee(JSONObject data) {
        BigDecimal postfee = BigDecimal.ZERO;
        JSONObject deliveryFee = data.getJSONObject("deliveryFee");
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
            String res = readHtmlFile(TaobaoItemDetail.class.getClassLoader().getResource("").getPath() + "/file/detail.html", "GBK");
            TaobaoItemDetail dox = new TaobaoItemDetail(res).call();

            DItems obj = dox.handelItemInfo(true);
            if (null != obj) {
//                new DItemsDao().insert(obj);

//                DItemNum diobj = dox.handelItemNum(obj.getNumiid());
//                new DItemsNumDao().insert(diobj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
