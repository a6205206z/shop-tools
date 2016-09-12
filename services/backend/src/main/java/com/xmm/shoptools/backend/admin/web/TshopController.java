package com.xmm.shoptools.backend.admin.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/tshop")
public class TshopController extends BaseAction {

	// 后台主页
	@RequestMapping("/index")
	public String index() {
		return "/admin/tshop";
	}

}