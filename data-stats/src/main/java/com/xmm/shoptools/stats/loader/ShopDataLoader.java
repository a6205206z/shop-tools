package com.xmm.shoptools.stats.loader;

import com.xmm.shoptools.stats.db.Dbutils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats.loader, v 0.1
 * @date 16 /9/8.
 */
public class ShopDataLoader {

    private static final Logger log = LoggerFactory.getLogger(ShopDataLoader.class);

    //按天加载店铺商品SQL
    private static final String item_sql = "SELECT `numiid`,`is_delisting` FROM `d_items` a,(SELECT id FROM `d_items` WHERE `date` = ? AND shopid = ? LIMIT ?,?) b WHERE a.id = b.id;";
    private static final String total_favorite = "SELECT MAX(`s_favorite`) FROM `d_item_num` WHERE `date` = ? AND shopid = ?";
    private static final String total_item_d = "SELECT SUM(`i_favorite_num`) AS i_favorite_num, SUM(`i_share_num`) AS i_share_num, SUM(`i_pv`) AS i_pv FROM `r_items` WHERE `date` = ? AND shopid = ?";
    private static final String total_fans = "SELECT `weitao_fans` FROM `d_fans` WHERE `date` = ? AND shopid = ?";
    private static final String insert_sql = "INSERT INTO `r_shop` (`id`,`date`,`shopid`,`sale_goods_num`,`on_goods_num`,`off_goods_num`,`favorite_num`,`i_favorite_num`,`i_share_num`,`total_pv`,`total_wt_fans`,`created`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final int MAX_LIMIT = 1000;
    private static final String all_shop = "SELECT `shopid`,`store_url` FROM `t_shop` WHERE STATUS = 0";

    /**
     * Gets item shop.
     *
     * @return the item shop
     */
    public List<Map> getAllShop() {
        try {
            return Dbutils.maplist(all_shop, new Object[]{});
        } catch (SQLException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * Gets item all.
     *
     * @param date   the date
     * @param shopId the shop id
     * @return the item all
     */
    public Map<Long, Boolean> getItemAll(Integer date, Long shopId) {
        Map<Long, Boolean> data = new HashMap<>();
        int p = 0;
        while (true) {
            List<Map> list = this.getItemByDate(date, shopId, p);
            if (null != list && list.size() > 0) {
                log.debug("date:{}===shop:{}===cnt:{}", date, shopId, list.size());
                for (Map map : list) {
                    data.put((Long) map.get("numiid"), (Boolean) map.get("is_delisting"));
                }
                p++;
            } else {
                break;
            }
        }
        return data;
    }

    /**
     * 按天查询店铺商品
     *
     * @param date   the date
     * @param shopId the shop id
     * @param page   the page
     * @return item by date
     */
    public List<Map> getItemByDate(Integer date, Long shopId, int page) {
        try {
            return Dbutils.maplist(item_sql, new Object[]{date, shopId, page * MAX_LIMIT, MAX_LIMIT});
        } catch (SQLException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * Cnt max favorite long.
     *
     * @param date   the date
     * @param shopId the shop id
     * @return the long
     */
    public Long cntMaxFavorite(Integer date, Long shopId) {
        try {
            return Dbutils.cnt(total_favorite, new Object[]{date, shopId});
        } catch (SQLException e) {
            log.error("", e);
        }
        return 0L;
    }

    /**
     * Gets stats item.
     *
     * @param date   the date
     * @param shopId the shop id
     * @return the stats item
     */
    public Map getStatsItem(Integer date, Long shopId) {
        try {
            return Dbutils.map(total_item_d, new Object[]{date, shopId});
        } catch (SQLException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * Cnt total fans long.
     *
     * @param date   the date
     * @param shopId the shop id
     * @return the long
     */
    public Long cntTotalFans(Integer date, Long shopId) {
        try {
            Long cnt = Dbutils.cnt(total_fans, new Object[]{date, shopId});
            if (null != null) {
                return cnt;
            }
        } catch (SQLException e) {
            log.error("", e);
        }
        return 0L;
    }

    /**
     * Insert.
     *
     * @param uuid           the uuid
     * @param date           the date
     * @param shopid         the shopid
     * @param sale_goods_num the sale goods num
     * @param on_goods_num   the on goods num
     * @param off_goods_num  the off goods num
     * @param favorite_num   the favorite num
     * @param i_favorite_num the favorite num
     * @param i_share_num    the share num
     * @param total_pv       the total pv
     * @param total_wt_fans  the total wt fans
     */
    public void insert(String uuid, Integer date, Long shopid, Integer sale_goods_num, Integer on_goods_num, Integer off_goods_num, Long favorite_num, BigDecimal i_favorite_num, BigDecimal i_share_num, BigDecimal total_pv, Long total_wt_fans) {
        try {
            Dbutils.update(insert_sql, new Object[]{
                    uuid, date, shopid,
                    sale_goods_num, on_goods_num, off_goods_num, favorite_num, i_favorite_num, i_share_num,
                    total_pv, total_wt_fans, new Date()
            });
        } catch (SQLException e) {
            log.error("", e);
        }
    }
}
