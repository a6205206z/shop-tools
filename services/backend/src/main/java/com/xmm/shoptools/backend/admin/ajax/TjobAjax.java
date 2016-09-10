package com.xmm.shoptools.backend.admin.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmm.shoptools.backend.admin.web.BaseAction;
import com.xmm.shoptools.backend.service.TjobService;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TjobQuery;
import com.xmm.shoptools.backend.vo.TjobVO;

@Controller
@Scope("prototype")
@RequestMapping("/ajax/job")
public class TjobAjax extends BaseAction {

	@Autowired
	private TjobService tjobService;

	@RequestMapping("/list")
	@ResponseBody
	public PageResult<TjobVO> list(HttpServletRequest request,TjobQuery query){
		PageResult<TjobVO> pr = null;
		pr = tjobService.query(query);
		return pr;
	}

}
