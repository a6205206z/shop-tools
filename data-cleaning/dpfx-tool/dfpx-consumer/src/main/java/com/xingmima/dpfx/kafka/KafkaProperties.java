package com.xingmima.dpfx.kafka;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version Consumer, v 0.1
 * @date 2016/8/31 17:40
 */
public class KafkaProperties {

    public static final String GROUP_ID = "xmm-group";

    public static final String ZOOKEEPER_CONNECT = "192.168.103.245:2181";
    public static final String ZOOKEEPER_SESSION_TIMEOUT_MS = "4000";
    public static final String ZOOKEEPER_SYNC_TIME_MS = "200";
    public static final String AUTO_COMMIT_INTERVAL_MS = "1000";

    public static final String AUTO_OFFSET_RESET = "smallest";

    public static final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";

    //店铺信用通道
    public static final String TOPIC_SHOP_INFO = "taobao.shop.info";
    public static final Integer TOPIC_SHOP_INFO_THREADS = 1;

    public static final String TOPIC_ITEM_DETAIL = "taobao.shop.product";
    public static final Integer TOPIC_ITEM_DETAIL_THREADS = 1;

    private KafkaProperties() {
    }
}
