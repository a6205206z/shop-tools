package com.xmm.shoptools.stats.job;

import com.xmm.shoptools.stats.Helper;
import com.xmm.shoptools.stats.db.Dbutils;
import com.xmm.shoptools.stats.entity.Product;
import com.xmm.shoptools.stats.entity.ProductStats;
import com.xmm.shoptools.stats.loader.ProductDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats.job, v 0.1
 * @date 16/9/8.
 */
public class ProductStatsJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(ProductStatsJob.class);
    private final String INSERT_SQL = "INSERT INTO r_items (id,date,shopid,numiid,total_sales,sold_total_count,total_rated_count,stock,i_favorite_num,i_share_num,i_pv,created)VALUES(?,?,?,?,?,?,?,?,?,?,?,now())\n";

    public void begin() {
        //load products
        ProductDataLoader loader = new ProductDataLoader();
        List<Product> list_today = loader.LoadProducts(0);
        List<Product> list_yesterday = loader.LoadProducts(1);
        List<ProductStats> list_stats = stats(list_today, list_yesterday);
        save(list_stats);
    }

    private List<ProductStats> stats(List<Product> today, List<Product> yesterday) {
        List<ProductStats> result = new ArrayList<>();

        for (int i = 0; i < today.size(); ++i) {
            Product tp = today.get(i);
            Product yp = null;
            for (int j = 0; j < yesterday.size(); ++j) {
                if (today.get(i).getNumiid() == yesterday.get(j).getNumiid()) {
                    yp = yesterday.get(j);
                    break;
                }
            }
            ProductStats ps = new ProductStats();
            ps.setDate(tp.getDate());
            ps.setShopid(tp.getShopid());
            ps.setNumiid(tp.getNumiid());
            ps.setStock(tp.getStock());
            if (yp != null) {
                ps.setTotal_sales(tp.getTotal_sales() - yp.getTotal_sales());
                ps.setSold_total_count(tp.getSold_total_count() - yp.getSold_total_count());
                ps.setTotal_rated_count(tp.getTotal_rated_count() - yp.getTotal_rated_count());
                ps.setI_favorite_num(tp.getI_favorite_num() - yp.getI_favorite_num());
                ps.setI_share_num(tp.getI_share_num() - yp.getI_share_num());
                ps.setI_pv(tp.getI_pv() - yp.getI_pv());
            }

            result.add(ps);
        }
        return result;
    }

    private void save(List<ProductStats> stats) {
        for (ProductStats ps : stats) {
            try {
                Dbutils.update(INSERT_SQL, new Object[]{
                        Helper.getGuid32(),
                        ps.getDate(),
                        ps.getShopid(),
                        ps.getNumiid(),
                        ps.getTotal_sales(),
                        ps.getSold_total_count(),
                        ps.getTotal_rated_count(),
                        ps.getStock(),
                        ps.getI_favorite_num(),
                        ps.getI_share_num(),
                        ps.getI_pv()
                });
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }
}
