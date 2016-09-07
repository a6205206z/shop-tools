package com.xingmima.dpfx.client;

import com.xingmima.dpfx.kafka.KafkaConsumer;
import com.xingmima.dpfx.kafka.KafkaProperties;
import com.xingmima.dpfx.thread.TmallItemDetailThread;
import kafka.consumer.KafkaStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 淘宝商品详情页面
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TmallItemConsumer, v 0.1
 * @date 2016/9/3 15:35
 */
public class TmallItemConsumer extends KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(TmallItemConsumer.class);

    private ExecutorService executor = null;

    public TmallItemConsumer() {
        super();
    }

    public void consumer() {
        Map<String, List<KafkaStream<String, String>>> consumerMap = this.createMessageStreams(KafkaProperties.TOPIC_TMALL_ITEM_DETAIL, KafkaProperties.TOPIC_TMALL_ITEM_DETAIL_THREADS);

        executor = Executors.newFixedThreadPool(KafkaProperties.TOPIC_TMALL_ITEM_DETAIL_THREADS);

        List<KafkaStream<String, String>> streams = consumerMap.get(KafkaProperties.TOPIC_TMALL_ITEM_DETAIL);
        for (final KafkaStream stream : streams) {
            executor.submit(new TmallItemDetailThread(stream));
        }
    }
}
