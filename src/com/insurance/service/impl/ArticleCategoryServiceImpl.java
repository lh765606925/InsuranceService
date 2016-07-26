package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.insurance.dao.ArticleCategoryDao;
import com.insurance.dao.BaseDao;
import com.insurance.model.Article;
import com.insurance.model.ArticleCategory;
import com.insurance.model.PageBean;
import com.insurance.service.ArticleCategoryService;

/**
 * Service实现类 - 文章分类
 */

@Service("ArticleCategoryService")
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory, String> implements
		ArticleCategoryService {

	@Resource
	private ArticleCategoryDao articleCategoryDao;

	@Resource
	private BaseDao<ArticleCategory, String> baseDao;

	public List<ArticleCategory> getRootArticleCategoryList() {
		List<ArticleCategory> rootArticleCategoryList = articleCategoryDao.getRootArticleCategoryList();
		if (rootArticleCategoryList != null) {
			for (ArticleCategory rootArticleCategory : rootArticleCategoryList) {
				Hibernate.initialize(rootArticleCategory);
			}
		}
		return rootArticleCategoryList;
	}

	public List<ArticleCategory> getParentArticleCategoryList(ArticleCategory articleCategory) {
		List<ArticleCategory> parentArticleCategoryList = articleCategoryDao
				.getParentArticleCategoryList(articleCategory);
		if (parentArticleCategoryList != null) {
			for (ArticleCategory parentArticleCategory : parentArticleCategoryList) {
				Hibernate.initialize(parentArticleCategory);
			}
		}
		return parentArticleCategoryList;
	}

	public List<ArticleCategory> getParentArticleCategoryList(Article article) {
		ArticleCategory articleCategory = article.getArticleCategory();
		List<ArticleCategory> articleCategoryList = new ArrayList<ArticleCategory>();
		articleCategoryList.addAll(this.getParentArticleCategoryList(articleCategory));
		articleCategoryList.add(articleCategory);
		return articleCategoryList;
	}

	public List<ArticleCategory> getArticleCategoryPathList(ArticleCategory articleCategory) {
		List<ArticleCategory> articleCategoryPathList = new ArrayList<ArticleCategory>();
		articleCategoryPathList.addAll(this.getParentArticleCategoryList(articleCategory));
		articleCategoryPathList.add(articleCategory);
		return articleCategoryPathList;
	}

	public List<ArticleCategory> getArticleCategoryPathList(Article article) {
		ArticleCategory articleCategory = article.getArticleCategory();
		List<ArticleCategory> articleCategoryList = new ArrayList<ArticleCategory>();
		articleCategoryList.addAll(this.getParentArticleCategoryList(articleCategory));
		articleCategoryList.add(articleCategory);
		return articleCategoryList;
	}

	public List<ArticleCategory> getChildrenArticleCategoryList(ArticleCategory articleCategory) {
		List<ArticleCategory> childrenArticleCategoryList = articleCategoryDao
				.getChildrenArticleCategoryList(articleCategory);
		if (childrenArticleCategoryList != null) {
			for (ArticleCategory childrenArticleCategory : childrenArticleCategoryList) {
				Hibernate.initialize(childrenArticleCategory);
			}
		}
		return childrenArticleCategoryList;
	}

	public List<ArticleCategory> getChildrenArticleCategoryList(Article article) {
		ArticleCategory articleCategory = article.getArticleCategory();
		List<ArticleCategory> articleCategoryList = getChildrenArticleCategoryList(articleCategory);
		if (articleCategoryList == null) {
			articleCategoryList = new ArrayList<ArticleCategory>();
		}
		articleCategoryList.add(articleCategory);
		return articleCategoryList;
	}

	public List<ArticleCategory> getArticleCategoryTreeList() {
		List<ArticleCategory> allArticleCategoryList = this.getAll();
		return recursivArticleCategoryTreeList(allArticleCategoryList, null, null);
	}


	/**
	 * @获取目录树
	 * @param parentId
	 * @return
	 */

	// 递归父类排序分类树
	private List<ArticleCategory> recursivArticleCategoryTreeList(List<ArticleCategory> allArticleCategoryList,
			ArticleCategory p, List<ArticleCategory> temp) {
//		if (temp == null) {
//			temp = new ArrayList<ArticleCategory>();
//		}
//		for (ArticleCategory articleCategory : allArticleCategoryList) {
////			ArticleCategory parent = articleCategory.getParent_articleCategory_id();
//			if ((p == null && parent == null) || (articleCategory != null && parent == p)) {
//				temp.add(articleCategory);
//				if (articleCategory.getChildren() != null && articleCategory.getChildren().size() > 0) {
//					recursivArticleCategoryTreeList(allArticleCategoryList, articleCategory, temp);
//				}
//			}
//		}
		return temp;
	}

	@Override
	public List<ArticleCategory> getAll() {
		List<ArticleCategory> allArticleCategoryList = articleCategoryDao.getAll();
		if (allArticleCategoryList != null) {
			for (ArticleCategory articleCategory : allArticleCategoryList) {
				Hibernate.initialize(articleCategory);
			}
		}
		return allArticleCategoryList;
	}

	@Override
	public void delete(ArticleCategory articleCategory) {
		articleCategoryDao.delete(articleCategory);
	}

	@Override
	public void delete(String id) {
		ArticleCategory articleCategory=articleCategoryDao.load(id);
		articleCategoryDao.delete(articleCategory);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			this.delete(id);
		}
	}

	@Override
	public String save(ArticleCategory articleCategory) {
		return articleCategoryDao.save(articleCategory);
	}

	@Override
	public void update(ArticleCategory articleCategory) {
		articleCategoryDao.update(articleCategory);
	}

	@Override
	public List<ArticleCategory> find(PageBean pageBean) {
		List<ArticleCategory> articleCategories=articleCategoryDao.find(pageBean);
		return articleCategories;
	}

	@Override
	public Long getTotalCount(){
		String hql = "select count(*) from " + ArticleCategory.class.getSimpleName();
		return baseDao.count(hql);
	}
	@Override
	public JSONArray getListByParentId(int i) {
		// TODO Auto-generated method stub
		return null;
	}


}