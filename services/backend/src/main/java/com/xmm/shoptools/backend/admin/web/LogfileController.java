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
@RequestMapping("/logfile")
public class LogfileController extends BaseAction {
	
	//日志详情查看后台页
	@RequestMapping("/index")
	public ModelAndView index(String logfile) {
		ModelAndView mav = new ModelAndView("/admin/logfile");
		String getLogs = HttpUtil.sendGet(ResUrl.LOG_IP+ResUrl.LOG_URL, "name="+logfile, "UTF-8");
		mav.addObject("getLogs", getLogs);
		return mav;
	}

}
