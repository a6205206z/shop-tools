package com.xingmima.dpfx.inter;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DPFXParserImpl, v 0.1
 * @date 2016/8/30 13:56
 */
public class DPFXParserImpl implements DPFXParser {

    /**
     * 待分析源码临时储存对象
     */
    private String resource = null;


    public DPFXParserImpl(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }



}
