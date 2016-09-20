package com.xingmima.dpfx;

import com.xingmima.dpfx.client.*;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DpfxMain, v 0.1
 * @date 2016/8/31 14:13
 */
public class DpfxMain {

    public static void main(String[] args) {
        new TaobaoShopInfoConsumer().consumer();
        new TaobaoItemConsumer().consumer();
        new TmallShopInfoConsumer().consumer();
        new TmallItemConsumer().consumer();
        new TejiaItemConsumer().consumer();
    }
}
