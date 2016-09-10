package com.xmm.shoptools.backend.admin.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("/dshop")
public class DshopController extends BaseAction {

    //日志详情查看后台页
    @RequestMapping("/index")
    public ModelAndView index(Long runid) {
        ModelAndView mav = new ModelAndView("/admin/dshop");
        mav.addObject("runid", runid);
        return mav;
    }
}
