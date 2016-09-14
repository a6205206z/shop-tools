package com.xmm.shoptools.backend.admin.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmm.shoptools.backend.service.DitemsService;

@Controller
@Scope("prototype")
@RequestMapping("/home")
public class HomePage {
	@Autowired
	private DitemsService ditemsService;

	@RequestMapping(value = "index")
	public ModelAndView index() {
	    List<Map<Long,Integer>> dateCount = ditemsService.dateCount();
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		 for (Map<Long, Integer> map : dateCount) {
		     sb.append(String.format("[%d,%d],",map.get("date"),map.get("value")));   
		 }

		sb.append("]");

		ModelAndView model = new ModelAndView("/admin/index");
		model.addObject("dateCount", sb.toString());
		return  model;
	}
}
