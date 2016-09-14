package com.xingmima.dpfx.rest.service;

import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.entity.RShop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RShopService, v 0.1
 * @date 2016/9/14 12:53
 */
@Service
public class RShopService {
    private static Logger log = LoggerFactory.getLogger(RShopService.class);

    @Autowired
    private RShopDao dao;

    //@Cacheable(value = "shop:bydate", key = "#root.targetClass+#root.methodName+#root.args")
    @Cacheable(value = "shop:bydate")
    public RShop getRShopByShop(@PathVariable Long shopid, @PathVariable Integer date) {
        log.error("error...");
        log.info("info...");
        log.debug("debug...");
        return dao.getRShopByShop(shopid, date);
    }
}
