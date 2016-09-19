package com.xingmima.dpfx.rest.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xingmima.dpfx.rest.dto.RShopDTO;
import com.xingmima.dpfx.rest.dto.TCategoryDTO;
import com.xingmima.dpfx.rest.dto.TFollowDTO;
import com.xingmima.dpfx.rest.dto.TopShopDTO;
import com.xingmima.dpfx.rest.entity.TCategory;
import com.xingmima.dpfx.rest.entity.TFollow;
import com.xingmima.dpfx.rest.entity.TShop;
import com.xingmima.dpfx.rest.response.ApiStatusCode;
import com.xingmima.dpfx.rest.response.ResponseDataModel;
import com.xingmima.dpfx.rest.service.RShopService;
import com.xingmima.dpfx.rest.service.ShopService;
import com.xingmima.dpfx.rest.service.TShopService;
import com.xingmima.dpfx.rest.util.BeanDTOUtil;

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
    
    @Autowired
    private TShopService tShopService;

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
     *@description  绑定或关注店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param nick
     *@param type 1:绑定店铺 0:关注店铺
     *@return
     */
    @RequestMapping("/shop/info/bindOrFollow/{uid}/{nick}/{isBinding}")
    @ResponseBody
    public ResponseDataModel bindOrFollowShop(@PathVariable String uid,@PathVariable String nick, @PathVariable int isBinding) {
        if(1 == isBinding) {
            // 判断账户是否已经绑定了店铺
            TFollow dbFollow = shopService.getBindingShop(uid);
            if(null != dbFollow) {
                return error(ApiStatusCode.ACCOUNT_HAS_BINDING_SHOP);
            }
        } else {
            // 判断账户关注店铺是否已经达到5个
            try {
                List<TFollowDTO> dbFollows = shopService.getMyFollows(uid);
                if(null != dbFollows && dbFollows.size() == 5) {
                    return error(ApiStatusCode.ACCOUNT_FOLLOW_SHOP_LIMIT);
                }
            } catch (ParseException e) {
                return error(ApiStatusCode.BUSSINESS_EXCEPTION);
            }
        }
        
        // 查询根据昵称查询店铺信息(完全匹配)
        TShop shop = shopService.getShopByNick(nick);
        if(null == shop) {
            // 店铺不存在，调用接口爬取店铺信息
            List<TShop> shoplist = tShopService.getTShopListFromTaobao(nick);
            if(null == shoplist || 0 == shoplist.size()) {
                // 未爬取到店铺信息，报错
                return error(ApiStatusCode.SHOP_NOT_EXIST);
            }
            
            // 使用爬取到的第一个店铺作为用户需要的店铺并且根据昵称去查数据库检查该店铺信息是否存在
            TShop tmpShop = shoplist.get(0);
            TShop dbShop = shopService.getShopByNick(tmpShop.getNick());
            if(null == dbShop) {
                // 店铺信息不存在数据库中，插入一条新记录
                shopService.insertShop(tmpShop);
                shop = tmpShop;
            } else {
                // 店铺信息存在，使用数据库中的店铺信息
                shop = dbShop;
            }
        }
        
        if(1 == isBinding) {
            // 判断店铺是否被绑定
            TFollow dbTFollow = shopService.getShopBinding(shop.getShopid());
            if(null != dbTFollow) {
                // 店铺已经被绑定
                return error(ApiStatusCode.SHOP_HAS_BINDED);
            }
        }
        
        int result = shopService.bindingOrFollowShop(uid, shop.getShopid(), isBinding);
        if(-1 == result) {
            // 已经绑定或关注了该店铺
            if (1 == isBinding) {
                return error(ApiStatusCode.HAS_BINDED_SHOP);
            }else {
                return error(ApiStatusCode.HAS_FOLLOWED_SHOP);
            }
        }
        
        return success(result);
    }
    
    /**
     *@description  判断账户是否绑定了店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@return
     */
    @RequestMapping("/shop/info/checkIsBindShop/{uid}")
    public ResponseDataModel checkIsBindShop(@PathVariable String uid) {
        TFollow follow = shopService.getBindingShop(uid);
        return success(null != follow);
    }
}
