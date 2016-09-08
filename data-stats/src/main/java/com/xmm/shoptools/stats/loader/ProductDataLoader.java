package com.xmm.shoptools.stats.loader;

import com.xmm.shoptools.stats.db.Dbutils;
import com.xmm.shoptools.stats.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
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

    private final String SELECT_PRODUCTS="SELECT date,shopid,numiid,title,item_url,pic_url,rcid,cid,marker_price,price,post_fee,sold_total_count,confirm_goods_count,total_rated_count,total_sales,stock,sellable_quantity,brand_name,list_time,delist_time,add_time,is_delisting,rated,updated FROM d_items WHERE date = unix_timestamp(date_sub(curdate(),interval ? day))";

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
