package com.xingmima.dpfx.rest.controller;

import com.xingmima.dpfx.rest.dto.QueryShopDTO;
import com.xingmima.dpfx.rest.response.ResponseDataModel;
import com.xingmima.dpfx.rest.service.RShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

    @RequestMapping("/shop/staisinfo/{shopid}/{type}")
    @ResponseBody
    public ResponseDataModel getShopStatisticalInformation(@PathVariable Long shopid, @PathVariable String type) {
        QueryShopDTO dto = dao.getShopDiffInfo(shopid, type);
        //return error(ApiStatusCode.DB_DELETE_ERROR);
        return success(dto);
    }

    @RequestMapping("/shop/report/{shopid}")
    @ResponseBody
    public ResponseDataModel getShopReportSevenDay(@PathVariable Long shopid) {
        HashMap<String, HashMap<String, Object>> report = dao.getShopReportSevenDay(shopid);
        return success(report);
    }

    @RequestMapping("/")
    public String test() {
        return "hello";
    }
}
