package com.xingmima.dpfx.client;

import com.xingmima.dpfx.kafka.KafkaConsumer;
import com.xingmima.dpfx.kafka.KafkaProperties;
import com.xingmima.dpfx.thread.TaobaoShopInfoThread;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 店铺信用页
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TaobaoShopInfoConsumer, v 0.1
 * @date 2016/8/31 17:54
 */
public class TaobaoShopInfoConsumer extends KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(TaobaoShopInfoConsumer.class);

    private ExecutorService executor = null;

    public TaobaoShopInfoConsumer() {
        super();
    }

    public void consumer() {
        Map<String, List<KafkaStream<String, String>>> consumerMap = this.createMessageStreams(KafkaProperties.TOPIC_SHOP_INFO, KafkaProperties.TOPIC_SHOP_INFO_THREADS);

        executor = Executors.newFixedThreadPool(KafkaProperties.TOPIC_SHOP_INFO_THREADS);

        List<KafkaStream<String, String>> streams = consumerMap.get(KafkaProperties.TOPIC_SHOP_INFO);
        for (final KafkaStream stream : streams) {
            executor.submit(new TaobaoShopInfoThread(stream));
        }
    }
}
