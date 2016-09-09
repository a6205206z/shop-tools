package com.xmm.shoptools.stats.loader;

import com.xmm.shoptools.stats.db.Dbutils;
import com.xmm.shoptools.stats.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats.loader, v 0.1
 * @date 16 /9/8.
 */
public class ProductDataLoader {
    private static final Logger log = LoggerFactory.getLogger(ProductDataLoader.class);

    private final String SELECT_PRODUCTS="SELECT i.date,i.shopid,i.numiid,i.title,i.item_url,i.pic_url,i.rcid,i.cid,i.marker_price,i.price,i.post_fee,i.sold_total_count,i.confirm_goods_count,i.total_rated_count,i.total_sales,i.stock,i.sellable_quantity,i.brand_name,i.list_time,i.delist_time,i.add_time,i.is_delisting,i.rated,i.updated,inum.i_favorite_num,inum.i_share_num,inum.i_pv FROM d_items as i LEFT JOIN d_item_num as inum on i.date=inum.date and i.numiid=inum.numiid WHERE i.date = unix_timestamp(date_sub(curdate(),interval ? day))";

    /**
     * Load products list.
     *
     * @param daysago the daysago
     * @return the list
     */
    public List<Product> LoadProducts(int daysago){

        List<Product> result = null;
        try {
            result = Dbutils.query(Product.class, SELECT_PRODUCTS, new Object[]{daysago});
        } catch (SQLException e) {
            log.error(e.toString());
        }

        return result;
    }

}
