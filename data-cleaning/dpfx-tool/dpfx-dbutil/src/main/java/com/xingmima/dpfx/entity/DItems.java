package com.xingmima.dpfx.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DItems {
    /**
     * 标识列
     */
    private String id;

    /**
     * 数据抓取日期：YYYYMMDDHH
     */
    private Long date;

    /**
     * 店铺ID
     */
    private Long shopid;

    /**
     * 商品ID
     */
    private Long numiid;

    /**
     * 商品标题,不能超过60字节
     */
    private String title;

    /**
     * 商品URL
     */
    private String itemUrl;

    /**
     * 图片链接地址
     */
    private String picUrl;

    /**
     * 一级类目
     */
    private Long rcid;

    /**
     * 二级类目
     */
    private Long cid;

    /**
     * 市场价格:1.0~100
     */
    private String markerPrice;

    /**
     * 销售价格，优惠后售价
     */
    private BigDecimal price;

    /**
     * 邮费
     */
    private BigDecimal postFee;

    /**
     * 促销优惠信息
     */
    private String promoInfo;

    /**
     * 近30天交易成功数
     */
    private Integer soldTotalCount;

    /**
     * 近30天确认收货数
     */
    private Integer confirmGoodsCount;

    /**
     * 累计评论数
     */
    private Integer totalRatedCount;

    /**
     * 销售额
     */
    private BigDecimal totalSales;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 可销售库存
     */
    private Integer sellableQuantity;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 自动上架时间
     */
    private Date listTime;

    /**
     * 自动下架时间（跟据上架时间计算+7天）
     */
    private Date delistTime;

    /**
     * 商品创建时间
     */
    private Date addTime;

    /**
     * 是否下架 true 正常 false下架
     */
    private Boolean isDelisting;

    /**
     * 商品动态评分(天猫)
     */
    private BigDecimal rated;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * sku库存明细
     */
    private String skuStock;

    /**
     * 标识列
     * @return id 标识列
     */
    public String getId() {
        return id;
    }

    /**
     * 标识列
     * @param id 标识列
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 数据抓取日期：YYYYMMDDHH
     * @return date 数据抓取日期：YYYYMMDDHH
     */
    public Long getDate() {
        return date;
    }

    /**
     * 数据抓取日期：YYYYMMDDHH
     * @param date 数据抓取日期：YYYYMMDDHH
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * 店铺ID
     * @return shopid 店铺ID
     */
    public Long getShopid() {
        return shopid;
    }

    /**
     * 店铺ID
     * @param shopid 店铺ID
     */
    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    /**
     * 商品ID
     * @return numiid 商品ID
     */
    public Long getNumiid() {
        return numiid;
    }

    /**
     * 商品ID
     * @param numiid 商品ID
     */
    public void setNumiid(Long numiid) {
        this.numiid = numiid;
    }

    /**
     * 商品标题,不能超过60字节
     * @return title 商品标题,不能超过60字节
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品标题,不能超过60字节
     * @param title 商品标题,不能超过60字节
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 商品URL
     * @return item_url 商品URL
     */
    public String getItemUrl() {
        return itemUrl;
    }

    /**
     * 商品URL
     * @param itemUrl 商品URL
     */
    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    /**
     * 图片链接地址
     * @return pic_url 图片链接地址
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 图片链接地址
     * @param picUrl 图片链接地址
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * 一级类目
     * @return rcid 一级类目
     */
    public Long getRcid() {
        return rcid;
    }

    /**
     * 一级类目
     * @param rcid 一级类目
     */
    public void setRcid(Long rcid) {
        this.rcid = rcid;
    }

    /**
     * 二级类目
     * @return cid 二级类目
     */
    public Long getCid() {
        return cid;
    }

    /**
     * 二级类目
     * @param cid 二级类目
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 市场价格:1.0~100
     * @return marker_price 市场价格:1.0~100
     */
    public String getMarkerPrice() {
        return markerPrice;
    }

    /**
     * 市场价格:1.0~100
     * @param markerPrice 市场价格:1.0~100
     */
    public void setMarkerPrice(String markerPrice) {
        this.markerPrice = markerPrice == null ? null : markerPrice.trim();
    }

    /**
     * 销售价格，优惠后售价
     * @return price 销售价格，优惠后售价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 销售价格，优惠后售价
     * @param price 销售价格，优惠后售价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 邮费
     * @return post_fee 邮费
     */
    public BigDecimal getPostFee() {
        return postFee;
    }

    /**
     * 邮费
     * @param postFee 邮费
     */
    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    /**
     * 近30天交易成功数
     * @return sold_total_count 近30天交易成功数
     */
    public Integer getSoldTotalCount() {
        return soldTotalCount;
    }

    /**
     * 近30天交易成功数
     * @param soldTotalCount 近30天交易成功数
     */
    public void setSoldTotalCount(Integer soldTotalCount) {
        this.soldTotalCount = soldTotalCount;
    }

    /**
     * 近30天确认收货数
     * @return confirm_goods_count 近30天确认收货数
     */
    public Integer getConfirmGoodsCount() {
        return confirmGoodsCount;
    }

    /**
     * 近30天确认收货数
     * @param confirmGoodsCount 近30天确认收货数
     */
    public void setConfirmGoodsCount(Integer confirmGoodsCount) {
        this.confirmGoodsCount = confirmGoodsCount;
    }

    /**
     * 累计评论数
     * @return total_rated_count 累计评论数
     */
    public Integer getTotalRatedCount() {
        return totalRatedCount;
    }

    /**
     * 累计评论数
     * @param totalRatedCount 累计评论数
     */
    public void setTotalRatedCount(Integer totalRatedCount) {
        this.totalRatedCount = totalRatedCount;
    }

    /**
     * 销售额
     * @return total_sales 销售额
     */
    public BigDecimal getTotalSales() {
        return totalSales;
    }

    /**
     * 销售额
     * @param totalSales 销售额
     */
    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * 库存
     * @return stock 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 库存
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 可销售库存
     * @return sellable_quantity 可销售库存
     */
    public Integer getSellableQuantity() {
        return sellableQuantity;
    }

    /**
     * 可销售库存
     * @param sellableQuantity 可销售库存
     */
    public void setSellableQuantity(Integer sellableQuantity) {
        this.sellableQuantity = sellableQuantity;
    }

    /**
     * 品牌
     * @return brand_name 品牌
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌
     * @param brandName 品牌
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * 自动上架时间
     * @return list_time 自动上架时间
     */
    public Date getListTime() {
        return listTime;
    }

    /**
     * 自动上架时间
     * @param listTime 自动上架时间
     */
    public void setListTime(Date listTime) {
        this.listTime = listTime;
    }

    /**
     * 自动下架时间（跟据上架时间计算+7天）
     * @return delist_time 自动下架时间（跟据上架时间计算+7天）
     */
    public Date getDelistTime() {
        return delistTime;
    }

    /**
     * 自动下架时间（跟据上架时间计算+7天）
     * @param delistTime 自动下架时间（跟据上架时间计算+7天）
     */
    public void setDelistTime(Date delistTime) {
        this.delistTime = delistTime;
    }

    /**
     * 商品创建时间
     * @return add_time 商品创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 商品创建时间
     * @param addTime 商品创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 是否下架 true 正常 false下架
     * @return is_delisting 是否下架 true 正常 false下架
     */
    public Boolean getIsDelisting() {
        return isDelisting;
    }

    /**
     * 是否下架 true 正常 false下架
     * @param isDelisting 是否下架 true 正常 false下架
     */
    public void setIsDelisting(Boolean isDelisting) {
        this.isDelisting = isDelisting;
    }

    /**
     * 商品动态评分(天猫)
     * @return rated 商品动态评分(天猫)
     */
    public BigDecimal getRated() {
        return rated;
    }

    /**
     * 商品动态评分(天猫)
     * @param rated 商品动态评分(天猫)
     */
    public void setRated(BigDecimal rated) {
        this.rated = rated;
    }

    /**
     * 更新时间
     * @return updated 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 更新时间
     * @param updated 更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * sku库存明细
     * @return sku_stock sku库存明细
     */
    public String getSkuStock() {
        return skuStock;
    }

    /**
     * sku库存明细
     * @param skuStock sku库存明细
     */
    public void setSkuStock(String skuStock) {
        this.skuStock = skuStock == null ? null : skuStock.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", shopid=").append(shopid);
        sb.append(", numiid=").append(numiid);
        sb.append(", title=").append(title);
        sb.append(", itemUrl=").append(itemUrl);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", rcid=").append(rcid);
        sb.append(", cid=").append(cid);
        sb.append(", markerPrice=").append(markerPrice);
        sb.append(", price=").append(price);
        sb.append(", postFee=").append(postFee);
        sb.append(", promoInfo=").append(promoInfo);
        sb.append(", soldTotalCount=").append(soldTotalCount);
        sb.append(", confirmGoodsCount=").append(confirmGoodsCount);
        sb.append(", totalRatedCount=").append(totalRatedCount);
        sb.append(", totalSales=").append(totalSales);
        sb.append(", stock=").append(stock);
        sb.append(", sellableQuantity=").append(sellableQuantity);
        sb.append(", brandName=").append(brandName);
        sb.append(", listTime=").append(listTime);
        sb.append(", delistTime=").append(delistTime);
        sb.append(", addTime=").append(addTime);
        sb.append(", isDelisting=").append(isDelisting);
        sb.append(", rated=").append(rated);
        sb.append(", updated=").append(updated);
        sb.append(", skuStock=").append(skuStock);
        sb.append("]");
        return sb.toString();
    }
}