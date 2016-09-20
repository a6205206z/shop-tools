package com.xingmima.dpfx.rest.dto;

import java.io.Serializable;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Baoluo
 * @date 2016年9月19日 下午8:41:53
 * @version StoreDTO.java, v 0.1
 *
 */
public class StoreDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            uid;
    private String            nick;
    private int               isBinding;
    private Long              shopid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(int isBinding) {
        this.isBinding = isBinding;
    }

    public Long getShopid() {
        return shopid;
    }

    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }
}
