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

/**
 * 
 * @author leidian
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/logfile")
public class LogfileController extends BaseAction {

	@Autowired
	TspiderService tspiderService;

	//日志文件详情查看后台页
	@RequestMapping("/index")
	public ModelAndView index(String logfile, String node) {
		ModelAndView mav = new ModelAndView("/admin/logfile");

		if (!StringUtils.isEmpty(node)) {
			Tspider tspider = tspiderService.selectTspiderBynodeName(node);
			if (tspider != null) {
				String getLogs = HttpUtil.sendGet(String.format("http://%s/%s", tspider.getHost(), InitConfig.LOG_URL), "name=" + logfile, "UTF-8");
				mav.addObject("getLogs", getLogs);
			}
		}
		return mav;
	}

}
