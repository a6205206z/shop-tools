package com.xingmima.dpfx.thread;

import com.xingmima.dpfx.dao.DdsrDao;
import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.kafka.KafkaProperties;
import com.xingmima.dpfx.parser.TmallShopInfo;
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
 * @version TmallShopInfoThread, v 0.1
 * @date 2016/8/31 20:37
 */
public class TmallShopInfoThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(TmallShopInfoThread.class);
    //消息流
    private KafkaStream stream;
    //数据库操作
    private ShopDao sd = null;
    private DdsrDao dd = null;

    public TmallShopInfoThread(KafkaStream stream) {
        this.stream = stream;
        this.sd = new ShopDao();
        this.dd = new DdsrDao();
    }

    @Override
    public void run() {
        log.info("----------{}-----------@startup", KafkaProperties.TOPIC_TMALL_SHOP_INFO);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            /*捕获异常，继续处理*/
            TmallShopInfo info = new TmallShopInfo(c.message()).call();
            if (null != info) {
                try {
                    DShop obj = info.handleShopInfo();
                    if (null != obj)
                        sd.insert(obj);
                } catch (Exception e) {
                    log.error(KafkaProperties.TOPIC_TMALL_SHOP_INFO + ":", e);
                }

                try {
                    DDsr obj = info.handelDsr();
                    if (null != obj)
                        dd.insert(obj);
                } catch (Exception e) {
                    log.error(KafkaProperties.TOPIC_TMALL_SHOP_INFO + ":", e);
                }
            }
        }
    }
}