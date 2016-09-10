package com.xmm.shoptools.backend.admin.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmm.shoptools.backend.admin.web.BaseAction;
import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.vo.Menu;

@Controller
@Scope("prototype")
@RequestMapping("/ajax/home")
public class HomePageAjax extends BaseAction {

	@RequestMapping(value = "/getTreeData")
	public @ResponseBody List<Menu> treeData(HttpServletRequest request) {
		List<Menu> children = new ArrayList<Menu>();
		children.add(new Menu("任务管理", InitConfig.path + "/job/index"));
//		children.add(new Menu("角色管理", InitConfig.path + "/role/list"));

		List<Menu> parent = new ArrayList<Menu>();
//		Menu menu2 = null;
//		Admins log = this.getLoginUser(request);
//		if (null != log && "admin".equals(log.getUserName())) {
//			children.add(new Menu("资源管理", InitConfig.path + "/res/index"));
//			List<Menu> children2 = new ArrayList<Menu>();
//			children2.add(new Menu("项目详情", InitConfig.path + "/pro/index"));
//			children2.add(new Menu("新增管理员", InitConfig.path + "/admin/index"));
//			children2.add(new Menu("日志详情", InitConfig.path + "/log/index"));
//			menu2 = new Menu("项目管理", "", children2);
//		}
		Menu menu = new Menu("后台管理", "", children);
		parent.add(menu);
//		if(menu2!=null){
//			parent.add(menu2);
//		}
		return parent;
	}
}
