package com.xmm.shoptools.backend.vo;

import java.util.ArrayList;
import java.util.List;

//封装分页结果的类
public class PageResult<T> {
	// 前台需要的总条数
	private Integer total;
	// 前台需要的data数据
	private List<T> rows = new ArrayList<T>();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageResult [total=" + total + ", rows=" + rows + "]";
	}

}
