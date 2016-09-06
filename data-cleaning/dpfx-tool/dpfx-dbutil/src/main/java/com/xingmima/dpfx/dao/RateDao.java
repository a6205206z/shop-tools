package com.xingmima.dpfx.dao;

import com.xingmima.dpfx.db.Dbutils;
import com.xingmima.dpfx.entity.DRated;
import com.xingmima.dpfx.entity.DShop;

import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RateDao, v 0.1
 * @date 2016/9/1 15:22
 */
public class RateDao {
    private static final String insertSql = "INSERT INTO `d_rated` (`id`,`date`,`shopid`,`week_good`,`week_neutral`,`week_bad`,`month_good`,`month_neutral`,`month_bad`,`halfyear_good`,`halfyear_neutral`,`halfyear_bad`,`ago_good`,`ago_neutral`,`ago_bad`,`rating`, `updated`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void insert(DRated obj) throws SQLException {
        Dbutils.update(insertSql, new Object[]{
                obj.getId(),
                obj.getDate(), obj.getShopid(),
                obj.getWeekGood(), obj.getWeekNeutral(), obj.getWeekBad(),
                obj.getMonthGood(), obj.getMonthNeutral(), obj.getMonthBad(),
                obj.getHalfyearGood(), obj.getHalfyearNeutral(), obj.getHalfyearBad(),
                obj.getAgoGood(), obj.getAgoNeutral(), obj.getAgoBad(),
                obj.getRating(), obj.getUpdated()
        });
    }
}
