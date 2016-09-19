package com.xingmima.dpfx.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xingmima.dpfx.rest.dto.TFollowDTO;
import com.xingmima.dpfx.rest.dto.TopShopDTO;
import com.xingmima.dpfx.rest.entity.TCategory;
import com.xingmima.dpfx.rest.entity.TFollow;
import com.xingmima.dpfx.rest.entity.TShop;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Baoluo
 * @date 2016年9月18日 下午5:52:44
 * @version ShopDao.java, v 0.1
 *
 */
public interface StoreManageDao {
    /**
     * 查询热门店铺top10
     *
     * @param date
     * @param shopid
     * @return
     */
    @Select("SELECT ri.shopid, ts.store_url, ds.title, SUM(ri.total_sales) AS 'totalSales', SUM(ri.sold_total_count) AS 'soldTotalCount', SUM(ri.i_pv) AS 'iPv', SUM(ri.i_share_num) AS 'iShareNum', SUM(ri.i_favorite_num) AS 'iFavoriteNum' FROM r_items ri LEFT JOIN t_shop ts ON ri.shopid = ts.shopid LEFT JOIN d_shop ds ON ts.shopid = ds.shopid WHERE ri.date = #{date} AND ts.cid = #{cid} AND ds.date = #{date} GROUP BY ts.shopid ORDER BY SUM(ri.total_sales) DESC LIMIT 10")
    List<TopShopDTO> getTopShops(@Param("cid") Long cid, @Param("date") Integer date);
    
    /**
     *@description  查询所有类目
     *@date 2016年9月18日 
     *@author Baoluo
     *@return
     */
    @Select("SELECT id, pid, title, updated FROM t_category LIMIT 50")
    @Options(useCache = true, timeout = 10000, flushCache = false)
    List<TCategory> getAllCategories();
    
    /**
     *@description  查询关注的店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param date
     *@param uid
     *@return
     */
    @Select("SELECT tf.id, tf.uid, tf.shopid, ts.logo_url as logoUrl, ds.title FROM t_follow tf LEFT JOIN t_shop ts ON ts.shopid = tf.shopid LEFT JOIN d_shop ds ON ds.shopid = ts.shopid WHERE ds.date = #{date} AND tf.is_binding = 1 AND tf.status = 0 AND uid = #{uid}")
    List<TFollowDTO> getMyFollows(@Param("date") Integer date, @Param("uid") String uid);
    
    /**
     *@description  查询绑定或关注信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@param shopid
     *@param isBinding
     *@return
     */
    @Select("select id, uid, shopid, is_binding as isBinding, status, updated from t_follow where uid = #{uid} and shopid = #{shopid} and is_binding = #{isBinding} and status = 1")
    TFollow getBindOrFollow(@Param("uid")String uid, @Param("shopid")Long shopid, @Param("isBinding")Integer isBinding);
    
    /**
     *@description  取消关注
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@param shopid
     *@return
     */
    @Update("update t_follow set status = 0 where uid = #{uid} and shopid = #{shopid} and status = 1 and is_binding = 0")
    int cancleFollowShop(@Param("uid")String uid, @Param("shopid")Long shopid);
    
    /**
     *@description  根据昵称查询店铺信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param nick
     *@return
     */
    @Select("SELECT id, shopid, seller_id as sellerId, nick FROM t_shop WHERE nick = #{nick}")
    TShop getShopByNick(@Param("nick")String nick);
    
    /**
     *@description  获取用户绑定的店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param uid
     *@return
     */
    @Select("select id, uid, shopid, is_binding as isBinding, status, updated from t_follow where uid = #{uid} and is_binding = 1 and status = 1")
    TFollow getBindingShop(@Param("uid") String uid);
    
    /**
     *@description  获取店铺绑定信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param shopid
     *@return
     */
    @Select("select id, uid, shopid, is_binding as isBinding, status, updated from t_follow where shopid = #{shopid} and is_binding = 1 and status = 1")
    TFollow getShopBinding(@Param("shopid") Long shopid);
    
    /**
     *@description  添加店铺信息
     *@date 2016年9月19日 
     *@author Baoluo
     *@param shop
     *@return
     */
    @Insert("INSERT INTO `t_shop` VALUES (#{id}, #{shopid}, #{sellerId}, #{securityId}, #{nick}, #{cid}, #{tCid}, #{storeUrl}, #{logoUrl}, #{category}, #{type}, #{location}, #{serviceNumber}, #{storeDate}, #{status}, #{lastTimes}, now(), now())")
    int insertShopInfo(TShop shop);
    
    /**
     *@description  绑定或关注店铺
     *@date 2016年9月19日 
     *@author Baoluo
     *@param tFollow
     *@return
     */
    @Insert("insert into t_follow values (#{id}, #{uid}, #{shopid}, #{isBinding}, #{status}, now())")
    int bindOrFollowShop(TFollow tFollow);
}