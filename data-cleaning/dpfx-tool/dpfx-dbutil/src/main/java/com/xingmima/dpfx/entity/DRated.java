package com.xingmima.dpfx.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DRated {
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
     * 最近一周好评总数
     */
    private Integer weekGood;

    /**
     * 最近一周中评总数
     */
    private Integer weekNeutral;

    /**
     * 最近一周差评总数
     */
    private Integer weekBad;

    /**
     * 最近一月好评总数
     */
    private Integer monthGood;

    /**
     * 最近一月中评总数
     */
    private Integer monthNeutral;

    /**
     * 最近一月差评总数
     */
    private Integer monthBad;

    /**
     * 最近半年好评总数
     */
    private Integer halfyearGood;

    /**
     * 最近半年中评总数
     */
    private Integer halfyearNeutral;

    /**
     * 最近半年差评总数
     */
    private Integer halfyearBad;

    /**
     * 半年以前好评总数
     */
    private Integer agoGood;

    /**
     * 半年以前中评总数
     */
    private Integer agoNeutral;

    /**
     * 半年以前差评总数
     */
    private Integer agoBad;

    /**
     * 好评率
     */
    private BigDecimal rating;

    /**
     * 更新时间
     */
    private Date updated;

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
     * 最近一周好评总数
     * @return week_good 最近一周好评总数
     */
    public Integer getWeekGood() {
        return weekGood;
    }

    /**
     * 最近一周好评总数
     * @param weekGood 最近一周好评总数
     */
    public void setWeekGood(Integer weekGood) {
        this.weekGood = weekGood;
    }

    /**
     * 最近一周中评总数
     * @return week_neutral 最近一周中评总数
     */
    public Integer getWeekNeutral() {
        return weekNeutral;
    }

    /**
     * 最近一周中评总数
     * @param weekNeutral 最近一周中评总数
     */
    public void setWeekNeutral(Integer weekNeutral) {
        this.weekNeutral = weekNeutral;
    }

    /**
     * 最近一周差评总数
     * @return week_bad 最近一周差评总数
     */
    public Integer getWeekBad() {
        return weekBad;
    }

    /**
     * 最近一周差评总数
     * @param weekBad 最近一周差评总数
     */
    public void setWeekBad(Integer weekBad) {
        this.weekBad = weekBad;
    }

    /**
     * 最近一月好评总数
     * @return month_good 最近一月好评总数
     */
    public Integer getMonthGood() {
        return monthGood;
    }

    /**
     * 最近一月好评总数
     * @param monthGood 最近一月好评总数
     */
    public void setMonthGood(Integer monthGood) {
        this.monthGood = monthGood;
    }

    /**
     * 最近一月中评总数
     * @return month_neutral 最近一月中评总数
     */
    public Integer getMonthNeutral() {
        return monthNeutral;
    }

    /**
     * 最近一月中评总数
     * @param monthNeutral 最近一月中评总数
     */
    public void setMonthNeutral(Integer monthNeutral) {
        this.monthNeutral = monthNeutral;
    }

    /**
     * 最近一月差评总数
     * @return month_bad 最近一月差评总数
     */
    public Integer getMonthBad() {
        return monthBad;
    }

    /**
     * 最近一月差评总数
     * @param monthBad 最近一月差评总数
     */
    public void setMonthBad(Integer monthBad) {
        this.monthBad = monthBad;
    }

    /**
     * 最近半年好评总数
     * @return halfyear_good 最近半年好评总数
     */
    public Integer getHalfyearGood() {
        return halfyearGood;
    }

    /**
     * 最近半年好评总数
     * @param halfyearGood 最近半年好评总数
     */
    public void setHalfyearGood(Integer halfyearGood) {
        this.halfyearGood = halfyearGood;
    }

    /**
     * 最近半年中评总数
     * @return halfyear_neutral 最近半年中评总数
     */
    public Integer getHalfyearNeutral() {
        return halfyearNeutral;
    }

    /**
     * 最近半年中评总数
     * @param halfyearNeutral 最近半年中评总数
     */
    public void setHalfyearNeutral(Integer halfyearNeutral) {
        this.halfyearNeutral = halfyearNeutral;
    }

    /**
     * 最近半年差评总数
     * @return halfyear_bad 最近半年差评总数
     */
    public Integer getHalfyearBad() {
        return halfyearBad;
    }

    /**
     * 最近半年差评总数
     * @param halfyearBad 最近半年差评总数
     */
    public void setHalfyearBad(Integer halfyearBad) {
        this.halfyearBad = halfyearBad;
    }

    /**
     * 半年以前好评总数
     * @return ago_good 半年以前好评总数
     */
    public Integer getAgoGood() {
        return agoGood;
    }

    /**
     * 半年以前好评总数
     * @param agoGood 半年以前好评总数
     */
    public void setAgoGood(Integer agoGood) {
        this.agoGood = agoGood;
    }

    /**
     * 半年以前中评总数
     * @return ago_neutral 半年以前中评总数
     */
    public Integer getAgoNeutral() {
        return agoNeutral;
    }

    /**
     * 半年以前中评总数
     * @param agoNeutral 半年以前中评总数
     */
    public void setAgoNeutral(Integer agoNeutral) {
        this.agoNeutral = agoNeutral;
    }

    /**
     * 半年以前差评总数
     * @return ago_bad 半年以前差评总数
     */
    public Integer getAgoBad() {
        return agoBad;
    }

    /**
     * 半年以前差评总数
     * @param agoBad 半年以前差评总数
     */
    public void setAgoBad(Integer agoBad) {
        this.agoBad = agoBad;
    }

    /**
     * 好评率
     * @return rating 好评率
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * 好评率
     * @param rating 好评率
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", shopid=").append(shopid);
        sb.append(", weekGood=").append(weekGood);
        sb.append(", weekNeutral=").append(weekNeutral);
        sb.append(", weekBad=").append(weekBad);
        sb.append(", monthGood=").append(monthGood);
        sb.append(", monthNeutral=").append(monthNeutral);
        sb.append(", monthBad=").append(monthBad);
        sb.append(", halfyearGood=").append(halfyearGood);
        sb.append(", halfyearNeutral=").append(halfyearNeutral);
        sb.append(", halfyearBad=").append(halfyearBad);
        sb.append(", agoGood=").append(agoGood);
        sb.append(", agoNeutral=").append(agoNeutral);
        sb.append(", agoBad=").append(agoBad);
        sb.append(", rating=").append(rating);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}