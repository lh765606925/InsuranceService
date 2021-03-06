package com.insurance.dao;

import java.util.List;

import com.insurance.model.ArticleCategory;
import com.insurance.model.PageBean;


/**
 * Dao接口 - 文章分类
 */

public interface ArticleCategoryDao extends BaseDao<ArticleCategory, String> {
	
	/**
	 * 获取所有顶级文章分类集合;
	 * 
	 * @return 所有顶级文章分类集合
	 * 
	 */
	public List<ArticleCategory> getRootArticleCategoryList();
	
	/**
	 * 根据ArticleCategory对象获取所有父类集合，若无父类则返回null;
	 * 
	 * @return 父类集合
	 * 
	 */
	public List<ArticleCategory> getParentArticleCategoryList(ArticleCategory articleCategory);
	
	/**
	 * 根据ArticleCategory对象获取所有子类集合，若无子类则返回null;
	 * 
	 * @return 子类集合
	 * 
	 */
	public List<ArticleCategory> getChildrenArticleCategoryList(ArticleCategory articleCategory);

	public List<ArticleCategory> find(PageBean pageBean);

	public String save(ArticleCategory articleCategory);
	
	public ArticleCategory findbyId(String id);
}
