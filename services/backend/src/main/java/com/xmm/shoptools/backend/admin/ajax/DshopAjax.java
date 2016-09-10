package com.xmm.shoptools.backend.admin.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmm.shoptools.backend.admin.web.BaseAction;
import com.xmm.shoptools.backend.service.DshopService;
import com.xmm.shoptools.backend.vo.DshopQuery;
import com.xmm.shoptools.backend.vo.DshopVO;
import com.xmm.shoptools.backend.vo.PageResult;

@Controller
@Scope("prototype")
@RequestMapping("/ajax/dshop")
public class DshopAjax extends BaseAction {

	@Autowired
	private DshopService dshopService;

	@RequestMapping("/list")
	@ResponseBody
	public PageResult<DshopVO> list(DshopQuery query){
		PageResult<DshopVO> pr = null;
		pr = dshopService.query(query);
		return pr;
	}

}
