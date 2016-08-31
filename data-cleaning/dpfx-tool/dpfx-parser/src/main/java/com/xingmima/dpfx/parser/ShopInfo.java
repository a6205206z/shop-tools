package com.xingmima.dpfx.parser;

import com.xingmima.dpfx.inter.DPFXParserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopInfo, v 0.1
 * @date 2016/8/30 13:58
 */
public class ShopInfo extends DPFXParserImpl implements Callable<Object> {

    private final static Logger log = LoggerFactory.getLogger(ShopInfo.class);

    public ShopInfo(String resource) {
        super(resource);
    }

    @Override
    public Object call() throws Exception {
        log.info("分析店铺数据ok");
        return "ok.";
    }

    public static void main(String[] args) {
        log.error("error..");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
