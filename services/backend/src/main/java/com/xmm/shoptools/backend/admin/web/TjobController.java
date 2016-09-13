package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.service.TjobService;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TjobQuery;
import com.xmm.shoptools.backend.vo.TjobVO;

@Controller
@Scope("prototype")
@RequestMapping("/job")
public class TjobController extends BaseAction {
    
    @Autowired
    private TjobService tjobService;

	//job后台页
    @RequestMapping("/index")
    public ModelAndView index() {
        PageResult<TjobVO> pr = null;
        TjobQuery query = new TjobQuery();
        query.setRows(Integer.MAX_VALUE);
        ModelAndView mav = new ModelAndView("/admin/job");
        pr = tjobService.query(query);
        mav.addObject("pr", pr);
        return mav;
    }
}
