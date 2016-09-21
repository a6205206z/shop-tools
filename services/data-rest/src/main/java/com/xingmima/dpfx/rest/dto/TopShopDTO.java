package com.xingmima.dpfx.rest.dto;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Baoluo
 * @date 2016年9月18日 下午5:53:37
 * @version TopShopDTO.java, v 0.1
 *
 */
public class TopShopDTO extends BaseDTO {
    /**
     * 店铺id
     */
    private Long shopid;
    
    /**
     * 店铺url
     */
    private String storeUrl;
    
    /**
     * 店铺logo地址
     */
    private String logoUrl;
    
    /**
     * 店铺名称
     */
    private String title;
    
    /**
     * 成交数量
     */
    private Integer soldTotalCount;
    
    /**
     * 访问量
     */
    private Integer iPv;
    
    /**
     * 分享数量
     */
    private Integer iShareNum;
    
    /**
     * 收藏数量
     */
    private Integer iFavoriteNum;

    public Long getShopid() {
        return shopid;
    }

    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSoldTotalCount() {
        return soldTotalCount;
    }

    public void setSoldTotalCount(Integer soldTotalCount) {
        this.soldTotalCount = soldTotalCount;
    }

    public Integer getiPv() {
        return iPv;
    }

    public void setiPv(Integer iPv) {
        this.iPv = iPv;
    }

    public Integer getiShareNum() {
        return iShareNum;
    }

    public void setiShareNum(Integer iShareNum) {
        this.iShareNum = iShareNum;
    }

    public Integer getiFavoriteNum() {
        return iFavoriteNum;
    }

    public void setiFavoriteNum(Integer iFavoriteNum) {
        this.iFavoriteNum = iFavoriteNum;
    }

    /**
     *@desc
     *@date 2016年9月21日
     *@author Baoluo
     *@parameter
     *@see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TopShopDTO [shopid=" + shopid + ", storeUrl=" + storeUrl + ", logoUrl=" + logoUrl
               + ", title=" + title + ", soldTotalCount=" + soldTotalCount + ", iPv=" + iPv
               + ", iShareNum=" + iShareNum + ", iFavoriteNum=" + iFavoriteNum + "]";
    }
}

