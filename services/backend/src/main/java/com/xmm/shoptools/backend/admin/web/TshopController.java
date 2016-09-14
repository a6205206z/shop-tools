package com.xmm.shoptools.backend.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.entity.Tshop;
import com.xmm.shoptools.backend.service.TshopService;
import com.xmm.shoptools.backend.utils.BeanVoUtil;
import com.xmm.shoptools.backend.utils.StringUtils;
import com.xmm.shoptools.backend.vo.AjaxResult;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TshopDTO;
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
    
    //添加&编辑页
    @RequestMapping("/input")
    public ModelAndView input(String id) {
        ModelAndView mav = new ModelAndView("/admin/tshop_input");
        if(StringUtils.isEmpty(id)){
            /*新增*/
        }else{
            Tshop tshop = this.tshopService.getTshop(id);
            if(tshop!=null){
                TshopVO tshopVO = new TshopVO();
                BeanVoUtil.copyObject(tshop, tshopVO);
                mav.addObject("tshop", tshopVO);
            }
        }
        return mav;
    }
    
    @RequestMapping(value = "/editTshops")
    public @ResponseBody AjaxResult editTshops(String dotype,String shopid,String sellerId,TshopDTO dto ) {
        try {
            if (StringUtils.isEmpty(shopid)||!StringUtils.isNumber(shopid)) {
                return new AjaxResult("请填写店铺ID,店铺ID必须为数字!", 100);
            }
            if (StringUtils.isEmpty(sellerId)||!StringUtils.isNumber(sellerId)) {
                return new AjaxResult("请填写卖家会员ID,卖家会员ID必须为数字!", 100);
            }
            
            if(StringUtils.isEmpty(dto.getNick())){
                return new AjaxResult("卖家昵称不能为空", 100);
            }
            if(StringUtils.isEmpty(dto.getStoreUrl())){
                return new AjaxResult("店铺地址不能为空", 100);
            }
            
            if(StringUtils.isEmpty(dto.getType())||dto.getType().equals("0")){
                return new AjaxResult("请选择用户类型", 100);
            }
            // 新增店铺
            if ("create".equals(dotype)) {
                this.tshopService.addTshop(dto,shopid,sellerId);
                return new AjaxResult("操作成功~");
            } else if ("edit".equals(dotype)) {
                if (StringUtils.isEmpty(dto.getId())) {
                    return new AjaxResult("请选择店铺进行编辑！", 100);
                }
                this.tshopService.editTshop(dto,shopid,sellerId);
                return new AjaxResult("操作成功~");
            }else {
                return new AjaxResult("操作失败！", 102);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("操作失败！", 102);
        }
    }

    /**
     * 禁用店铺
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable")
    public ModelAndView disable(String id) {
        try {
            if (StringUtils.isEmpty(id)) {
                return error("店铺id为空，操作失败！！");
            }
            this.tshopService.disableTshop(id);
            /*返回主页*/
            return index();
        } catch (Exception e) {
            return error("操作失败！！异常信息:"+e.getMessage());
        }
    }
}
