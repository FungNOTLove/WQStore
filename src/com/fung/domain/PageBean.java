package com.fung.domain;

import java.util.List;

public class PageBean<T> {
	
	private List<T> lists;//保存当页数据   limit 0 12
	private int maxPages;//最大分页数
	private int currPage;//当前页数
	private int count;//每一页的数据量
	
	public PageBean() {
		// TODO Auto-generated constructor stub
	}

	public PageBean(List<T> lists, int maxPages, int currPage, int count) {
		super();
		this.lists = lists;
		this.maxPages = maxPages;
		this.currPage = currPage;
		this.count = count;
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PageBean [lists=" + lists + ", maxPages=" + maxPages
				+ ", currPage=" + currPage + ", count=" + count + "]";
	}
	
	
	
	
	
	
}
