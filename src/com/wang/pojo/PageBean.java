package com.wang.pojo;

import java.util.List;

public class PageBean {
	//当前页数
	private int currentPage;
	//总记录数
    private int totalCount;
    //每页显示的记录数
    private int pageSize;
    //总的页数
    private int totalPage;
    //开始位置
    private int begin;
    //每页list集合
    private List<?> list;
    public PageBean() {
		// TODO Auto-generated constructor stub
	}
    public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
}
