package com.xmm.shoptools.backend.admin.web;

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
	
	//日志文件详情查看后台页
	@RequestMapping("/index")
	public ModelAndView index(String logfile,String node) {
		ModelAndView mav = new ModelAndView("/admin/logfile");
		String getLogs = HttpUtil.sendGet(InitConfig.REMOTE_IP+InitConfig.LOG_URL, "name="+logfile, "UTF-8");
		mav.addObject("getLogs", getLogs);
		return mav;
	}

}
