package com.xingmima.dpfx.entity;

import java.util.Date;

public class DFans {
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
     * 微淘粉丝数
     */
    private Integer weitaoFans;

    /**
     * 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
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
     * 微淘粉丝数
     * @return weitao_fans 微淘粉丝数
     */
    public Integer getWeitaoFans() {
        return weitaoFans;
    }

    /**
     * 微淘粉丝数
     * @param weitaoFans 微淘粉丝数
     */
    public void setWeitaoFans(Integer weitaoFans) {
        this.weitaoFans = weitaoFans;
    }

    /**
     * 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @return updated 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @param updated 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
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
        sb.append(", weitaoFans=").append(weitaoFans);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}