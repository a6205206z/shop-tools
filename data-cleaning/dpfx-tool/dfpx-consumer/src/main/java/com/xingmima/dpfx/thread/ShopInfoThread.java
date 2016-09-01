package com.xingmima.dpfx.thread;

import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.parser.ShopInfo;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version ShopInfoThread, v 0.1
 * @date 2016/8/31 20:37
 */
public class ShopInfoThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ShopInfoThread.class);

    private KafkaStream stream;
    private ShopDao dao = null;

    public ShopInfoThread(KafkaStream stream) {
        this.stream = stream;
        this.dao = new ShopDao();
    }

    @Override
    public void run() {
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            System.out.println(c.message());

            ShopInfo info = new ShopInfo(c.message()).call();
            DShop obj = info.handleShopInfo();
            try {
                dao.insert(obj);
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }
}
