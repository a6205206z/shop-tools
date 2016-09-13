package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.service.DshopService;
import com.xmm.shoptools.backend.vo.DshopQuery;
import com.xmm.shoptools.backend.vo.DshopVO;
import com.xmm.shoptools.backend.vo.PageResult;

@Controller
@Scope("prototype")
@RequestMapping("/dshop")
public class DshopController extends BaseAction {
    
    @Autowired
    private DshopService dshopService;
    
    //店铺详情查看后台页
    @RequestMapping("/index")
    public ModelAndView index(DshopQuery query) {
        PageResult<DshopVO> pr = null;
        if(query!=null&&query.getRunid()!=null){
            query.setRows(Integer.MAX_VALUE);
            pr = dshopService.query(query);
        }
        ModelAndView mav = new ModelAndView("/admin/dshop");
        mav.addObject("pr", pr);
        return mav;
    }
}
