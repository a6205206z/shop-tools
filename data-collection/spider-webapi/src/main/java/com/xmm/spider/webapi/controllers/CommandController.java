package com.xmm.spider.webapi.controllers;

import com.xmm.spider.webapi.configs.SpiderConfig;
import com.xmm.spider.webapi.core.Spider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Huke
 * @version com.xmm.spider.webapi.controllers.CommandController.java, v 0.1
 * @date 2016年8月30日 下午3:26:57
 */
@RestController
@RequestMapping("/cmd")
public class CommandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandController.class);

    /**
     * The Spider config.
     */
    @Autowired
    SpiderConfig spiderConfig;

    /**
     * List all spiders.
     *
     * @return spiders crawls
     */
    @Deprecated
    @RequestMapping(value = "/spider/crawl/list", method = RequestMethod.GET)
    public List<String> ListSpiderCrawls(){
        return Spider.load(spiderConfig).getCrawlList();
    }

    /**
     * Get spider config.
     *
     * @return spider config
     */
    @Deprecated
    @RequestMapping(value = "/spider/config", method = RequestMethod.GET)
    public String GetSpiderConfig(){
        return Spider.load(spiderConfig).getSpiderConfig();
    }


    /**
     * Start crawl.
     *
     * @param name the name
     * @return result dir path
     */
    @Deprecated
    @RequestMapping(value = "/spider/crawl/start", method = RequestMethod.GET)
    public String StartCrawl(String name){
        return Spider.load(spiderConfig).runCrawl(name);
    }


    /**
     * Get log string.
     *
     * @param name the name
     * @return log content
     */
    @Deprecated
    @RequestMapping(value = "/spider/log", method = RequestMethod.GET)
    public String GetLog(String name){
        return Spider.load(spiderConfig).getSpiderLog(name);
    }
}
