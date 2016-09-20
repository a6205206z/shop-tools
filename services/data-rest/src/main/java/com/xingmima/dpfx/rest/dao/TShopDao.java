package com.xingmima.dpfx.rest.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TShopDao, v 0.1
 * @date 2016 /9/13 19:43
 */
public interface TShopDao {

    /**
     * 查询店铺基础信息
     *
     * @param shopid the shopid
     * @return r shop by shop
     */
    @Select("SELECT `id`, `shopid`, `nick`, `cid`, `store_url`, `logo_url`, `category`, `type`, `location`, `service_number` FROM `t_shop` WHERE shopid =#{shopid}")
//    @Options(useCache = true, timeout = 10000, flushCache = false)
    HashMap<String, Object> getRShopInfo(@Param("shopid") Long shopid);

    /**
     * 查询店铺变更_基础信息
     *
     * @param shopid the shopid
     * @return the d shop info
     */
    @Select("SELECT `title`, `credit_score`, `credit_level`, `credit_total_num`, `credit_good_num`, `rating`, `updated` FROM `d_shop` WHERE shopid =#{shopid} ORDER BY `date` DESC LIMIT 1")
    HashMap<String, Object> getDShopInfo(@Param("shopid") Long shopid);
}