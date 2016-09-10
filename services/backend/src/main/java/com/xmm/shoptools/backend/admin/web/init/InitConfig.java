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

	// 企业钉钉密钥KEY
	// public static String oapi_host;
	// public static String corp_id;
	// public static String corp_secret;
	// public static String sso_secret;

	/**
	 * 项目根路径
	 */
	public static String path = "/";
//
//	@Value("#{config['master_site']}")
//	public void setMaster_site(String master_site) {
//		InitConfig.master_site = master_site;
//		log.error("master_site:" + master_site);
//	}

	// @Value("#{config['oapi_host']}")
	// public void setOapi_host(String oapi_host) {
	// InitConfig.oapi_host = oapi_host;
	// }
	//
	// @Value("#{config['corp_id']}")
	// public void setCorp_id(String corp_id) {
	// InitConfig.corp_id = corp_id;
	// }
	//
	// @Value("#{config['corp_secret']}")
	// public void setCorp_secret(String corp_secret) {
	// InitConfig.corp_secret = corp_secret;
	// }
	//
	// @Value("#{config['sso_secret']}")
	// public void setSso_secret(String sso_secret) {
	// InitConfig.sso_secret = sso_secret;
	// }
}
