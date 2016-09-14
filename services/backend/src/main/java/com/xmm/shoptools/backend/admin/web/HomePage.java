package com.xmm.shoptools.backend.admin.web;

import com.xmm.shoptools.backend.service.DitemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/home")
public class HomePage {
	@Autowired
	private DitemsService ditemsService;

	@RequestMapping(value = "index")
	public ModelAndView index() {
		Map<Long,Integer> dateCount = ditemsService.dateCount();
		StringBuilder sb = new StringBuilder();
		sb.append("[");

//		for (Map.Entry<Long, Integer> entry : dateCount.entrySet()) {
//			sb.append(String.format("[%d,%d],",entry.getKey(),entry.getValue()));
//		}

		sb.append("]");

		ModelAndView model = new ModelAndView("/admin/index");
		return  model;
	}
}
