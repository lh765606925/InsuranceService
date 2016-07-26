package com.insurance.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.insurance.bean.ProductImage;
import com.insurance.bean.SystemConfig;
import com.insurance.model.Article;
import com.insurance.service.ArticleCategoryService;
import com.insurance.service.ArticleService;
import com.insurance.service.BaseService;
import com.insurance.service.ProductImageService;
import com.insurance.util.DateUtil;
import com.insurance.util.PageUtil;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.ResponseUtil;
import com.insurance.util.StringUtil;
import com.insurance.util.SystemConfigUtil;

/**
 * 后台Action类 - 文章
 */

public class ArticleAction extends BaseAction{

	private static final long serialVersionUID = -6825456589196458406L;
	private File[] productImages;
	private String[] productImagesFileName;
	private String[] productImageParameterTypes;
	private String[] productImageIds;
	private Article article;// 文章
	private File head_Pic;// 文章头部图片
	private String hot_total;// 热门文章条数
	private String articlecategory_id;// 栏目ID

	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleCategoryService articleCategoryService;
	@Resource
	private ProductImageService productImageService;
	@Resource
	private BaseService<Article, String> baseService;

	/**
	 * @category 添加文章
	 * @throws Exception
	 * 
	 * 
	 * 
	 */
	public String add() throws Exception {
		JSONObject result = new JSONObject();
		try {
			article.setCreateDate(DateUtil.getCurrentDateStr1());
			article.setHits(0);
			article.setPageCount(1);
			article.setIsPublication(true);
			articleService.save(article);
			result.put("success", true);
			if (head_Pic != null) {
				PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
				String dir = "/" + propertiesUtil.readValue("data_path") + "/article_img/";
				String path = StringUtil.getWebApp() + dir;
				// 写到指定的路径中
				File file = new File(path);
				// 如果指定的路径没有就创建
				if (!file.exists())
					file.mkdirs();
				// 获取当前16位随机数为命名参数
				String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(DateUtil.GetDateRandom16());
				FileUtils.copyFile(head_Pic, new File(file, fileName1));
				article.setProductImageListStore(dir + fileName1);
				articleService.save(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}

	// 编辑
	public String edit() {
		article = articleService.load(id);
		return INPUT;
	}

	/**
	 * @category 后台查询所有文章
	 * @return
	 * @throws Exception
	 */
	public void admin_show_list() throws Exception {
		long total = articleService.getTotalCount();
		List<Article> slist = articleService.find(PageUtil.getAdmin_PageBean(offset, limit, total));
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
	}

	/**
	 * @category APP查询热门文章
	 * @return
	 * @throws Exception
	 */
	public String App_show_hot_list() throws Exception {
		int total;
		if (StringUtil.isNotEmpty(hot_total)) {
			try {
				total = Integer.parseInt(hot_total);
			} catch (Exception e) {
				total = 5;
			}
		} else {
			total = 5;
		}
		List<Article> slist = articleService.getHotArticleList(total);// 查询5天热门数据，
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", slist.size());
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * @category  App查询文章列表 BY 文章栏目，分页查询文章（标题和ID）
	 * @param 4028811650c5c0680150c5c4b82e0000
	 * @case http://192.168.1.150:8080/InsuranceService/back/article_app_show_list_by_articlecategory.action?articlecategory_id=4028811650c5c0680150c5c4b82e0000
	 * @return
	 * @throws Exception
	 */
	public String app_show_list_by_articlecategory() throws Exception {
		System.out.println("this is the method " + this.getClass().getSimpleName()
				+ ".app_show_list_by_articlecategory");
		if (StringUtil.isEmpty(articlecategory_id)) {
			return "参数\"articlecategory_id\"不正确";
		}
		long total = articleService.getTotalCount(articlecategory_id);
		List<Article> tlist = articleService.find_title(PageUtil.getApp_PageBean(pagesize, pageindex, total),
				articlecategory_id);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray rows = JSONArray.fromObject(tlist, jsonConfig);
		jsonObject.put("total", tlist.size());
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * @category App端查询文章详情
	 * @param 文章ID=4028811650adb51a0150adb7968b0001
	 * @case http://192.168.1.150:8080/InsuranceService/back/article_app_show_detail.action?id=4028811650adb51a0150adb7968b0001
	 * @return
	 * @throws Exception
	 */
	public String app_show_detail() throws Exception {
		System.out.println("this is the method " + this.getClass().getSimpleName() + ".app_show_detail");
		if (StringUtil.isEmpty(id)) {
			return "参数\"id\"不正确";
		}
		Article article = articleService.getArticleById(id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("article", article);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * @category 后台添加文章
	 * @return
	 * @throws Exception
	 */
	public String admin_input() throws Exception {

		return SUCCESS;
	}

	public String save() throws Exception {
		// product photos upload ----------------------------
		if (productImages != null) {
			// System.out.println("admin/articleAction.java: "+ productImages.toString());
			String allowedUploadImageExtension = SystemConfigUtil.getSystemConfig().getAllowedUploadImageExtension()
					.toLowerCase();
			if (StringUtils.isEmpty(allowedUploadImageExtension)) {
				addActionError("不允许上传图片文件!");
				return ERROR;
			}
			for (int i = 0; i < productImages.length; i++) {
				System.out.println(productImagesFileName[i]);
				String productImageExtension = StringUtils.substringAfterLast(productImagesFileName[i], ".")
						.toLowerCase();
				String[] imageExtensionArray = allowedUploadImageExtension.split(SystemConfig.EXTENSION_SEPARATOR);
				if (!ArrayUtils.contains(imageExtensionArray, productImageExtension)) {
					addActionError("只允许上传图片文件类型: " + allowedUploadImageExtension + "!");
					return ERROR;
				}
				if (SystemConfigUtil.getSystemConfig().getUploadLimit() != 0
						&& productImages[i].length() > SystemConfigUtil.getSystemConfig().getUploadLimit() * 1024) {
					addActionError("此上传文件大小超出限制!");
					return ERROR;
				}
			}
		}

		List<ProductImage> productImageList = new ArrayList<ProductImage>();
		if (productImages != null && productImages.length > 0) {
			for (int i = 0; i < productImages.length; i++) {
				ProductImage productImage = productImageService.buildProductImage(productImages[i]);
				productImageList.add(productImage);
			}
		}

//		article.setProductImageList(productImageList);
		// product photos upload end --------------------------------
		return SUCCESS;
	}

	/**
	 * @category 后台:文章管理
	 * @return
	 * @throws Exception
	 */
	public String admin_manage() throws Exception {
		request.setAttribute("leftPage", "article/left.jsp");
		request.setAttribute("mainPage", "article/manage.jsp");
		return SUCCESS;
	}

	// 删除
	public void delete() throws Exception {
		try {
			JSONObject result = new JSONObject();
			if (ids.contains(",")) {
				String[] is = ids.split(";");
				articleService.delete(is);
			} else {
				articleService.delete(ids);
			}
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File[] getProductImages() {
		return productImages;
	}

	public void setProductImages(File[] productImages) {
		this.productImages = productImages;
	}

	public String[] getProductImagesFileName() {
		return productImagesFileName;
	}

	public void setProductImagesFileName(String[] productImagesFileName) {
		this.productImagesFileName = productImagesFileName;
	}

	public String[] getProductImageParameterTypes() {
		return productImageParameterTypes;
	}

	public void setProductImageParameterTypes(String[] productImageParameterTypes) {
		this.productImageParameterTypes = productImageParameterTypes;
	}

	public String[] getProductImageIds() {
		return productImageIds;
	}

	public void setProductImageIds(String[] productImageIds) {
		this.productImageIds = productImageIds;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public File getHead_Pic() {
		return head_Pic;
	}

	public void setHead_Pic(File head_Pic) {
		this.head_Pic = head_Pic;
	}

	public String getHot_total() {
		return hot_total;
	}

	public void setHot_total(String hot_total) {
		this.hot_total = hot_total;
	}

	public String getArticlecategory_id() {
		return articlecategory_id;
	}

	public void setArticlecategory_id(String articlecategory_id) {
		this.articlecategory_id = articlecategory_id;
	}
}