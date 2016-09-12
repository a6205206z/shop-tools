package com.xmm.shoptools.backend.admin.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.utils.HttpUtil;
import com.xmm.shoptools.backend.vo.ResUrl;

/**
 * 
 * @author leidian
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/spider")
public class SpiderController extends BaseAction {
	
	//爬虫配置详情查看后台页
	@RequestMapping("/cfg")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/admin/spidercfg");
		String cfgs = HttpUtil.sendGet(ResUrl.REMOTE_IP+ResUrl.SPIDER_CFG_URL, "","UTF-8");
		mav.addObject("cfgs", cfgs);
		return mav;
	}

}
