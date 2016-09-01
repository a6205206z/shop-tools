package com.xingmima.dpfx.dao;

import com.xingmima.dpfx.db.Dbutils;
import com.xingmima.dpfx.entity.DShop;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Date;
import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopDao, v 0.1
 * @date 2016/9/1 15:22
 */
public class ShopDao {
    private static final String insertSql = "INSERT INTO `d_shop` (`id`,`date`,`shopid`,`title`,`credit_score`,`credit_level`,`credit_total_num`,`credit_good_num`,`rating`,`updated`) VALUES(?,?,?,?,?,?,?,?,?,?)";

    public void insert(DShop obj) throws SQLException {
        Dbutils.update(insertSql, new Object[]{
                obj.getId(),
                obj.getDate(), obj.getShopid(), obj.getTitle(), obj.getCreditScore(),
                obj.getCreditLevel(), obj.getCreditTotalNum(), obj.getCreditGoodNum(), obj.getRating(), obj.getUpdated()
        });
    }
}
