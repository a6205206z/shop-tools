package com.xmm.shoptools.stats;

import com.xmm.shoptools.stats.job.ProductStatsJob;
import com.xmm.shoptools.stats.job.ShopStatsJob;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats, v 0.1
 * @date 16/9/8.
 */
public class Bootstrap {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]){
        ProductStatsJob productjob = new ProductStatsJob();
        productjob.begin();
        ShopStatsJob shopjob = new ShopStatsJob();
        shopjob.begin();
    }
}
