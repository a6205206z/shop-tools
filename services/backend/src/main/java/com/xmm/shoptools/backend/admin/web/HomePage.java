package com.xmm.shoptools.backend.admin.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
@RequestMapping("/home")
public class HomePage {
	@RequestMapping(value = "index")
	public ModelAndView index() {
		return new ModelAndView("/admin/index");
	}
}
