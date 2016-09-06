package com.xmm.spider.webapi.dao;

import com.xmm.spider.webapi.db.Dbutils;
import com.xmm.spider.webapi.entity.Job;

import java.sql.SQLException;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.spider.webapi.dao, v 0.1
 * @date 16 /9/5.
 */
public class JobDao {
    private final String SELECT_ONE_JOB="SELECT runid,spider_name,starttime,finishtime,logfile,stats FROM t_job where runid = ?";

    /**
     * Get job by run id job.
     *
     * @param runid the runid
     * @return the job
     * @throws SQLException the sql exception
     */
    public Job GetJobByRunID(int runid) throws SQLException {
        Job job = Dbutils.read(Job.class,SELECT_ONE_JOB,new Object[]{runid});
        return job;
    }
}
