package com.xingmima.dpfx.rest.dao;

import com.xingmima.dpfx.rest.entity.RShop;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RShopDao, v 0.1
 * @date 2016 /9/13 19:43
 */
public interface RShopDao {

    /**
     * 查询店铺统计信息
     *
     * @param shopid the shopid
     * @param date   the date
     * @return r shop by shop
     */
    @Select("SELECT `id`, `date`, `shopid`, `sale_goods_num`,`on_goods_num`, `off_goods_num`, `favorite_num`, `i_favorite_num`, `i_share_num`, `total_pv`, `total_wt_fans`, `created` FROM `r_shop` WHERE `date` = #{date} AND shopid = #{shopid}")
    //@Options(useCache = true, timeout = 10000, flushCache = false)
    RShop getRShopByShop(@Param("shopid") Long shopid, @Param("date") Integer date);

    /**
     * 查询店铺时间段间的PV汇总
     *
     * @param shopid the shopid
     * @param date1  the date 1
     * @param date2  the date 2
     * @param date3  the date 3
     * @param date4  the date 4
     * @return the shop pv
     */
    @Select("SELECT 'd1' AS k, SUM(total_pv) AS pv FROM `r_shop` WHERE `date` >= #{date1} AND `date` < #{date2} AND shopid = #{shopid} UNION SELECT 'd2' AS k, SUM(total_pv) AS pv FROM `r_shop` WHERE `date` >= #{date3} AND `date` < #{date4} AND shopid = #{shopid}")
    List<HashMap<String, Object>> getShopPvDiff(@Param("shopid") Long shopid, @Param("date1") Integer date1, @Param("date2") Integer date2, @Param("date3") Integer date3, @Param("date4") Integer date4);

    /**
     * 查询店铺时间段间的在架商品、收藏量汇总
     *
     * @param shopid the shopid
     * @param date1  the date 1
     * @param date2  the date 2
     * @return the shop sales diff
     */
    @Select("SELECT 'd1' AS k, sale_goods_num, favorite_num FROM `r_shop` WHERE `date` = #{date1} AND shopid = #{shopid} UNION SELECT 'd2' AS k, sale_goods_num, favorite_num FROM `r_shop` WHERE `date` = #{date2} AND shopid = #{shopid}")
    List<HashMap<String, Object>> getShopGoodsDiff(@Param("shopid") Long shopid, @Param("date1") Integer date1, @Param("date2") Integer date2);

}
