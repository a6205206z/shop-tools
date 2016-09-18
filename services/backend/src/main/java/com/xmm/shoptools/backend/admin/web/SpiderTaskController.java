package com.xmm.shoptools.backend.admin.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.utils.HttpUtil;
import com.xmm.shoptools.backend.utils.StringUtils;

@Controller
@Scope("prototype")
@RequestMapping("/spiderTask")
public class SpiderTaskController extends BaseAction {
    
	//后台主页
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/admin/spiderTask");
        String spiderList = HttpUtil.sendGet(InitConfig.REMOTE_IP+InitConfig.SPIDER_LIST_URL, "","UTF-8");
        mav.addObject("spiders", spiderList);
        return mav;
    }
    
    @RequestMapping(value = "/startSpider")
    public ModelAndView editTshops(String spiderName,String runid) {
        try {
            if(StringUtils.isEmpty(spiderName) || spiderName.trim().equals("0")){
                return error("爬虫名字不能为空！");
            }
            if (StringUtils.isEmpty(runid)||!StringUtils.isNumber(runid)) {
                return error("时间戳不能为空,必须是数字!");
            }
            
            String result = HttpUtil.sendGet(InitConfig.REMOTE_IP+InitConfig.SPIDER_START_URL,"name="+spiderName+"&runid="+runid,"UTF-8");
            if(null==result){
                return error("开启失败！");
            }
            return getRedirectView("/job/index");
        } catch (Exception e) {
            e.printStackTrace();
            return error("操作失败！！异常信息:"+e.getMessage());
        }
    }

}
