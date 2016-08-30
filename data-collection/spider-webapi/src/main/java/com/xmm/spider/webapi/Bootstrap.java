package com.xmm.spider.webapi;

import com.xmm.spider.webapi.configs.SpiderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cean on 16/8/30.
 */
//@ImportResource(locations={"classpath:META-INF/spring/spring-mvc.xml"})
@EnableConfigurationProperties({SpiderConfig.class})
@SpringBootApplication(scanBasePackages={"com.xmm.spider.webapi"})
public class Bootstrap {
    public static void main(String args[]){
        SpringApplication.run(Bootstrap.class, args);
    }
}
