package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.model.Article;
import com.insurance.model.ArticleCategory;
import com.insurance.service.ArticleCategoryService;
import com.insurance.service.ArticleService;
import com.insurance.util.DateUtil;
import com.insurance.util.PageUtil;
import com.insurance.util.ResponseUtil;

/**
 * 后台Action类 - 文章分类
 */

public class ArticleCategoryAction extends BaseAction {

	private static final long serialVersionUID = -6825456589196458406L;

	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleCategoryService articleCategoryService;

	private ArticleCategory articleCategory;

	// 添加
	public String add() {
		return INPUT;
	}

	// 添加
	public String insert() throws Exception {
		JSONObject result = new JSONObject();
		try {
			articleCategory.setCreateDate(DateUtil.getCurrentDateStr1());
			articleCategoryService.save(articleCategory);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}

	// 更新
	public String update() throws Exception {
		return SUCCESS;
	}

	// 删除
	public void delete() {
		try {
			JSONObject result = new JSONObject();
			if (ids.contains(",")) {
				String[] is = ids.split(";");
				articleCategoryService.delete(is);
			} else {
				articleCategoryService.delete(ids);
			}
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @category 后台查询所有栏目
	 * @return
	 * @throws Exception
	 */
	public void admin_show_list() throws Exception {
		// TEST
		System.out.println("this is class:" + this.getClass().getSimpleName() + " method:admin_show_list!");
		long total = articleCategoryService.getTotalCount();
		List<ArticleCategory> slist = articleCategoryService.find(PageUtil.getPageBean(offset, limit, total));
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
	}

	

	public void app_show_list() throws Exception {
		// TEST
		System.out.println("this is class:" + this.getClass().getSimpleName() + " method:app_show_list!");
		long total = articleCategoryService.getTotalCount();
		List<ArticleCategory> slist = articleCategoryService.find(PageUtil.getPageBean(offset, limit, total));
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		
	}
	
	
	public String admin_show_jsp_list() throws Exception{
		long total = articleCategoryService.getTotalCount();
		List<ArticleCategory> slist = articleCategoryService.find(PageUtil.getPageBean(offset, limit, total));
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("message", "");
		jsonObject.put("value", rows);
		jsonObject.put("code", 200);
		jsonObject.put("redirect", "");
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}
	
	
	/**
	 * @category 后台:文章管理
	 * @return
	 * @throws Exception
	 */
	public String admin_manage() throws Exception {
		request.setAttribute("leftPage", "article/left.jsp");
		request.setAttribute("mainPage", "articlecategory/manage.jsp");
		return SUCCESS;
	}

	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
}