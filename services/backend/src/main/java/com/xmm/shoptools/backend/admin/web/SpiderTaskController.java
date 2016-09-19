package com.xmm.shoptools.backend.admin.web;

import com.xmm.shoptools.backend.entity.Tspider;
import com.xmm.shoptools.backend.service.TspiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.utils.HttpUtil;
import com.xmm.shoptools.backend.utils.StringUtils;

import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/spiderTask")
public class SpiderTaskController extends BaseAction {

    @Autowired
    TspiderService tspiderService;

	//后台主页
    @RequestMapping("/index")
    public ModelAndView index(String node) {
        ModelAndView mav = new ModelAndView("/admin/spiderTask");

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
    
    @RequestMapping(value = "/startSpider")
    public ModelAndView editTshops(String spiderName,String runid,String node) {
        try {
            if(StringUtils.isEmpty(node)){
                return error("请选择爬虫节点!");
            }
            if(StringUtils.isEmpty(spiderName) || spiderName.trim().equals("0")){
                return error("爬虫名字不能为空！");
            }
            if (StringUtils.isEmpty(runid)||!StringUtils.isNumber(runid)) {
                return error("时间戳不能为空,必须是数字!");
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
