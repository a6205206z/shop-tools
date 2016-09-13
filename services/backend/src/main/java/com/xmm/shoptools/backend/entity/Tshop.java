package com.xmm.shoptools.backend.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xmm.shoptools.backend.utils.CustomDateSerializer;

public class Tshop extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	//columns START
	private java.lang.Long shopid;
	private java.lang.Long sellerId;
	private java.lang.String securityId;
	private java.lang.String nick;
	private java.lang.Long cid;
	private java.lang.String storeUrl;
	private java.lang.String category;
	private java.lang.String type;
	private java.lang.String location;
	private java.lang.String serviceNumber;
	private java.util.Date storeDate;
	private java.lang.String status;
	private java.lang.Integer lastTimes;
	private java.util.Date created;
	private java.util.Date updated;
	//columns END
	public void setShopid(java.lang.Long value) {
		this.shopid = value;
	}
	public java.lang.Long getShopid() {
		return this.shopid;
	}
	public void setSellerId(java.lang.Long value) {
		this.sellerId = value;
	}
	public java.lang.Long getSellerId() {
		return this.sellerId;
	}
	public void setSecurityId(java.lang.String value) {
		this.securityId = value;
	}
	public java.lang.String getSecurityId() {
		return this.securityId;
	}
	public void setNick(java.lang.String value) {
		this.nick = value;
	}
	public java.lang.String getNick() {
		return this.nick;
	}
	public void setCid(java.lang.Long value) {
		this.cid = value;
	}
	public java.lang.Long getCid() {
		return this.cid;
	}
	public void setStoreUrl(java.lang.String value) {
		this.storeUrl = value;
	}
	public java.lang.String getStoreUrl() {
		return this.storeUrl;
	}
	public void setCategory(java.lang.String value) {
		this.category = value;
	}
	public java.lang.String getCategory() {
		return this.category;
	}
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	public void setLocation(java.lang.String value) {
		this.location = value;
	}
	public java.lang.String getLocation() {
		return this.location;
	}
	public void setServiceNumber(java.lang.String value) {
		this.serviceNumber = value;
	}
	public java.lang.String getServiceNumber() {
		return this.serviceNumber;
	}
	public void setStoreDate(java.util.Date value) {
		this.storeDate = value;
	}
	public java.util.Date getStoreDate() {
		return this.storeDate;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setLastTimes(java.lang.Integer value) {
		this.lastTimes = value;
	}
	public java.lang.Integer getLastTimes() {
		return this.lastTimes;
	}
	public void setCreated(java.util.Date value) {
		this.created = value;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public java.util.Date getCreated() {
		return this.created;
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
        return "Tshop [shopid=" + shopid + ", sellerId=" + sellerId + ", securityId=" + securityId
               + ", nick=" + nick + ", cid=" + cid + ", storeUrl=" + storeUrl + ", category="
               + category + ", type=" + type + ", location=" + location + ", serviceNumber="
               + serviceNumber + ", storeDate=" + storeDate + ", status=" + status + ", lastTimes="
               + lastTimes + ", created=" + created + ", updated=" + updated + "]";
    }
	
}

