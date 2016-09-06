package com.xingmima.dpfx.thread;

import com.xingmima.dpfx.dao.DdsrDao;
import com.xingmima.dpfx.dao.RateDao;
import com.xingmima.dpfx.dao.ShopDao;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DRated;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.kafka.KafkaProperties;
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
 * @date 2016/8/31 20:37
 */
public class TaobaoShopInfoThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(TaobaoShopInfoThread.class);
    //消息流
    private KafkaStream stream;
    //数据库操作
    private ShopDao sd = null;
    private RateDao rd = null;
    private DdsrDao dd = null;

    public TaobaoShopInfoThread(KafkaStream stream) {
        this.stream = stream;
        this.sd = new ShopDao();
        this.rd = new RateDao();
        this.dd = new DdsrDao();
    }

    @Override
    public void run() {
        log.info("----------{}-----------@startup", KafkaProperties.TOPIC_SHOP_INFO);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            /*捕获异常，继续处理*/
            TaobaoShopInfo info = new TaobaoShopInfo(c.message()).call();
            if (null != info) {
                try {
                    DShop obj = info.handleShopInfo();
                    if (null != obj)
                        sd.insert(obj);
                } catch (Exception e) {
                    log.error(KafkaProperties.TOPIC_SHOP_INFO + ":", e);
                }
                try {
                    DRated obj = info.handleRating();
                    if (null != obj)
                        rd.insert(obj);
                } catch (Exception e) {
                    log.error(KafkaProperties.TOPIC_SHOP_INFO + ":", e);
                }
                try {
                    DDsr obj = info.handelDsr();
                    if (null != obj)
                        dd.insert(obj);
                } catch (Exception e) {
                    log.error(KafkaProperties.TOPIC_SHOP_INFO + ":", e);
                }
            }
        }
    }
}