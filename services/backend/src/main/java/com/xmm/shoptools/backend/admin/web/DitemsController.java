package com.xmm.shoptools.backend.admin.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("/ditems")
public class DitemsController extends BaseAction {

    //商品详情查看后台页
    @RequestMapping("/index")
    public ModelAndView index(Long shopid,Long date) {
        ModelAndView mav = new ModelAndView("/admin/ditems");
        mav.addObject("shopid", shopid);
        mav.addObject("date", date);
        return mav;
    }
}
