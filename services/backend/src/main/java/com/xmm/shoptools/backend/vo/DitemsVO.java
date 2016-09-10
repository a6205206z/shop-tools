package com.xmm.shoptools.backend.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xmm.shoptools.backend.utils.CustomDateSerializer;

public class DitemsVO extends BaseVo {
	
	//columns START
	private java.lang.Long date;
	private java.lang.Long shopid;
	private java.lang.Long numiid;
	private java.lang.String title;
	private java.lang.String itemUrl;
	private java.lang.String picUrl;
	private java.lang.Long rcid;
	private java.lang.Long cid;
	private java.lang.String markerPrice;
	private java.math.BigDecimal price;
	private java.math.BigDecimal postFee;
	private java.lang.Integer soldTotalCount;
	private java.lang.Integer confirmGoodsCount;
	private java.lang.Integer totalRatedCount;
	private java.math.BigDecimal totalSales;
	private java.lang.Integer stock;
	private java.lang.Integer sellableQuantity;
	private java.lang.String skuStock;
	private java.lang.String brandName;
	private java.util.Date listTime;
	private java.util.Date delistTime;
	private java.util.Date addTime;
	private java.lang.Boolean isDelisting;
	private java.math.BigDecimal rated;
	private java.util.Date updated;
	//columns END
	public void setDate(java.lang.Long value) {
		this.date = value;
	}
	public java.lang.Long getDate() {
		return this.date;
	}
	public void setShopid(java.lang.Long value) {
		this.shopid = value;
	}
	public java.lang.Long getShopid() {
		return this.shopid;
	}
	public void setNumiid(java.lang.Long value) {
		this.numiid = value;
	}
	public java.lang.Long getNumiid() {
		return this.numiid;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setItemUrl(java.lang.String value) {
		this.itemUrl = value;
	}
	public java.lang.String getItemUrl() {
		return this.itemUrl;
	}
	public void setPicUrl(java.lang.String value) {
		this.picUrl = value;
	}
	public java.lang.String getPicUrl() {
		return this.picUrl;
	}
	public void setRcid(java.lang.Long value) {
		this.rcid = value;
	}
	public java.lang.Long getRcid() {
		return this.rcid;
	}
	public void setCid(java.lang.Long value) {
		this.cid = value;
	}
	public java.lang.Long getCid() {
		return this.cid;
	}
	public void setMarkerPrice(java.lang.String value) {
		this.markerPrice = value;
	}
	public java.lang.String getMarkerPrice() {
		return this.markerPrice;
	}
	public void setPrice(java.math.BigDecimal value) {
		this.price = value;
	}
	public java.math.BigDecimal getPrice() {
		return this.price;
	}
	public void setPostFee(java.math.BigDecimal value) {
		this.postFee = value;
	}
	public java.math.BigDecimal getPostFee() {
		return this.postFee;
	}
	public void setSoldTotalCount(java.lang.Integer value) {
		this.soldTotalCount = value;
	}
	public java.lang.Integer getSoldTotalCount() {
		return this.soldTotalCount;
	}
	public void setConfirmGoodsCount(java.lang.Integer value) {
		this.confirmGoodsCount = value;
	}
	public java.lang.Integer getConfirmGoodsCount() {
		return this.confirmGoodsCount;
	}
	public void setTotalRatedCount(java.lang.Integer value) {
		this.totalRatedCount = value;
	}
	public java.lang.Integer getTotalRatedCount() {
		return this.totalRatedCount;
	}
	public void setTotalSales(java.math.BigDecimal value) {
		this.totalSales = value;
	}
	public java.math.BigDecimal getTotalSales() {
		return this.totalSales;
	}
	public void setStock(java.lang.Integer value) {
		this.stock = value;
	}
	public java.lang.Integer getStock() {
		return this.stock;
	}
	public void setSellableQuantity(java.lang.Integer value) {
		this.sellableQuantity = value;
	}
	public java.lang.Integer getSellableQuantity() {
		return this.sellableQuantity;
	}
	public void setSkuStock(java.lang.String value) {
		this.skuStock = value;
	}
	public java.lang.String getSkuStock() {
		return this.skuStock;
	}
	public void setBrandName(java.lang.String value) {
		this.brandName = value;
	}
	public java.lang.String getBrandName() {
		return this.brandName;
	}
	public void setListTime(java.util.Date value) {
		this.listTime = value;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getListTime() {
		return this.listTime;
	}
	public void setDelistTime(java.util.Date value) {
		this.delistTime = value;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getDelistTime() {
		return this.delistTime;
	}
	public void setAddTime(java.util.Date value) {
		this.addTime = value;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getAddTime() {
		return this.addTime;
	}
	public void setIsDelisting(java.lang.Boolean value) {
		this.isDelisting = value;
	}
	public java.lang.Boolean getIsDelisting() {
		return this.isDelisting;
	}
	public void setRated(java.math.BigDecimal value) {
		this.rated = value;
	}
	public java.math.BigDecimal getRated() {
		return this.rated;
	}
	public void setUpdated(java.util.Date value) {
		this.updated = value;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getUpdated() {
		return this.updated;
	}
	
    @Override
    public String toString() {
        return "Ditems [date=" + date + ", shopid=" + shopid + ", numiid=" + numiid + ", title="
               + title + ", itemUrl=" + itemUrl + ", picUrl=" + picUrl + ", rcid=" + rcid
               + ", cid=" + cid + ", markerPrice=" + markerPrice + ", price=" + price
               + ", postFee=" + postFee + ", soldTotalCount=" + soldTotalCount
               + ", confirmGoodsCount=" + confirmGoodsCount + ", totalRatedCount="
               + totalRatedCount + ", totalSales=" + totalSales + ", stock=" + stock
               + ", sellableQuantity=" + sellableQuantity + ", skuStock=" + skuStock
               + ", brandName=" + brandName + ", listTime=" + listTime + ", delistTime="
               + delistTime + ", addTime=" + addTime + ", isDelisting=" + isDelisting + ", rated="
               + rated + ", updated=" + updated + "]";
    }
	
}

