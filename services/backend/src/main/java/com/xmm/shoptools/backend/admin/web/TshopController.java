package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.service.TshopService;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TshopQuery;
import com.xmm.shoptools.backend.vo.TshopVO;

@Controller
@Scope("prototype")
@RequestMapping("/tshop")
public class TshopController extends BaseAction {
    
    @Autowired
    private TshopService tshopService;
    
	//后台主页
    @RequestMapping("/index")
    public ModelAndView index() {
        PageResult<TshopVO> pr = null;
        TshopQuery query = new TshopQuery();
        query.setRows(Integer.MAX_VALUE);
        ModelAndView mav = new ModelAndView("/admin/tshop");
        pr = tshopService.query(query);
        mav.addObject("pr", pr);
        return mav;
    }
}
