package com.xingmima.dpfx.dao;

import com.xingmima.dpfx.db.Dbutils;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DItems;

import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DItemsDao, v 0.1
 * @date 2016/9/6 14:22
 */
public class DItemsDao {
    private static final String insertSql = "INSERT INTO `d_items` (`id`,`date`,`numiid`,`title`,`item_url`,`pic_url`,`rcid`,`cid`,`marker_price`,`price`,`post_fee`,`promo_info`,`sold_total_count`,`confirm_goods_count`,`total_rated_count`,`total_sales`,`stock`,`sellableQuantity`,`sku_stock`,`list_time`,`delist_time`,`add_time`,`is_delisting`,`rated`,`updated`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void insert(DItems obj) throws SQLException {
        Dbutils.update(insertSql, new Object[]{
                obj.getId(), obj.getDate(), obj.getNumiid(),
                obj.getTitle(), obj.getItemUrl(), obj.getPicUrl(), obj.getRcid(), obj.getCid(),
                obj.getMarkerPrice(), obj.getPrice(), obj.getPostFee(),
                obj.getPromoInfo(), obj.getSoldTotalCount(), obj.getConfirmGoodsCount(),
                obj.getTotalRatedCount(), obj.getTotalSales(), obj.getStock(), obj.getSellablequantity(), obj.getSkuStock(),
                obj.getListTime(), obj.getDelistTime(), obj.getAddTime(), obj.getIsDelisting(), obj.getRated(),
                obj.getUpdated()
        });
    }
}
