package com.xmm.shoptools.backend.admin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.entity.Tspider;
import com.xmm.shoptools.backend.service.TspiderService;
import com.xmm.shoptools.backend.utils.HttpUtil;
import com.xmm.shoptools.backend.utils.StringUtils;

/**
 * 
 * @author leidian
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/timeTask")
public class TimeTaskController extends BaseAction {

	@Autowired
	TspiderService tspiderService;

	//后台主页
    @RequestMapping("/index")
    public ModelAndView index(String node) {
        ModelAndView mav = new ModelAndView("/admin/timeTask");

        List<Tspider> spiderList = tspiderService.selectAllTspider();
        mav.addObject("spiderList", spiderList);

        if(!StringUtils.isEmpty(node)) {
            Tspider tspider = tspiderService.selectTspiderBynodeName(node);
            if (tspider != null) {
                String spiderNames = HttpUtil.sendGet(String.format("http://%s/%s", tspider.getHost(), InitConfig.SPIDER_LIST_URL), "","UTF-8");
                mav.addObject("spiderNames", spiderNames);
            }
            mav.addObject("selectNode",node);
        }

        return mav;
    }
    
    @RequestMapping(value = "/startJob")
    public ModelAndView editTshops(String[] spiderName,String runid,String node,String jobTime) {
        try {
            if(StringUtils.isEmpty(node)){
                return error("请选择爬虫节点!");
            }
            if(spiderName==null||spiderName.length<0){
                return error("请选择爬虫名！");
            }
            if (StringUtils.isEmpty(runid)||!StringUtils.isNumber(runid)) {
                return error("时间戳不能为空,必须是数字!");
            }
            
            if (StringUtils.isEmpty(jobTime)) {
                return error("请选择定时时间!");
            }
            String result = null;

            if(!StringUtils.isEmpty(node)) {
                Tspider tspider = tspiderService.selectTspiderBynodeName(node);
                if (tspider != null) {
                    result = HttpUtil.sendGet(String.format("http://%s/%s", tspider.getHost(), InitConfig.SPIDER_START_URL),"name="+spiderName+"&runid="+runid,"UTF-8");
                }
            }

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
