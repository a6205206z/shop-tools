package com.xingmima.dpfx.dao;

import com.xingmima.dpfx.db.Dbutils;
import com.xingmima.dpfx.entity.DItemNum;
import com.xingmima.dpfx.entity.DItems;

import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DItemsNumDao, v 0.1
 * @date 2016/9/6 14:45
 */
public class DItemsNumDao {
    private static final String insertSql = "INSERT INTO `d_item_num` (`id`,`date`,`shopid`,`numiid`,`s_favorite`,`i_favorite_num`,`i_share_num`,`i_pv`,`updated`) VALUES(?,?,?,?,?,?,?,?,?)";

    public void insert(DItemNum obj) throws SQLException {
        Dbutils.update(insertSql, new Object[]{
                obj.getId(), obj.getDate(), obj.getShopid(), obj.getNumiid(),
                obj.getsFavorite(), obj.getiFavoriteNum(), obj.getiShareNum(), obj.getiPv(),
                obj.getUpdated()
        });
    }
}
