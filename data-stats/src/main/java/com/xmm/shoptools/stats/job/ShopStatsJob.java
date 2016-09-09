package com.xmm.shoptools.stats.job;

import com.xmm.shoptools.stats.Helper;
import com.xmm.shoptools.stats.loader.ShopDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopStatsJob, v 0.1
 * @date 2016/9/9 10:22
 */
public class ShopStatsJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(ShopStatsJob.class);

    private ShopDataLoader loader = new ShopDataLoader();

    @Override
    public void begin() {
        List<Map> list = loader.getAllShop();
        if (null != list && list.size() > 0) {
            for (Map map : list) {
                this.statsShopData((Long) map.get("shopid"), (String) map.get("store_url"));
            }
        }
    }

    public void statsShopData(Long shopId, String storeUrl) {
        Integer date = Integer.parseInt(Helper.getTodayAsSecond());
        Integer yesdate = Integer.parseInt(Helper.getTodayAsSecond(-1));

        log.info("stats shop data==={},{},{},{}", date, yesdate, shopId, storeUrl);

         /*统计上下架情况*/
        Integer sale_goods_num = 0, on_goods_num = 0, off_goods_num = 0;

        Map<Long, Boolean> yestday = loader.getItemAll(yesdate, shopId);
        Map<Long, Boolean> today = loader.getItemAll(date, shopId);
        boolean exists = yestday.isEmpty();
        if (!today.isEmpty()) {
            for (Map.Entry<Long, Boolean> entry : today.entrySet()) {
                if (!entry.getValue()) {
                    sale_goods_num += 1;
                    //新上架
                    if (!yestday.containsKey(entry.getKey()) && !exists) {
                        on_goods_num += 1;
                    }
                } else {
                    off_goods_num += 1;
                }
            }
            log.debug("sale_goods_num = " + sale_goods_num + ", on_goods_num = " + on_goods_num + ", off_goods_num = " + off_goods_num);
            today.clear();
            yestday.clear();
        }

        //商品维度报表数据
        Map map = loader.getStatsItem(date, shopId);
        if (map != null) {
            log.debug("getstatsitem:{}", map.toString());
        } else {
            log.error("stats item error! {},{} ", date, yesdate, shopId);
            return;
        }

        //店铺收藏总量
        Long favorite_num = loader.cntMaxFavorite(date, shopId);
        log.debug("favorite_num:{}", favorite_num.toString());


        Long total_wt_fans = loader.cntTotalFans(date, shopId);
        log.debug("total_wt_fans:{}", total_wt_fans.toString());

        this.loader.insert(Helper.getGuid32(), date, shopId, sale_goods_num, on_goods_num, off_goods_num
                , favorite_num, (BigDecimal) map.get("i_favorite_num"), (BigDecimal) map.get("i_share_num"), (BigDecimal) map.get("i_pv")
                , total_wt_fans);

        log.info("stats success@{}#{}", shopId, date);
    }

}
