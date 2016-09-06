package com.xingmima.dpfx.entity;

import java.util.Date;

public class DItemNum {
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
     * 店铺收藏数
     */
    private Integer sFavorite;

    /**
     * 商品收藏数
     */
    private Integer iFavoriteNum;

    /**
     * 商品分享数
     */
    private Integer iShareNum;

    /**
     * 商品浏览量
     */
    private Integer iPv;

    /**
     * 维护时间
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
     * 店铺收藏数
     * @return s_favorite 店铺收藏数
     */
    public Integer getsFavorite() {
        return sFavorite;
    }

    /**
     * 店铺收藏数
     * @param sFavorite 店铺收藏数
     */
    public void setsFavorite(Integer sFavorite) {
        this.sFavorite = sFavorite;
    }

    /**
     * 商品收藏数
     * @return i_favorite_num 商品收藏数
     */
    public Integer getiFavoriteNum() {
        return iFavoriteNum;
    }

    /**
     * 商品收藏数
     * @param iFavoriteNum 商品收藏数
     */
    public void setiFavoriteNum(Integer iFavoriteNum) {
        this.iFavoriteNum = iFavoriteNum;
    }

    /**
     * 商品分享数
     * @return i_share_num 商品分享数
     */
    public Integer getiShareNum() {
        return iShareNum;
    }

    /**
     * 商品分享数
     * @param iShareNum 商品分享数
     */
    public void setiShareNum(Integer iShareNum) {
        this.iShareNum = iShareNum;
    }

    /**
     * 商品浏览量
     * @return i_pv 商品浏览量
     */
    public Integer getiPv() {
        return iPv;
    }

    /**
     * 商品浏览量
     * @param iPv 商品浏览量
     */
    public void setiPv(Integer iPv) {
        this.iPv = iPv;
    }

    /**
     * 维护时间
     * @return updated 维护时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 维护时间
     * @param updated 维护时间
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
        sb.append(", sFavorite=").append(sFavorite);
        sb.append(", iFavoriteNum=").append(iFavoriteNum);
        sb.append(", iShareNum=").append(iShareNum);
        sb.append(", iPv=").append(iPv);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}