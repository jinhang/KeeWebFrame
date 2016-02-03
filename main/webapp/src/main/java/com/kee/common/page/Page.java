package com.kee.common.page;

/**
 * 这里用一句话描述这个类的作用
 * 
 * @author 蒋永亮
 * @version 1.00 2011-8-31
 */
public class Page {
	/**
	 * 每页显示数量
	 */
	private int everyPage;
	/**
	 * 总记录数
	 */
	private int totalCount;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 起始点
	 */
	private int beginIndex;
	/**
	 * 是否有上一页
	 */
	private boolean hasPrePage;
	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage;

	/**
	 * 构造方法
	 * @param everyPage2
	 * @param totalCount2
	 * @param totalPage2
	 * @param currentPage2
	 * @param beginIndex2
	 * @param hasPrePage2
	 * @param hasNextPage2
	 */
	public Page(int everyPage2, int totalCount2, int totalPage2,
			int currentPage2, int beginIndex2, boolean hasPrePage2,
			boolean hasNextPage2) {

		this.everyPage = everyPage2;
		this.totalCount = totalCount2;
		this.totalPage = totalPage2;
		this.currentPage = currentPage2;
		this.beginIndex = beginIndex2;
		this.hasPrePage = hasPrePage2;
		this.hasNextPage = hasNextPage2;
	}

	/*
	 * 相应的有参无参构造器，getters和setters方法
	 */
	public Page() {

	}

	/**
	 * 构造函数
	 * @param everyPage 每页数
	 * @param totalCount 总数
	 * @param currentPage 当前页码
	 */
	public Page(int everyPage,int totalCount,int currentPage){
		this.everyPage = getEveryPage(everyPage);
		this.totalPage = getTotalPage(everyPage, totalCount);
		this.currentPage = getCurrentPage(currentPage,totalPage);
		this.beginIndex = getBeginIndex(everyPage, currentPage);
		this.hasPrePage = getHasPrePage(currentPage);
		this.hasNextPage = getHasNextPage(totalPage, currentPage);
	}
	
	public int getEveryPage() {
		return this.everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBeginIndex() {
		return this.beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public boolean isHasPrePage() {
		return this.hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return this.hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	public int getEveryPage(int everyPage) {
		return everyPage == 0 ? 10 : everyPage;
	}

	public int getCurrentPage(int currentPage,int totalPage) {
		return currentPage <1||currentPage>totalPage ? 1 : currentPage;
	}

	public int getTotalPage(int everyPage, int totalCount) {
		int totalPage = 0;
		this.totalCount = totalCount;
		if (totalCount % everyPage == 0) {
			totalPage = totalCount / everyPage;
		} else {
			totalPage = totalCount / everyPage + 1;
		}
		return totalPage;
	}

	public int getBeginIndex(int everyPage, int currentPage) {
		return (currentPage - 1) * everyPage;
	}

	public boolean getHasPrePage(int currentPage) {
		return currentPage == 1 ? false : true;
	}

	public boolean getHasNextPage(int totalPage, int currentPage) {
		return currentPage == totalPage || totalPage == 0 ? false : true;
	}
}
