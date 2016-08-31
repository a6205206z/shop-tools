package com.xingmima.dpfx.thread;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public ShopInfoThread(KafkaStream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        ConsumerIterator<String, String> it = stream.iterator();
        System.out.println("start....");
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            System.out.println(c.message());
        }
    }
}
