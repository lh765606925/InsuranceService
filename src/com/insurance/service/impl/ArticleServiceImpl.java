package com.insurance.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.ArticleCategoryDao;
import com.insurance.dao.ArticleDao;
import com.insurance.dao.BaseDao;
import com.insurance.model.Article;
import com.insurance.model.ArticleCategory;
import com.insurance.model.PageBean;
import com.insurance.model.Pager;
import com.insurance.service.ArticleService;

/**
 * Service实现类 - 文章
 */

@Service("ArticleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements ArticleService {

	@Resource
	private ArticleDao articleDao;

	@Resource
	private ArticleCategoryDao articleCategoryDao;

	@Resource
	private BaseDao<Article, String> baseDao;

	public List<Article> getArticleList(ArticleCategory articleCategory) {
		return articleDao.getArticleList(articleCategory);
	}

	public List<Article> getArticleList(int firstResult, int maxResults) {
		return articleDao.getArticleList(firstResult, maxResults);
	}

	public List<Article> getArticleList(Date beginDate, Date endDate, int firstResult, int maxResults) {
		return articleDao.getArticleList(beginDate, endDate, firstResult, maxResults);
	}

	public List<Article> getArticleList(ArticleCategory articleCategory, int firstResult, int maxResults) {
		return articleDao.getArticleList(articleCategory, firstResult, maxResults);
	}

	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager) {
		return articleDao.getArticlePager(articleCategory, pager);
	}

	public List<Article> getRecommendArticleList(int maxResults) {
		return articleDao.getRecommendArticleList(maxResults);
	}

	public List<Article> getRecommendArticleList(ArticleCategory articleCategory, int maxResults) {
		return articleDao.getRecommendArticleList(articleCategory, maxResults);
	}

	public List<Article> getHotArticleList(int maxResults) {
		return articleDao.getHotArticleList(maxResults);
	}

	public List<Article> getHotArticleList(ArticleCategory articleCategory, int maxResults) {
		return articleDao.getHotArticleList(articleCategory, maxResults);
	}

	public List<Article> getNewArticleList(int maxResults) {
		return articleDao.getNewArticleList(maxResults);
	}

	public List<Article> getNewArticleList(ArticleCategory articleCategory, int maxResults) {
		return articleDao.getNewArticleList(articleCategory, maxResults);
	}

	// 根据ID删除指定文章
	@Override
	public void delete(String id) {
		Article article = articleDao.load(id);
		articleDao.delete(article);
	}

	// 批量删除
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			this.delete(id);
		}
	}

	@Override
	public Pager search(Pager pager) {
		return null;
	}

	public List<Article> find(PageBean pageBean) {
		List<Article> articles = baseDao.find(pageBean);
		for (int j = 0; j < articles.size(); j++) {
			Article article = articles.get(j);
			System.out.println(article.getArticleCategory_id());
			ArticleCategory articleCategory = articleCategoryDao.findbyId(article.getArticleCategory_id());
			article.setArticleCategory(articleCategory);
			articles.set(j, article);
		}
		return articles;
	}

	@Override
	public Long getTotalCount() {
		String hql = "select count(*) from " + Article.class.getSimpleName();
		return baseDao.count(hql, null);
	}

	@Override
	public List<Article> findById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(Article article) {
		return articleDao.save(article);
	}

	@Override
	public long getTotalCount(String articlecategory_id) {
		String hql = "select count(*) from Article as ar where ar.articleCategory_id=?";
		return baseDao.count(hql, new Object[] { articlecategory_id });
	}

	@Override
	public List<Article> find(PageBean app_PageBean, String articlecategory_id) {
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public List<Article> find_title(PageBean app_PageBean, String articlecategory_id) {
		List<Article> articles = articleDao.find_title(app_PageBean, articlecategory_id);
		return articles;
	}

	@Override
	public Article getArticleById(String id) {
		Article articles = articleDao.getArticleById(id);
		return articles;
	}

}