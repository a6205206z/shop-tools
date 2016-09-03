package com.xingmima.dpfx.thread;

import com.xingmima.dpfx.dao.DdsrDao;
import com.xingmima.dpfx.dao.RateDao;
import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DRated;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.kafka.KafkaProperties;
import com.xingmima.dpfx.parser.TaobaoItemDetail;
import com.xingmima.dpfx.parser.TaobaoShopInfo;
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
 * @version TaobaoShopInfoThread, v 0.1
 * @date 2016/9/3 15:37
 */
public class TaobaoItemDetailThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(TaobaoItemDetailThread.class);
    //消息流
    private KafkaStream stream;


    public TaobaoItemDetailThread(KafkaStream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            log.info("----------{}-----------@startup", KafkaProperties.TOPIC_SHOP_INFO);
            /*捕获异常，继续处理*/
            TaobaoItemDetail info = new TaobaoItemDetail(c.message()).call();
            if (null != info) {
//                try {
//                    log.info("handle shop info----------");
//                    DShop obj = info.handleShopInfo();
//                    if (null != obj)
//                        sd.insert(obj);
//                } catch (Exception e) {
//                    log.error(KafkaProperties.TOPIC_SHOP_INFO + ":", e);
//                }
            }
        }
    }
}