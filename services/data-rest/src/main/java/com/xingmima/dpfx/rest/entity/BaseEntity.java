package com.xingmima.dpfx.rest.entity;

import java.io.Serializable;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version BaseEntity, v 0.1
 * @date 2016/9/14 13:00
 */
public class BaseEntity implements Serializable, Cloneable {

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
