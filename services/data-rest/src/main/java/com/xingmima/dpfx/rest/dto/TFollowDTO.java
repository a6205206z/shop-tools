package com.xingmima.dpfx.rest.dto;

import java.io.Serializable;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Baoluo
 * @date 2016年9月19日 上午10:27:52
 * @version TFollowDTO.java, v 0.1
 *
 */
public class TFollowDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 会员id
     */
    private String uid;
    
    /**
     * 店铺id
     */
    private Long shopid;
    
    /**
     * 店铺logo地址
     */
    private String logoUrl;
    
    /**
     * 店铺名称
     */
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getShopid() {
        return shopid;
    }

    public void setShopid(Long shopid) {
        this.shopid = shopid;
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

    /**
     *@desc
     *@date 2016年9月19日
     *@author Baoluo
     *@parameter
     *@see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TFollowDTO [id=" + id + ", uid=" + uid + ", shopid=" + shopid + ", logoUrl="
               + logoUrl + ", title=" + title + "]";
    }
}

