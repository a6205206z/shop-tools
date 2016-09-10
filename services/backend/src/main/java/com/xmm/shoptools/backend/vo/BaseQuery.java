package com.xmm.shoptools.backend.vo;

public abstract class BaseQuery {
	
	private Integer currentPage = 1;
	private Integer pageSize = 10;
//	关键字
	private String keyword;
	
	public Integer getBegin() {
		return (currentPage-1)*pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}


	public Integer getPageSize() {
		return pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	//	接收前台传入的分页数据page,rows,封装
	public void setPage(Integer page) {
		this.currentPage = page;
	}
		
	public void setRows(Integer rows) {
		this.pageSize = rows;
	}

	
	@Override
	public String toString() {
		return "BaseQuery [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", keyword=" + keyword + "]";
	}
	
	
}
