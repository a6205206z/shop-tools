package com.xingmima.dpfx.thread;

import com.xingmima.dpfx.dao.DItemsDao;
import com.xingmima.dpfx.dao.DItemsNumDao;
import com.xingmima.dpfx.entity.DItemNum;
import com.xingmima.dpfx.entity.DItems;
import com.xingmima.dpfx.kafka.KafkaProperties;
import com.xingmima.dpfx.parser.TaobaoItemDetail;
import com.xingmima.dpfx.parser.TmallItemDetail;
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
 * @version TmallItemDetailThread, v 0.1
 * @date 2016/9/3 15:37
 */
public class TmallItemDetailThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(TmallItemDetailThread.class);
    //消息流
    private KafkaStream stream;
    private DItemsDao did;
    private DItemsNumDao dind;


    public TmallItemDetailThread(KafkaStream stream) {
        this.stream = stream;
        this.did = new DItemsDao();
        this.dind = new DItemsNumDao();
    }

    @Override
    public void run() {
        log.info("----------{}-----------@startup", KafkaProperties.TOPIC_TMALL_ITEM_DETAIL);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            MessageAndMetadata<String, String> c = it.next();
            /*捕获异常，继续处理*/
            TmallItemDetail info = new TmallItemDetail(c.message()).call();
            if (null != info) {
                try {
                    DItems obj = info.handelItemInfo();
                    if (null != obj) {
                        did.insert(obj);

                        DItemNum diobj = info.handelItemNum(obj.getNumiid());
                        dind.insert(diobj);
                    }
                } catch (Exception e) {
                    log.error(KafkaProperties.TOPIC_TMALL_ITEM_DETAIL + ":", e);
                }
            } else {
                log.error("null========================{}", c.message());
            }
        }
    }
}