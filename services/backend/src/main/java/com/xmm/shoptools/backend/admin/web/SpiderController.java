package com.xmm.shoptools.backend.admin.web;

import com.xmm.shoptools.backend.service.TspiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.utils.HttpUtil;

/**
 * 
 * @author leidian
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/spider")
public class SpiderController extends BaseAction {

	@Autowired
	TspiderService tspiderService;


	//爬虫配置详情查看后台页
	@RequestMapping("/cfg")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/admin/spidercfg");

		String cfgs = HttpUtil.sendGet(InitConfig.REMOTE_IP+InitConfig.SPIDER_CFG_URL, "","UTF-8");

		mav.addObject("cfgs", cfgs);
		return mav;
	}

}
