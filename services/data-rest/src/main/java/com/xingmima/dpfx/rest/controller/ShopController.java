package com.xingmima.dpfx.rest.controller;

import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.dto.RShopDTO;
import com.xingmima.dpfx.rest.dto.TCategoryDTO;
import com.xingmima.dpfx.rest.dto.TFollowDTO;
import com.xingmima.dpfx.rest.dto.TopShopDTO;
import com.xingmima.dpfx.rest.entity.TCategory;
import com.xingmima.dpfx.rest.response.ApiStatusCode;
import com.xingmima.dpfx.rest.response.ResponseDataModel;
import com.xingmima.dpfx.rest.service.RShopService;
import com.xingmima.dpfx.rest.service.ShopService;
import com.xingmima.dpfx.rest.util.BeanDTOUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    
    @Autowired
    private ShopService shopService;

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
    
    /**
     *@description  查询店铺Top榜
     *@date 2016年9月18日 
     *@author Baoluo
     *@param cid
     *@return
     */
    @RequestMapping("/shop/info/getTopStore/{cid}")
    @ResponseBody
    public ResponseDataModel getTopStore(@PathVariable Long cid) {
        try {
            List<TopShopDTO> dto = shopService.getTopShops(cid);
            return success(dto);
        } catch (ParseException e) {
            return error(ApiStatusCode.BUSSINESS_EXCEPTION);
        }
    }
    
    /**
     *@description  查询所有类目
     *@date 2016年9月18日 
     *@author Baoluo
     *@return
     */
    @RequestMapping("/shop/info/getCategories")
    @ResponseBody
    public ResponseDataModel getAllCategory() {
        List<TCategory> list = shopService.getAllCategories();
        List<TCategoryDTO> result = BeanDTOUtil.copyList(list, TCategoryDTO.class);
        return success(result);
    }
    
    /**
     *@description  获取所有关注店铺
     *@date 2016年9月18日 
     *@author Baoluo
     *@param uid
     *@return
     */
    @RequestMapping("/shop/info/getAllFollows/{uid}")
    @ResponseBody
    public ResponseDataModel getAllFollows(@PathVariable String uid) {
        try {
            List<TFollowDTO> dtoList = shopService.getMyFollows(uid);
            return success(dtoList);
        } catch (ParseException e) {
            return error(ApiStatusCode.BUSSINESS_EXCEPTION);
        }
    }
    
    /**
     *@description  取消关注店铺
     *@date 2016年9月18日 
     *@author Baoluo
     *@param uid
     *@param shopid
     *@return
     */
    @RequestMapping("/shop/info/cancleFollow/{uid}/{shopid}")
    @ResponseBody
    public ResponseDataModel cancleFollow(@PathVariable String uid, @PathVariable Long shopid) {
        int result = shopService.cancleFollow(uid, shopid);
        return success(result);
    }
    
    /**
     *@description  
     *@date 2016年9月19日 
     *@author Baoluo
     *@param nick
     *@param type 1:绑定店铺  2:关注店铺
     *@return
     */
    @RequestMapping("/shop/info/bindOrFollow/{nick}/{type}")
    public ResponseDataModel bindOrFollowShop(String nick, int type) {
        return null;
    }
}
