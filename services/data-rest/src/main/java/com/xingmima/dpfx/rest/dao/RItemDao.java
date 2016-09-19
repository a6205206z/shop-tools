package com.xingmima.dpfx.rest.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RItemDao, v 0.1
 * @date 2016 /9/19 19:43
 */
public interface RItemDao {

    /**
     * 查询销量统计
     *
     * @param shopid the shopid
     * @param date1  the date 1
     * @param date2  the date 2
     * @return shop sales diff
     */
    @Select("SELECT 'd1' AS k, sold_total_count, total_sales FROM `r_shop` WHERE `date` = #{date1} AND shopid = #{shopid} UNION SELECT 'd2' AS k, sold_total_count, total_sales FROM `r_shop` WHERE `date` = #{date2} AND shopid = #{shopid}")
    List<HashMap<String, Object>> getShopSalesDiff(@Param("shopid") Long shopid, @Param("date1") Integer date1, @Param("date2") Integer date2);


    /**
     * 查询店铺“时间段内”的热销宝贝
     *
     * @param shopid the shopid
     * @param date1  the date 1
     * @param date2  the date 2
     * @return the shop hot sales
     */
    @Select("SELECT c.*, a.sold_total_count, a.total_sales FROM (SELECT `numiid`, SUM(sold_total_count) sold_total_count,SUM(total_sales) total_sales FROM `r_items` WHERE `date` >= #{date1} AND `date` < #{date2} AND shopid = #{shopid} GROUP BY `numiid` ORDER BY sold_total_count DESC LIMIT 10) a LEFT JOIN (SELECT b.numiid, `title`,`item_url`,`pic_url` FROM `d_items` b WHERE b.`date` >= #{date1} AND b.`date` < #{date2} AND b.shopid = #{shopid} GROUP BY b.numiid) c ON a.numiid = c.numiid")
    List<HashMap<String, Object>> getShopHotSales(@Param("shopid") Long shopid, @Param("date1") Integer date1, @Param("date2") Integer date2);

    /**
     * 查询店铺“时间段内”的人气宝贝
     *
     * @param shopid the shopid
     * @param date1  the date 1
     * @param date2  the date 2
     * @return the shop hot sales
     */
    @Select("SELECT c.*, a.ipv, a.ifavorite FROM (SELECT `numiid`, SUM(`i_pv`) ipv, SUM(`i_favorite_num`) ifavorite FROM `r_items` WHERE `date` >= #{date1} AND `date` < #{date2} AND shopid = #{shopid} GROUP BY `numiid` ORDER BY ipv DESC LIMIT 10) a LEFT JOIN (SELECT b.numiid, `title`,`item_url`,`pic_url` FROM `d_items` b WHERE b.`date` >= #{date1} AND b.`date` < #{date2} AND b.shopid = #{shopid} GROUP BY b.numiid) c ON a.numiid = c.numiid")
    List<HashMap<String, Object>> getShopHotFavorite(@Param("shopid") Long shopid, @Param("date1") Integer date1, @Param("date2") Integer date2);

}
