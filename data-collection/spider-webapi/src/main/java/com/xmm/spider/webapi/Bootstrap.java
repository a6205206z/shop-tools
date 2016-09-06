package com.xmm.spider.webapi;

import com.xmm.spider.webapi.configs.SpiderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author Huke
 * @version com.xmm.spider.webapi.Bootstrap.java, v 0.1
 * @date 2016年8月30日 下午3:26:57
 */
//@ImportResource(locations={"classpath:META-INF/spring/spring-mvc.xml"})
@EnableConfigurationProperties({SpiderConfig.class})
@SpringBootApplication(scanBasePackages={"com.xmm.spider.webapi"})
public class Bootstrap {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]){
        SpringApplication.run(Bootstrap.class, args);
    }
}
