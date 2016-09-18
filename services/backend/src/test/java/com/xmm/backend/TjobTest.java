package com.xmm.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmm.shoptools.backend.dao.TjobDao;
import com.xmm.shoptools.backend.service.DitemsService;

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
    @Autowired DitemsService ditemsService;
    
    @Test
    public void testName() throws Exception {
//        Tjob tjob = tjobDao.get(1473091200);
//        List<Tjob> rows = tjobDao.query(new TjobQuery());
//        for (Tjob tjob : rows) {
//            System.err.println(tjob.getStats());
//            System.err.println(tjob.getLogfile());
//        }
    }
    
}

