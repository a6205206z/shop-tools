package com.xmm.shoptools.backend.admin.web.init;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author tiaotiaohu
 * @version 1.0.1 2016年5月20日
 *
 */
@Component
public class InitListener implements ApplicationListener<ApplicationEvent> {

	// spring 容器初始成功后执行
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof ContextRefreshedEvent) {
		}
	}

}
