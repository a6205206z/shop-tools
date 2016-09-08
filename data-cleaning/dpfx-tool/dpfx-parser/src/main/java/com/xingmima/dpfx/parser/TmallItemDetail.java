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
import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
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
 * @version TmallItemDetail, v 0.1
 * @date 2016 /9/5 13:58
 */
public class TmallItemDetail extends TaobaoParser {
    /**
     * Instantiates a new Taobao item detail.
     *
     * @param resource the resource
     */
    public TmallItemDetail(String resource) {
        super(resource);
    }

    /**
     * Call taobao item detail.
     *
     * @return the taobao item detail
     */
    public TmallItemDetail call() {
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
    public DItems handelItemInfo() throws UnsupportedEncodingException, ParserException {
        Long numiid = null;
        String detailCount = "", sib = "";
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
            } else {
                log.info("===not found item detail count==={}", producturl);
                return null;
            }

            //Json串
            if (null != obj.getString("sib")) {
                sib = (URLDecoder.decode(obj.getString("sib"), UTF8)).replaceFirst("setMdskip", "").replaceFirst("\\(", "").replaceFirst("\\}\\)", "}");
            } else {
                log.info("===not found item sib-api==={}", producturl);
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        DItems item = this.getDefaultDitem(numiid);

        //获取图片跟标题
        this.handleImages(item, true);

        //上下架时间
        this.handleDbst(item, "date=.*?(?=\")");

        //类目
        String rcid = RegexUtils.findText(this.getResource(), "\"rootCatId\".*?(?=\",)");
        if (!StringUtils.isEmpty(rcid)) {
            item.setRcid(Long.parseLong(RegexUtils.getInteger(rcid.trim())));
        }
        String cid = RegexUtils.findText(this.getResource(), "\"categoryId\".*?(?=\",)");
        if (!StringUtils.isEmpty(cid)) {
            item.setCid(Long.parseLong(RegexUtils.getInteger(cid.trim())));
        }

        //是否卖光或手动下架
        item.setIsDelisting(this.checkSaleout("sold-out-tit"));

        //天猫基础售价
        String markerPrice = RegexUtils.findText(this.resource, "defaultItemPrice.*?(?=\",)");
        if (StringUtils.isEmpty(markerPrice)) {
            log.error("===item default item price is null==={}", producturl);
            return null;
        }
        item.setMarkerPrice(markerPrice.replaceAll("[\\r|\\s|\\n|defaultItemPrice\":\"]", ""));

        if (!StringUtils.isEmpty(sib)) {
            if (log.isDebugEnabled()) {
                log.debug(sib);
            }
            JSONObject data = null;
            try {
                JSONObject j = JSON.parseObject(sib);
                data = (JSONObject) j.get("defaultModel");
                if (data == null) {
                    log.error("===item sib->data is null==={}", producturl);
                    return null;
                }
                if (log.isDebugEnabled()) {
                    log.debug("data:==={}", j.get("defaultModel"));
                }
            } catch (Exception e) {
                throw e;
            }
            this.handelPrices(item, data);
        } else {
            log.error("===sib->data is null==={}", producturl);
            return null;  //数据不全，返回
        }

        //处理商品统计数据
        if (!StringUtils.isEmpty(detailCount)) {
            if (log.isDebugEnabled()) {
                log.debug("detailCount:" + detailCount);
            }
            String dl = RegexUtils.findText(detailCount, "rateTotal\":.*?(?=,)");
            if (!StringUtils.isEmpty(dl)) {
                item.setTotalRatedCount(Integer.parseInt(RegexUtils.getInteger(dl)));
            } else {
                item.setTotalRatedCount(0);
            }
            //商品动态评分
            dl = RegexUtils.findText(detailCount, "gradeAvg\":.*?(?=,)").replaceFirst("gradeAvg\":", "");
            log.error(dl);
            if (!StringUtils.isEmpty(dl)) {
                item.setRated(new BigDecimal(dl));
            } else {
                item.setRated(BigDecimal.ZERO);
            }
        }

        //品牌名称
        String brand = RegexUtils.findText(this.resource, "\"brand\":\".*?(?=\",)");
        if (!StringUtils.isEmpty(brand)) {
            item.setBrandName(RegexUtils.charDecoder(brand.replace("\"brand\":\"", "")));
        } else {
            log.error("===item sib->brand is null==={}", producturl);
            item.setBrandName("");
        }

        log.info(item.toString());
        return item;
    }

    private void handelPrices(DItems item, JSONObject data) {
        //库存
        JSONObject dynStock = (JSONObject) data.get("inventoryDO");
        if (null != dynStock) {
            if (log.isDebugEnabled()) {
                log.debug(dynStock.toJSONString());
                log.debug("icTotalQuantity={}", dynStock.getInteger("icTotalQuantity").toString());
                log.debug("totalQuantity={}", dynStock.getInteger("totalQuantity").toString());
            }
            item.setStock(dynStock.getInteger("totalQuantity"));
            item.setSellableQuantity(dynStock.getInteger("icTotalQuantity"));
            //库存明细
            if (null != dynStock.get("skuQuantity")) {
                item.setSkuStock(dynStock.get("skuQuantity").toString());
            }
        }

        //销售量
        JSONObject soldQuantity = data.getJSONObject("sellCountDO");
        if (null != soldQuantity) {
            if (log.isDebugEnabled()) {
                log.debug("soldTotalCount={}", soldQuantity.getInteger("sellCount").toString());
            }
            item.setSoldTotalCount(soldQuantity.getInteger("sellCount"));
            item.setConfirmGoodsCount(0);
        }

        //获取优惠信息及价格
        BigDecimal price = null;
        //区间价
        if (item.getMarkerPrice().indexOf("-") > 0) {
            price = new BigDecimal(item.getMarkerPrice().split("-")[0].trim());
        } else {
            price = new BigDecimal(item.getMarkerPrice());
        }
        //销售金额
        item.setPrice(price);
        item.setTotalSales(item.getPrice().multiply(new BigDecimal(item.getSoldTotalCount())));

        //邮费
        item.setPostFee(BigDecimal.ZERO);
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            String res = readHtmlFile(TmallItemDetail.class.getClassLoader().getResource("").getPath() + "/file/tmall_detail.html", "GBK");
            TmallItemDetail dox = new TmallItemDetail(res).call();

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
