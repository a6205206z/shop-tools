package com.xmm.shoptools.backend.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xmm.shoptools.backend.utils.CustomDateSerializer;

public class Dshop extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	//columns START
	private java.lang.Long date;
	private java.lang.Long shopid;
	private java.lang.String title;
	private java.lang.Long creditScore;
	private java.lang.String creditLevel;
	private java.lang.Integer creditTotalNum;
	private java.lang.Integer creditGoodNum;
	private java.math.BigDecimal rating;
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
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setCreditScore(java.lang.Long value) {
		this.creditScore = value;
	}
	public java.lang.Long getCreditScore() {
		return this.creditScore;
	}
	public void setCreditLevel(java.lang.String value) {
		this.creditLevel = value;
	}
	public java.lang.String getCreditLevel() {
		return this.creditLevel;
	}
	public void setCreditTotalNum(java.lang.Integer value) {
		this.creditTotalNum = value;
	}
	public java.lang.Integer getCreditTotalNum() {
		return this.creditTotalNum;
	}
	public void setCreditGoodNum(java.lang.Integer value) {
		this.creditGoodNum = value;
	}
	public java.lang.Integer getCreditGoodNum() {
		return this.creditGoodNum;
	}
	public void setRating(java.math.BigDecimal value) {
		this.rating = value;
	}
	public java.math.BigDecimal getRating() {
		return this.rating;
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
        return "Dshop [date=" + date + ", shopid=" + shopid + ", title=" + title + ", creditScore="
               + creditScore + ", creditLevel=" + creditLevel + ", creditTotalNum="
               + creditTotalNum + ", creditGoodNum=" + creditGoodNum + ", rating=" + rating
               + ", updated=" + updated + "]";
    }
	
}

