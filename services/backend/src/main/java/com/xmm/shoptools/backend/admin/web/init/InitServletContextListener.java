package com.xmm.shoptools.backend.admin.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author tiaotiaohu
 * @version 1.0.1 2016年5月20日
 *
 */
public class InitServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		String path = "/shop";
		event.getServletContext().setAttribute("root", path);
		InitConfig.path = path;
	}

}
