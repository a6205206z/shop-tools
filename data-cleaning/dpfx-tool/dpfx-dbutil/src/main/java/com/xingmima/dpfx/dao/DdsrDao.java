package com.xingmima.dpfx.dao;

import com.xingmima.dpfx.db.Dbutils;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DShop;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DdsrDao, v 0.1
 * @date 2016/9/1 15:22
 */
public class DdsrDao {
    private static final String insertSql = "INSERT INTO `d_dsr` (`id`,`date`,`shopid`,`detail`,`d_css`,`d_hy`,`d_json`,`seller`,`s_css`,`s_hy`,`s_json`,`rating`,`r_css`,`r_hy`,`r_json`,`created`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void insert(DDsr obj) throws SQLException {
        Dbutils.update(insertSql, new Object[]{
                obj.getId(), obj.getDate(), obj.getShopid(),
                obj.getDetail(), obj.getdCss(), obj.getdHy(), obj.getdJson(),
                obj.getSeller(), obj.getsCss(), obj.getsHy(), obj.getsJson(),
                obj.getRating(), obj.getrCss(), obj.getrHy(), obj.getrJson(),
                obj.getCreated()
        });
    }
}
