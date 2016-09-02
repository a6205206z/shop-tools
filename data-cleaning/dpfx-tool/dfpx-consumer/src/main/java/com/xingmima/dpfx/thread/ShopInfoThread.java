package com.xingmima.dpfx.thread;

import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.kafka.KafkaProperties;
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
    //消息流
    private KafkaStream stream;
    //数据库操作
    private ShopDao db = null;

    public ShopInfoThread(KafkaStream stream) {
        this.stream = stream;
        this.db = new ShopDao();
    }

    @Override
    public void run() {
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            try {
                /*捕获异常，继续处理*/
                ShopInfo info = new ShopInfo(c.message()).call();
                DShop obj = info.handleShopInfo();
                db.insert(obj);
            } catch (Exception e) {
                log.error(KafkaProperties.TOPIC_SHOP_INFO + ":" + e.getMessage());
            }
        }
    }
}