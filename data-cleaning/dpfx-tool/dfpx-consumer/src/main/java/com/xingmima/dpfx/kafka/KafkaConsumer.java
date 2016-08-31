package com.xingmima.dpfx.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version Consumer, v 0.1
 * @date 2016/8/31 14:20
 */
public class KafkaConsumer {
    protected final ConsumerConnector consumer;

    protected KafkaConsumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", KafkaProperties.ZOOKEEPER_CONNECT);
        //group 代表一个消费组
        props.put("group.id", KafkaProperties.GROUP_ID);
        //zk连接超时
        props.put("zookeeper.session.timeout.ms", KafkaProperties.ZOOKEEPER_SESSION_TIMEOUT_MS);
        /**
         * ZooKeeper集群中leader和follower之间的同步实际
         */
        props.put("zookeeper.sync.time.ms", KafkaProperties.ZOOKEEPER_SYNC_TIME_MS);
        /**
         * 自动提交的时间间隔
         */
        props.put("auto.commit.interval.ms", KafkaProperties.AUTO_COMMIT_INTERVAL_MS);

        /**
         当消息被消费时,会想zk提交当前groupId的consumer消费的offset信息,当consumer再次启动将会从此offset开始继续消费.
         在consumter端配置文件中(或者是ConsumerConfig类参数) 有个"autooffset.reset"(在kafka 0.8版本中为auto.offset.reset),
         有2个合法的值"largest"/"smallest",默认为"largest",此配置参数表示当此groupId下的消费者,在ZK中没有offset值时(比如新的groupId,或者是zk数据被清空),
         consumer应该从哪个offset开始消费.largest表示接受接收最大的offset(即最新消息),smallest表示最小offset,即从topic的开始位置消费所有消息.
         */
        props.put("auto.offset.reset", KafkaProperties.AUTO_OFFSET_RESET);
        //序列化类
        props.put("serializer.class", KafkaProperties.SERIALIZER_CLASS);
        ConsumerConfig config = new ConsumerConfig(props);
        //实例化消费者连接器
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    /**
     * 创建消息流
     *
     * @param topic
     * @param numThreads
     * @return
     */
    public Map<String, List<KafkaStream<String, String>>> createMessageStreams(String topic, Integer numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, numThreads);

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        return consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
    }
}