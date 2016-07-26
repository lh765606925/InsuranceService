package com.insurance.util;

import com.insurance.model.PageBean;

/**
 * 分页工具类
 * 
 * @author fly 2014-12-2
 */
public class PageUtil {

	/**
	 * 获取分页参数
	 * 
	 * @param offset
	 * @param limit
	 * @param total
	 * @return
	 */
	public static PageBean getPageBean(String offset, String limit, long total) {
		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		if (offSet >= total && total != 0) {
			offSet = offSet - liMit;
		}
		PageBean pageBean = new PageBean(liMit, offSet);
		return pageBean;

	}

	public static PageBean getApp_PageBean(String pagesize, String pageindex, long total) {
		if (pagesize == null) {
			pagesize = "10";
		}
		if (pageindex == null) {
			pageindex = "0";
		}
		int pageSize = Integer.parseInt(pagesize);
		int pageIndex = Integer.parseInt(pageindex);
		int start = (pageIndex - 1) * pageSize;
		if (start >= total && total != 0) {
			start = start - pageSize;
		}
		PageBean pageBean = new PageBean(pageSize, start);
		return pageBean;

	}

	public static PageBean getAdmin_PageBean(String offset, String limit, long total) {
		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		if (offSet >= total && total != 0) {
			offSet = offSet - liMit;
		}
		PageBean pageBean = new PageBean(liMit, offSet);
		return pageBean;

	}

	/**
	 * 生成分页代码
	 * 
	 * @param targetUrl
	 *            目标地址
	 * @param totalNum
	 *            总数据
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页数据量
	 * @return
	 */
	public static String getPagination(String targetUrl, long totalNum, int currentPage, int pageSize) {
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "未查询到数据";
		} else {
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<li><a href='" + targetUrl + "?page=1'>首页</a></li>");
			if (currentPage > 1) {
				pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "'>上一页</a></li>");
			} else {
				pageCode.append("<li><a href='#'>上一页</a></li>");
			}
			for (int i = currentPage - 3; i <= currentPage + 3; i++) {
				if (i < 1 || i > totalPage) {
					continue;
				}
				if (i == currentPage) {
					pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
				} else {
					pageCode.append("<li><a href='" + targetUrl + "?page=" + i + "'>" + i + "</a></li>");
				}
			}
			if (currentPage < totalPage) {
				pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "'>下一页</a></li>");
			} else {
				pageCode.append("<li><a href='#'>下一页</a></li>");
			}

			return pageCode.toString();
		}

	}

}
