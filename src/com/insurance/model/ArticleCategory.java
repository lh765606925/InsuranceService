package com.insurance.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

/**
 * 实体类 - 文章分类
 */

@Entity
public class ArticleCategory extends BaseEntity {

	private static final long serialVersionUID = -5132652107151648662L;

	public static final String PATH_SEPARATOR = ",";// 树路径分隔符

	private String name;// 分类名称
	private String metaKeywords;// 页面关键词
	private String metaDescription;// 页面描述
	private Integer orderList;// 排序
	private String path;// 树路径
	
	private String parent_articleCategory_id;// 上级分类

	
	public String getParent_articleCategory_id() {
		return parent_articleCategory_id;
	}

	public void setParent_articleCategory_id(String parent_articleCategory_id) {
		this.parent_articleCategory_id = parent_articleCategory_id;
	}


	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length = 5000)
	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	@Column(length = 5000)
	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	
	@Column(nullable = false)
	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@Column(nullable = true, length = 10000)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}