package com.xmm.shoptools.stats;

import com.xmm.shoptools.stats.entity.Product;
import com.xmm.shoptools.stats.loader.ProductDataLoader;

import java.util.List;

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
        ProductDataLoader loader = new ProductDataLoader();
        List<Product> list = loader.LoadProducts(0);
    }
}
