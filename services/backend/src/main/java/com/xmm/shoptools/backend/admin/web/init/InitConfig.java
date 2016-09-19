package com.xmm.shoptools.backend.admin.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tiaotiaohu
 * @version 1.0.1 2016年5月10日
 */
@Component
public class InitConfig {
	private static final Logger log = LoggerFactory.getLogger(InitConfig.class);
	// 主站域名
	public static String master_site;


	/**
	 * 项目根路径
	 */
	public static String path = "/";


    //日志文件接口URL
    public static String LOG_URL;
    //爬虫配置详情Url
    public static String SPIDER_CFG_URL;
    //获取爬虫名的Url
    public static String SPIDER_LIST_URL;
    //开启爬虫任务
    public static String SPIDER_START_URL;

//	@Value("#{config['master_site']}")
//	public void setMaster_site(String master_site) {
//		InitConfig.master_site = master_site;
//		log.error("master_site:" + master_site);
//	}



	@Value("#{config['log_Url']}")
    public void setLOG_URL(String log_Url) {
	    InitConfig.LOG_URL = log_Url;
        log.error("LOG_URL:" + log_Url);
    }

	@Value("#{config['spider_cfg_Url']}")
    public void setSPIDER_CFG_URL(String spider_cfg_Url) {
	    InitConfig.SPIDER_CFG_URL = spider_cfg_Url;
        log.error("SPIDER_CFG_URL:" + spider_cfg_Url);
    }

	@Value("#{config['spider_list_Url']}")
    public void setSPIDER_LIST_URL(String spider_list_Url) {
	    InitConfig.SPIDER_LIST_URL = spider_list_Url;
        log.error("SPIDER_LIST_URL:" + spider_list_Url);
    }

	@Value("#{config['spider_start_Url']}")
    public void setSPIDER_START_URL(String spider_start_Url) {
	    InitConfig.SPIDER_START_URL = spider_start_Url;
        log.error("SPIDER_START_URL:" + spider_start_Url);
    }
	
	

}
