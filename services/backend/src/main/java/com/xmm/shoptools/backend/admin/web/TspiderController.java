package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.entity.Tspider;
import com.xmm.shoptools.backend.service.TspiderService;
import com.xmm.shoptools.backend.utils.BeanVoUtil;
import com.xmm.shoptools.backend.utils.StringUtils;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TspiderQuery;
import com.xmm.shoptools.backend.vo.TspiderVO;

@Controller
@Scope("prototype")
@RequestMapping("/tspider")
public class TspiderController extends BaseAction {
    
    @Autowired
    private TspiderService tspiderService;
    
	//后台主页
    @RequestMapping("/index")
    public ModelAndView index() {
        PageResult<TspiderVO> pr = null;
        TspiderQuery query = new TspiderQuery();
        query.setRows(Integer.MAX_VALUE);
        ModelAndView mav = new ModelAndView("/admin/tspider");
        pr = tspiderService.query(query);
        mav.addObject("pr", pr);
        return mav;
    }
    
    //添加&编辑 跳转页
    @RequestMapping("/input")
    public ModelAndView input(String id) {
        ModelAndView mav = new ModelAndView("/admin/tspider_input");
        if(StringUtils.isEmpty(id)){
            /*新增*/
        }else{
            /*编辑回显*/
            Tspider tspider = this.tspiderService.getTspider(id);
            if(tspider!=null){
                TspiderVO tspiderVO = new TspiderVO();
                BeanVoUtil.copyObject(tspider, tspiderVO);
                mav.addObject("tspider", tspiderVO);
            }
        }
        return mav;
    }
    
    @RequestMapping(value = "/editSpiders")
    public ModelAndView editTshops(String id,String nName,String host,String descrition) {
        try {
            
            if(StringUtils.isEmpty(nName)){
                return error("主机名不能为空！");
            }
            if(StringUtils.isEmpty(host)){
                return error("爬虫地址不能为空！");
            }
            
            // 新增
            if (StringUtils.isEmpty(id)) {
                this.tspiderService.addTspider(nName,host,descrition);
                /*返回主页*/
                return getRedirectView("/tspider/index");
            } else {
                //编辑
                this.tspiderService.editTspider(id,nName,host,descrition);
                /*返回主页*/
                return getRedirectView("/tspider/index");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error("操作失败！！异常信息:"+e.getMessage());
        }
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable")
    public ModelAndView disable(String id) {
        try {
            if (StringUtils.isEmpty(id)) {
                return error("爬虫id为空，操作失败！！");
            }
            boolean result = this.tspiderService.disableTspider(id);
            if(result){
                /*返回主页*/
                return getRedirectView("/tspider/index");
            }else{
                return error("该爬虫状态为空！操作失败！！");
            }
        } catch (Exception e) {
            return error("操作失败！！异常信息:"+e.getMessage());
        }
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    public ModelAndView delete(String id) {
        try {
            if (StringUtils.isEmpty(id)) {
                return error("爬虫id为空，操作失败！！");
            }
            boolean result = this.tspiderService.deleteTspider(id);
            if(result){
                /*返回主页*/
                return getRedirectView("/tspider/index");
            }else{
                return error("该爬虫不存在！操作失败！！");
            }
        } catch (Exception e) {
            return error("操作失败！！异常信息:"+e.getMessage());
        }
    }
}
