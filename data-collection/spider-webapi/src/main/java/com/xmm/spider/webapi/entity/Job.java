package com.xmm.spider.webapi.entity;

import java.util.Date;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.spider.webapi.entity, v 0.1
 * @date 16 /9/5.
 */
public class Job {
    /**
     * Gets runid.
     *
     * @return the runid
     */
    public int getRunid() {
        return runid;
    }

    /**
     * Sets runid.
     *
     * @param runid the runid
     */
    public void setRunid(int runid) {
        this.runid = runid;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets finish time.
     *
     * @return the finish time
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * Sets finish time.
     *
     * @param finishTime the finish time
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * Gets logfile.
     *
     * @return the logfile
     */
    public String getLogfile() {
        return logfile;
    }

    /**
     * Sets logfile.
     *
     * @param logfile the logfile
     */
    public void setLogfile(String logfile) {
        this.logfile = logfile;
    }

    /**
     * Gets stats.
     *
     * @return the stats
     */
    public String getStats() {
        return stats;
    }

    /**
     * Sets stats.
     *
     * @param stats the stats
     */
    public void setStats(String stats) {
        this.stats = stats;
    }

    /**
     * Gets spider name.
     *
     * @return the spider name
     */
    public String getSpider_name() {
        return spider_name;
    }

    /**
     * Sets spider name.
     *
     * @param spider_name the spider name
     */
    public void setSpider_name(String spider_name) {
        this.spider_name = spider_name;
    }


    private int runid;
    private String spider_name;
    private Date startTime;
    private Date finishTime;
    private String logfile;
    private String stats;
}
