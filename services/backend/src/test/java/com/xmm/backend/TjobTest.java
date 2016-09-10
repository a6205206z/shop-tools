package com.xmm.backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmm.shoptools.backend.dao.TjobDao;
import com.xmm.shoptools.backend.entity.Tjob;
import com.xmm.shoptools.backend.utils.HttpUtil;
import com.xmm.shoptools.backend.vo.ResUrl;
import com.xmm.shoptools.backend.vo.TjobQuery;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author leidian
 * @date 2016年9月7日 下午5:17:35
 * @version TjobTest.java, v 0.1
 *
 */
public class TjobTest extends BaseJunit4Test {
    @Autowired TjobDao tjobDao;
    
    @Test
    public void testName() throws Exception {
//        Tjob tjob = tjobDao.get(1473091200);
        List<Tjob> rows = tjobDao.query(new TjobQuery());
        for (Tjob tjob : rows) {
//            System.err.println(tjob.getStats());
            System.err.println(tjob.getLogfile());
        }
    }
    
    @Test
    public void testName2() throws Exception {
        
        String get = HttpUtil.sendGet(ResUrl.LOG_IP+ResUrl.LOG_URL, "name=TaobaoShopProductSpider-1473264000.log", "UTF-8");
        System.out.println("----------------get"+get);
    }
}

