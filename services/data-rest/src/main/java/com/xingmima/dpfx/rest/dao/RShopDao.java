package com.xingmima.dpfx.rest.dao;

import com.xingmima.dpfx.rest.entity.RShop;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RShopDao, v 0.1
 * @date 2016/9/13 19:43
 */
public interface RShopDao {

    /**
     * 查询店铺统计信息
     *
     * @param date
     * @param shopid
     * @return
     */
    @Select("SELECT `id`, `date`, `shopid`, `sale_goods_num`,`on_goods_num`, `off_goods_num`, `favorite_num`, `i_favorite_num`, `i_share_num`, `total_pv`, `total_wt_fans`, `created` FROM `r_shop` WHERE `date` = #{date} AND shopid = #{shopid}")
    @Options(useCache = true, timeout = 10000, flushCache = false)
    RShop getRShopByShop(@Param("shopid") Long shopid, @Param("date") Integer date);
    
}
