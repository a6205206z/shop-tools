package com.xingmima.dpfx.dao;

import com.xingmima.dpfx.db.Dbutils;
import com.xingmima.dpfx.entity.DItems;

import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DTejiaItemsDao, v 0.1
 * @date 2016/9/6 14:22
 */
public class DTejiaItemsDao {
    private static final String insertSql = "INSERT INTO `d_tejia_items` (`id`,`date`,`shopid`,`numiid`,`title`,`item_url`,`pic_url`,`rcid`,`cid`,`publish_category`,`marker_price`,`price`,`promo_price`,`post_fee`,`sold_total_count`,`confirm_goods_count`,`total_rated_count`,`total_sales`,`stock`,`sellable_quantity`,`sku_stock`,`brand_name`,`list_time`,`delist_time`,`add_time`,`is_delisting`,`rated`,`updated`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public void insert(DItems obj) throws SQLException {
        Dbutils.update(insertSql, new Object[]{
                obj.getId(), obj.getDate(), obj.getShopid(), obj.getNumiid(),
                obj.getTitle(), obj.getItemUrl(), obj.getPicUrl(), obj.getRcid(), obj.getCid(), obj.getPublishCategory(),
                obj.getMarkerPrice(), obj.getPrice(), obj.getPromoPrice(), obj.getPostFee(),
                obj.getSoldTotalCount(), obj.getConfirmGoodsCount(),
                obj.getTotalRatedCount(), obj.getTotalSales(), obj.getStock(), obj.getSellableQuantity(), obj.getSkuStock(), obj.getBrandName(),
                obj.getListTime(), obj.getDelistTime(), obj.getAddTime(), obj.getIsDelisting(), obj.getRated(),
                obj.getUpdated()
        });
    }
}
