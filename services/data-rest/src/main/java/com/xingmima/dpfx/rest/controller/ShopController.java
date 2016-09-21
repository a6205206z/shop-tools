package com.xingmima.dpfx.rest.controller;

import com.xingmima.dpfx.rest.dto.QueryShopDTO;
import com.xingmima.dpfx.rest.dto.TopShopDTO;
import com.xingmima.dpfx.rest.entity.TFollow;
import com.xingmima.dpfx.rest.response.ApiStatusCode;
import com.xingmima.dpfx.rest.response.ResponseDataModel;
import com.xingmima.dpfx.rest.service.RShopService;
import com.xingmima.dpfx.rest.service.StoreManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

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
    private RShopService rShopService;
    @Autowired
    private StoreManageService storeService;

    @RequestMapping("/shop/staisinfo/{uid}/{type}")
    @ResponseBody
    public ResponseDataModel getShopStatisticalInformation(@PathVariable String uid, @PathVariable String type) {
        try {
            if (StringUtils.isEmpty(uid)) {
                return error(ApiStatusCode.ACCOUNT_NOT_BIND_SHOP);
            }
            TFollow login = this.storeService.getBindingShop(uid);
            if (login == null) {
                return error(ApiStatusCode.ACCOUNT_NOT_BIND_SHOP);
            }
            QueryShopDTO dto = this.rShopService.getShopDiffInfo(login.getShopid(), type);
            return success(dto);
        } catch (Exception e) {
            log.error("店铺概况请求错误:", e);
            return error(ApiStatusCode.BUSSINESS_EXCEPTION);
        }
    }

    @RequestMapping("/shop/report/{shopid}")
    @ResponseBody
    public ResponseDataModel getShopReportSevenDay(@PathVariable Long shopid) {
        try {
            HashMap<String, HashMap<String, Object>> report = this.rShopService.getShopReportSevenDay(shopid);
            return success(report);
        } catch (Exception e) {
            log.error("店铺体检数据出错:", e);
            return error(ApiStatusCode.BUSSINESS_EXCEPTION);
        }
    }

    @RequestMapping("/")
    public String test() {
        return "店参谋服务启动成功!";
    }
}
