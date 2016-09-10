package com.xmm.shoptools.backend.admin.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;

public class BaseAction {

	/**
	 * @return
	 */
	protected ModelAndView home() {
		return new ModelAndView(new RedirectView(InitConfig.path + "/home/index"));
	}

	/**
	 * 错误提示页面
	 * 
	 * @param msg
	 * @return
	 */
	public ModelAndView error(String msg) {
		ModelAndView error = new ModelAndView("/error");
		error.addObject("msg", msg);
		return error;
	}

	/**
	 * 获取项目部署物理路径
	 * 
	 * @return
	 */
	public String getRealPath() {
		return System.getProperty("web.root");
	}

	/**
	 * 跳转到action
	 * 
	 * @param path
	 * @return
	 */
	public ModelAndView getRedirectView(String path) {
		return new ModelAndView(new RedirectView(InitConfig.path + path));
	}

//	public Admins getLoginUser(HttpServletRequest request) {
//		return (Admins) request.getSession().getAttribute(Constants.USER_IN_SESSION);
//	}
}
