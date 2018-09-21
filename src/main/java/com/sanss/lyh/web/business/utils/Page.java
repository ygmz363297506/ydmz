package com.sanss.lyh.web.business.utils;

public class Page {
	private int flag = 1;
	private long row = 10;
	private long count = 0;
	@SuppressWarnings("unused")
	private long pageCount = 0;
	@SuppressWarnings("unused")
	private long maxPage;
	@SuppressWarnings("unused")
	private long pageFrom;
	private long pageNow = 1;
	private String orderby;
	private String orderasc;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOrderasc() {
		return orderasc;
	}

	public void setOrderasc(String orderasc) {
		this.orderasc = orderasc;
	}

	public long getRow() {
		return row;
	}

	public void setRow(long row) {
		this.row = row;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
		setPageCount(getMaxPage());
	}

	public long getPageCount() {
		return getMaxPage();
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getPageNow() {
		return pageNow;
	}

	public void setPageNow(long pageNow) {
		this.pageNow = pageNow;
	}

	public long getPageFrom() {
		return pageNow < 1 ? 0 : (pageNow - 1) * row;
	}

	public long getMaxPage() {
		return count % row == 0 ? count / row : count / row + 1;
	}

	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}

	public void setPageFrom(long pageFrom) {
		this.pageFrom = pageFrom;
	}

}
