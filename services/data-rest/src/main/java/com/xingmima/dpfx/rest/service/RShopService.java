package com.xingmima.dpfx.rest.service;

import com.sun.deploy.util.ArrayUtil;
import com.sun.jmx.snmp.Timestamp;
import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.entity.RShop;
import com.xingmima.dpfx.rest.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RShopService, v 0.1
 * @date 2016/9/14 12:53
 */
@Service
public class RShopService {
    private static Logger log = LoggerFactory.getLogger(RShopService.class);

    @Autowired
    private RShopDao dao;

    //@Cacheable(value = "shop:bydate", key = "#root.targetClass+#root.methodName+#root.args")
    @Cacheable(value = "shop:bydate")
    public RShop getRShopByShop(Long shopid, Integer date) {
        log.error("error...");
        log.info("info...");
        log.debug("debug...");
        return dao.getRShopByShop(shopid, date);
    }

    public void getShopPv(Long shopid, String type) {
        /*默认昨天*/
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }


//        return dao.getShopPv();
    }

    public Integer[] initDate(String type) {
        Integer[] date = new Integer[]{0, 0, 0, 0};
        /*昨天日期对比*/
        if ("0".equals(type)) {
            //今天
            date[0] = Helper.getTodayAsSecondInt(0);
            //昨天
            date[1] = Helper.getTodayAsSecondInt(-1);
            date[2] = date[1];
            //前天
            date[3] = Helper.getTodayAsSecondInt(-2);
        } else if ("1".equals(type)) { /*近7天对比*/
            //今天
            date[0] = Helper.getTodayAsSecondInt(0);
            //前7天
            date[1] = Helper.getTodayAsSecondInt(-7);
            date[2] = date[1];
            //上个前7天
            date[3] = Helper.getTodayAsSecondInt(-14);
        }
        return date;
    }

    public static void main(String[] args) {
        Integer[] d = new RShopService().initDate("0");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i] + " f== " + df.format(new Date(new Long(d[i].toString() + "000"))));
        }

    }
}
