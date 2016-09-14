package com.xingmima.dpfx.rest.controller;

import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.dto.RShopDTO;
import com.xingmima.dpfx.rest.response.ApiStatusCode;
import com.xingmima.dpfx.rest.response.ResponseDataModel;
import com.xingmima.dpfx.rest.service.RShopService;
import com.xingmima.dpfx.rest.util.BeanDTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopController, v 0.1
 * @date 2016/9/13 19:52
 */
@RestController
//@EnableAutoConfiguration
public class ShopController extends BaseController {

    private Logger log = LoggerFactory.getLogger(ShopController.class);
    @Autowired
    private RShopService dao;

    @RequestMapping("/shop/info/{shopid}/{date}")
    @ResponseBody
    public ResponseDataModel getRShopByShop(@PathVariable Long shopid, @PathVariable Integer date) {
        log.error("error...");
        log.info("info...");
        log.debug("debug...");
        RShopDTO back = new RShopDTO();
        BeanDTOUtil.copyObject(dao.getRShopByShop(shopid, date), back);
        //return error(ApiStatusCode.DB_DELETE_ERROR);
        return success(back);
    }

    @RequestMapping("/")
    public String test() {
        return "hello";
    }
}
