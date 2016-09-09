package com.xmm.shoptools.stats.job;

import com.xmm.shoptools.stats.Helper;
import com.xmm.shoptools.stats.loader.ShopDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopStatsJob, v 0.1
 * @date 2016/9/9 10:22
 */
public class ShopStatsJob {

    private static final Logger log = LoggerFactory.getLogger(ShopStatsJob.class);

    private ShopDataLoader loader = new ShopDataLoader();

    public void statsShopData(Long shopId) {
        /*统计上下架情况*/
        Integer sale_goods_num = 0, on_goods_num = 0, off_goods_num = 0;

        Integer date = Integer.parseInt(Helper.getTodayAsSecond());
        Integer yesdate = Integer.parseInt(Helper.getTodayAsSecond(-1));

        log.info("stats shop data==={},{},{}", date, yesdate, shopId);

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
            log.info("sale_goods_num = " + sale_goods_num + ", on_goods_num = " + on_goods_num + ", off_goods_num = " + off_goods_num);
            today.clear();
            yestday.clear();
        }

        //店铺收藏总量
        Long totalmf = loader.cntMaxFavorite(date, shopId);
        log.info("totalmf:{}", totalmf.toString());

        //商品维度报表数据
        Map map = loader.getStatsItem(date, shopId);
        if (map != null) {
            log.info(map.toString());
        }

        Long totalfans = loader.cntTotalFans(date, shopId);
        log.info("totalfans:{}", totalfans.toString());


    }

    public static void main(String[] args) {
        new ShopStatsJob().statsShopData(34685656L);
    }
}
