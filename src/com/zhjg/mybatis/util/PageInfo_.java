package com.zhjg.mybatis.util;

import java.util.List;

public class PageInfo_<T> {
	
	//每页记录数
	private int pageSize = 20;
	//当前页
	private int curPage = 1;
	//偏移量
	private int offset;
	//总记录数
	private int totalCount;
	//总页数
	private int totalPages;
	//记录
	private List<T> records;
	
	
	
	public PageInfo_() {
		super();
	}

	public PageInfo_(int pageSize, int curPage) {
		super();
		if(pageSize > 0)
		this.pageSize = pageSize;
		if(curPage > 0)
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getOffset() {
		this.offset = (curPage-1)*pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		this.totalPages = totalCount/pageSize + ((totalCount%pageSize>0)?1:0);
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
}
