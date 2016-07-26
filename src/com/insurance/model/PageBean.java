package com.insurance.model;

/**
 * 分页model
 * @author fly
 * 2014-11-26
 */
public class PageBean {

	private int page; // 第几页
	private int pageSize; // 每页记录数
    private int offset;//起始行 bootstrp-table分页
    
//	public PageBean(int page, int pageSize) {
//		super();
//		this.page = page;
//		this.pageSize = pageSize;
//	}
	
    /**
     * 
     * @param pageSize 每页大小
     * @param offset起始行
     */
	public PageBean(int pageSize,int offset) {
		super();
		this.offset=offset;
		this.pageSize=pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * 获取起始行
	 * 
	 * @return
	 */
	public int getStart() {
		return (page - 1) * pageSize;
	}

}
