package com.xmm.shoptools.backend.vo;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String text;
	private String url;

	// 添加一个属性,装二级菜单
	private List<Menu> children = new ArrayList<Menu>();

	public Menu(String text, String url, List<Menu> children) {
		super();
		this.text = text;
		this.url = url;
		this.children = children;
	}
	
	
	
	public Menu(String text, String url) {
		super();
		this.text = text;
		this.url = url;
	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}


}
