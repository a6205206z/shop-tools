package com.xingmima.dpfx.rest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xingmima.dpfx.rest.dao.StoreManageDao;
import com.xingmima.dpfx.rest.dto.TFollowDTO;
import com.xingmima.dpfx.rest.dto.TopShopDTO;
import com.xingmima.dpfx.rest.entity.TCategory;
import com.xingmima.dpfx.rest.entity.TFollow;
import com.xingmima.dpfx.rest.entity.TShop;
import com.xingmima.dpfx.rest.util.Helper;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Baoluo
 * @date 2016年9月18日 下午6:46:32
 * @version ShopService.java, v 0.1
 *
 */
@Service
public class StoreManageService {
    private static final Logger LOGGER =LoggerFactory.getLogger(StoreManageService.class);
    
    @Autowired
    private StoreManageDao dao;
    
    /**
     *@description  获取top10店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param cid
     *@return
     *@throws ParseException
     */
    public List<TopShopDTO> getTopShops(Long cid) throws ParseException {
        LOGGER.info("Get in the method : getRShopByShop, param : {}", cid);
        int yesterDay = getYesterdayTime();
        LOGGER.info("Get the Time: {}", yesterDay);
        List<TopShopDTO> dtoList = dao.getTopShops(cid, yesterDay);
        LOGGER.info("Get out the method : getRShopByShop, dto : {}", dtoList);
        return dtoList;
    }
    
    /**
     *@description  获取所有一级类目
     *@date 2016年9月19日 
     *@author Baoluo
     *@return
     */
    @Cacheable(value = "shop:allCategories")
    public List<TCategory> getAllCategories() {
        LOGGER.info("Get in the method : getAllCategories");
        List<TCategory> dtoList = dao.getAllCategories();
        LOGGER.info("Get out the method : getAllCategories, dto : {}", dtoList);
        return dtoList;
    }
    
    /**
     *@description  获取所有我关注的店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@return
     *@throws ParseException
     */
    public List<TFollowDTO> getMyFollows(String uid) throws ParseException {
        LOGGER.info("Get in the method : getMyFollows , uid : {}", uid);
        int yesterday = getYesterdayTime();
        LOGGER.info("Get the time : {}", yesterday);
        List<TFollowDTO> dtoList = dao.getMyFollows(yesterday, uid);
        return dtoList;
    }
    
    /**
     *@description  取消关注
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@param shopid
     *@return
     */
    public int cancleFollow(String uid, Long shopid) {
        return dao.cancleFollowShop(uid, shopid);
    }
    
    /**
     *@description  根据昵称获取店铺信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param nick
     *@return
     */
    public TShop getShopByNick(String nick) {
        return dao.getShopByNick(nick);
    }
    
    /**
     *@description  添加店铺信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param shop
     *@return
     */
    public int insertShop(TShop shop) {
        return dao.insertShopInfo(shop);
    }
    
    /**
     *@description  绑定或关注店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@param shopid
     *@param isBinding
     *@return
     */
    public int bindingOrFollowShop(String uid, Long shopid, int isBinding) {
        TFollow dbFollow = dao.getBindOrFollow(uid, shopid, isBinding);
        if(null != dbFollow) {
            // 已经绑定或关注
            return -1;
        }
        TFollow follow = new TFollow();
        follow.setUid(uid);
        follow.setShopid(shopid);
        follow.setStatus(true);
        follow.setIsBinding(isBinding == 1 ? true : false);
        follow.setId(Helper.getGuid32());
        return dao.bindOrFollowShop(follow);
    }
    
    /**
     *@description  获取用户绑定的店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@return
     */
    public TFollow getBindingShop(String uid) {
        return dao.getBindingShop(uid);
    }
    
    /**
     *@description  获取店铺绑定信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param shopid
     *@return
     */
    public TFollow getShopBinding(Long shopid) {
        return dao.getShopBinding(shopid);
    }
    
    /**
     *@description  获取当天前一天的时间戳
     *@date 2016年9月19日 
     *@author Baoluo
     *@return
     *@throws ParseException
     */
    private int getYesterdayTime() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        Date today = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -1);
        Integer time = (int)(calendar.getTime().getTime()/1000);
        return time;
    }
}