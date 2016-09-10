package com.xmm.shoptools.backend.admin.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmm.shoptools.backend.admin.web.BaseAction;
import com.xmm.shoptools.backend.service.DitemsService;
import com.xmm.shoptools.backend.vo.DitemsQuery;
import com.xmm.shoptools.backend.vo.DitemsVO;
import com.xmm.shoptools.backend.vo.PageResult;

@Controller
@Scope("prototype")
@RequestMapping("/ajax/ditems")
public class DitemsAjax extends BaseAction {

	@Autowired
	private DitemsService ditemsService;

	@RequestMapping("/list")
	@ResponseBody
	public PageResult<DitemsVO> list(DitemsQuery query){
		PageResult<DitemsVO> pr = null;
		pr = ditemsService.query(query);
		return pr;
	}

}
