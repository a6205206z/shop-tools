package com.xmm.shoptools.backend.admin.web;

import com.xmm.shoptools.backend.entity.Tspider;
import com.xmm.shoptools.backend.service.TspiderService;
import com.xmm.shoptools.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.utils.HttpUtil;

import java.util.List;

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
	public ModelAndView index(String node) {
		ModelAndView mav = new ModelAndView("/admin/spidercfg");

		List<Tspider> spiderList = tspiderService.selectAllTspider();
		mav.addObject("spiderList", spiderList);

		if(!StringUtils.isEmpty(node)) {
			Tspider tspider = tspiderService.selectTspiderBynodeName(node);
			if (tspider != null) {
				String cfg = HttpUtil.sendGet(String.format("http://%s/%s", tspider.getHost(), InitConfig.SPIDER_CFG_URL), "", "UTF-8");
				mav.addObject("cfg", cfg);
			}
		}

		return mav;
	}

}
