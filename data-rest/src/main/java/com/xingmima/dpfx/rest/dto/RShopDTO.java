package com.xingmima.dpfx.rest.dto;

import com.xingmima.dpfx.rest.entity.BaseEntity;

import java.util.Date;

public class RShopDTO extends BaseDTO{
    /**
     * 标识列（今天-昨天）
     */
    private String id;

    /**
     * 日期:YYYYMMDD
     */
    private Long date;

    /**
     * 店铺ID
     */
    private Long shopid;

    /**
     * 当天在售商品数量
     */
    private Integer saleGoodsNum;

    /**
     * 当天下架商品数量
     */
    private Integer onGoodsNum;

    /**
     * 当天上架商品数量
     */
    private Integer offGoodsNum;

    /**
     * 当天店铺收藏总量
     */
    private Integer favoriteNum;

    /**
     * 当天商品收藏总量
     */
    private Integer iFavoriteNum;

    /**
     * 当天商品分享数
     */
    private Integer iShareNum;

    /**
     * 当天店铺流量
     */
    private Integer totalPv;

    /**
     * 当天微淘粉丝总量
     */
    private Integer totalWtFans;

    /**
     * 统计完成时间
     */
    private Date created;

    /**
     * 标识列（今天-昨天）
     *
     * @return id 标识列（今天-昨天）
     */
    public String getId() {
        return id;
    }

    /**
     * 标识列（今天-昨天）
     *
     * @param id 标识列（今天-昨天）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 日期:YYYYMMDD
     *
     * @return date 日期:YYYYMMDD
     */
    public Long getDate() {
        return date;
    }

    /**
     * 日期:YYYYMMDD
     *
     * @param date 日期:YYYYMMDD
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * 店铺ID
     *
     * @return shopid 店铺ID
     */
    public Long getShopid() {
        return shopid;
    }

    /**
     * 店铺ID
     *
     * @param shopid 店铺ID
     */
    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    /**
     * 当天在售商品数量
     *
     * @return sale_goods_num 当天在售商品数量
     */
    public Integer getSaleGoodsNum() {
        return saleGoodsNum;
    }

    /**
     * 当天在售商品数量
     *
     * @param saleGoodsNum 当天在售商品数量
     */
    public void setSaleGoodsNum(Integer saleGoodsNum) {
        this.saleGoodsNum = saleGoodsNum;
    }

    /**
     * 当天下架商品数量
     *
     * @return on_goods_num 当天下架商品数量
     */
    public Integer getOnGoodsNum() {
        return onGoodsNum;
    }

    /**
     * 当天下架商品数量
     *
     * @param onGoodsNum 当天下架商品数量
     */
    public void setOnGoodsNum(Integer onGoodsNum) {
        this.onGoodsNum = onGoodsNum;
    }

    /**
     * 当天上架商品数量
     *
     * @return off_goods_num 当天上架商品数量
     */
    public Integer getOffGoodsNum() {
        return offGoodsNum;
    }

    /**
     * 当天上架商品数量
     *
     * @param offGoodsNum 当天上架商品数量
     */
    public void setOffGoodsNum(Integer offGoodsNum) {
        this.offGoodsNum = offGoodsNum;
    }

    /**
     * 当天店铺收藏总量
     *
     * @return favorite_num 当天店铺收藏总量
     */
    public Integer getFavoriteNum() {
        return favoriteNum;
    }

    /**
     * 当天店铺收藏总量
     *
     * @param favoriteNum 当天店铺收藏总量
     */
    public void setFavoriteNum(Integer favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    /**
     * 当天商品收藏总量
     *
     * @return i_favorite_num 当天商品收藏总量
     */
    public Integer getiFavoriteNum() {
        return iFavoriteNum;
    }

    /**
     * 当天商品收藏总量
     *
     * @param iFavoriteNum 当天商品收藏总量
     */
    public void setiFavoriteNum(Integer iFavoriteNum) {
        this.iFavoriteNum = iFavoriteNum;
    }

    /**
     * 当天商品分享数
     *
     * @return i_share_num 当天商品分享数
     */
    public Integer getiShareNum() {
        return iShareNum;
    }

    /**
     * 当天商品分享数
     *
     * @param iShareNum 当天商品分享数
     */
    public void setiShareNum(Integer iShareNum) {
        this.iShareNum = iShareNum;
    }

    /**
     * 当天店铺流量
     *
     * @return total_pv 当天店铺流量
     */
    public Integer getTotalPv() {
        return totalPv;
    }

    /**
     * 当天店铺流量
     *
     * @param totalPv 当天店铺流量
     */
    public void setTotalPv(Integer totalPv) {
        this.totalPv = totalPv;
    }

    /**
     * 当天微淘粉丝总量
     *
     * @return total_wt_fans 当天微淘粉丝总量
     */
    public Integer getTotalWtFans() {
        return totalWtFans;
    }

    /**
     * 当天微淘粉丝总量
     *
     * @param totalWtFans 当天微淘粉丝总量
     */
    public void setTotalWtFans(Integer totalWtFans) {
        this.totalWtFans = totalWtFans;
    }

    /**
     * 统计完成时间
     *
     * @return created 统计完成时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 统计完成时间
     *
     * @param created 统计完成时间
     */
    public void setCreated(Date created) {
        this.created = created;
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
        sb.append(", saleGoodsNum=").append(saleGoodsNum);
        sb.append(", onGoodsNum=").append(onGoodsNum);
        sb.append(", offGoodsNum=").append(offGoodsNum);
        sb.append(", favoriteNum=").append(favoriteNum);
        sb.append(", iFavoriteNum=").append(iFavoriteNum);
        sb.append(", iShareNum=").append(iShareNum);
        sb.append(", totalPv=").append(totalPv);
        sb.append(", totalWtFans=").append(totalWtFans);
        sb.append(", created=").append(created);
        sb.append("]");
        return sb.toString();
    }
}