package com.xmm.spider.webapi.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Huke
 * @version com.xmm.spider.webapi.configs.SpiderConfig.java, v 0.1
 * @date 2016年8月30日 下午3:26:57
 */
@ConfigurationProperties(prefix = "spider")
public class SpiderConfig {
    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets result path.
     *
     * @return the result path
     */
    public String getResultPath() {
        return resultPath;
    }

    /**
     * Sets result path.
     *
     * @param resultPath the result path
     */
    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    /**
     * Gets log path.
     *
     * @return the log path
     */
    public String getLogPath() {
        return logPath;
    }

    /**
     * Sets log path.
     *
     * @param logPath the log path
     */
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    private String name;
    private String path;
    private String resultPath;
    private String logPath;

}
