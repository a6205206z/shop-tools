package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.service.TjobService;
import com.xmm.shoptools.backend.utils.StringUtils;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TjobQuery;
import com.xmm.shoptools.backend.vo.TjobVO;

@Controller
@Scope("prototype")
@RequestMapping("/job")
public class TjobController extends BaseAction {
    
    @Autowired
    private TjobService tjobService;
    
    // 后台主页
    @RequestMapping("/index")
    public String index() {
        return "/admin/spiderType";
    }
    
	//job详情展示页
    @RequestMapping("/list")
    public ModelAndView list(String token) {
        try {
            if(StringUtils.isEmpty(token)){
                return error("请选择爬虫任务类别!");
            }
            String spider_name =  InitConfig.SPIDER_NAME;
            if(StringUtils.isEmpty(spider_name)){
                return error("配置文件爬虫为空!");
            }
            ModelAndView mav = new ModelAndView("/admin/job");
            PageResult<TjobVO> pr = null;
            TjobQuery query = new TjobQuery();
            query.setRows(Integer.MAX_VALUE);
            query.setToken(token); 
            String[] splits = spider_name.split(",");
            query.setSpider_name(splits);
            pr = tjobService.query(query);
            mav.addObject("pr", pr);
            mav.addObject("token", token);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            return error("操作失败！！异常信息:"+e.getMessage());
        }
    }
}
