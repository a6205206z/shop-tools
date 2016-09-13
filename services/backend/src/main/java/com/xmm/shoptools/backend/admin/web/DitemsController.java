package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.service.DitemsService;
import com.xmm.shoptools.backend.vo.DitemsQuery;
import com.xmm.shoptools.backend.vo.DitemsVO;
import com.xmm.shoptools.backend.vo.PageResult;

@Controller
@Scope("prototype")
@RequestMapping("/ditems")
public class DitemsController extends BaseAction {
    
    @Autowired
    private DitemsService ditemsService;
    
    //商品详情查看后台页
    @RequestMapping("/index")
    public ModelAndView index(DitemsQuery query) {
        PageResult<DitemsVO> pr = null;
        if(query!=null&&query.getShopid()!=null&&query.getDate()!=null){
            query.setRows(Integer.MAX_VALUE);
            pr = ditemsService.query(query);
        }
        ModelAndView mav = new ModelAndView("/admin/ditems");
        mav.addObject("pr", pr);
        return mav;
    }
}
