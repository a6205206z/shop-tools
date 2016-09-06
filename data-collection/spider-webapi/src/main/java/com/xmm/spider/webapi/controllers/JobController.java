package com.xmm.spider.webapi.controllers;

import com.xmm.spider.webapi.dao.JobDao;
import com.xmm.spider.webapi.entity.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.spider.webapi.controllers, v 0.1
 * @date 16 /9/5.
 */
@RestController
@RequestMapping("/job")
public class JobController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    /**
     * Get one job job.
     *
     * @param runid the runid
     * @return the job
     */
    @Deprecated
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Job GetOneJob(int runid){
        JobDao jd = new JobDao();
        Job job = null;
        try {
            job = jd.GetJobByRunID(runid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }
}