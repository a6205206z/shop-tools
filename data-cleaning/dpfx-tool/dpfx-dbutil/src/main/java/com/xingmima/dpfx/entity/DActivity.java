package com.xingmima.dpfx.entity;

import java.util.Date;

public class DActivity {
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
     * 活动类型
     */
    private String type;

    /**
     * 活动优惠信息
     */
    private String info;

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
     * 活动类型
     * @return type 活动类型
     */
    public String getType() {
        return type;
    }

    /**
     * 活动类型
     * @param type 活动类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 活动优惠信息
     * @return info 活动优惠信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 活动优惠信息
     * @param info 活动优惠信息
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
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
        sb.append(", numiid=").append(numiid);
        sb.append(", type=").append(type);
        sb.append(", info=").append(info);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}