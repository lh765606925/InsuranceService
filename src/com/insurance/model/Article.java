package com.insurance.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.insurance.bean.ProductImage;

/**
 * 实体类 - 文章
 */

@Entity
public class Article extends BaseEntity {

	private static final long serialVersionUID = 1475773294701585482L;

	public static final int MAX_RECOMMEND_ARTICLE_LIST_COUNT = 20;// 推荐文章列表最大文章数
	public static final int MAX_HOT_ARTICLE_LIST_COUNT = 20;// 热点文章列表最大文章数
	public static final int MAX_NEW_ARTICLE_LIST_COUNT = 20;// 最新文章列表最大文章数
	public static final int MAX_PAGE_CONTENT_COUNT = 2000;// 内容分页每页最大字数
	public static final int DEFAULT_ARTICLE_LIST_PAGE_SIZE = 20;// 文章列表默认每页显示数

	private String title;// 标题
	private String author;// 作者
	private String content;// 内容
	private String metaKeywords;// 页面关键词
	private String metaDescription;// 页面描述
	private Boolean isPublication;// 是否发布
	private Boolean isTop;// 是否置顶
	private Boolean isRecommend;// 是否为推荐文章
	private Integer pageCount;// 文章页数
	private String htmlFilePath;// HTML静态文件路径（首页）
	private Integer hits;// 点击数
	private String articleCategory_id;// 文章栏目ID

	private ArticleCategory articleCategory;// 文章分类

	@Column(name = "productImageListStore", length = 10000)
	private String productImageListStore;// 图片路径存储

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(length = 10000, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	public Boolean getIsPublication() {
		return isPublication;
	}

	public void setIsPublication(Boolean isPublication) {
		this.isPublication = isPublication;
	}

	@Column(nullable = false)
	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	@Column(nullable = false)
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(nullable = false)
	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@JoinColumn(nullable = false, updatable = false)
	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

	@Column(nullable = false)
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
	@Transient
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	public String getArticleCategory_id() {
		return articleCategory_id;
	}

	public void setArticleCategory_id(String articleCategory_id) {
		this.articleCategory_id = articleCategory_id;
	}

	// photo begin ---------------------------------
	// 图片URL存放字段

	@Column(length = 500)
	public String getProductImageListStore() {
		return productImageListStore;
	}

	public void setProductImageListStore(String productImageListStore) {
		this.productImageListStore = productImageListStore;
	}

//	// 获取商品图片
//	@SuppressWarnings("unchecked")
//	@Transient
//	public List<ProductImage> getProductImageList() {
//		if (StringUtils.isEmpty(productImageListStore)) {
//			return null;
//		}
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setRootClass(ProductImage.class);
//		JSONArray jsonArray = JSONArray.fromObject(productImageListStore);
//		return (List<ProductImage>) JSONSerializer.toJava(jsonArray, jsonConfig);
//	}
//
//	// 设置商品图片
//	@Transient
//	public void setProductImageList(List<ProductImage> productImageList) {
//		if (productImageList == null || productImageList.size() == 0) {
//			productImageListStore = null;
//			return;
//		}
////		System.out.println("Acticle.java: " + JSONArray.fromObject(productImageList).toString());
//		JSONArray jsonArray = JSONArray.fromObject(productImageList);
//
//		productImageListStore = jsonArray.toString();
//	}
//
//	/**
//	 * 根据商品图片ID获取商品图片，未找到则返回null
//	 * 
//	 * @param ProductImage
//	 *            ProductImage对象
//	 */
//	@Transient
//	public ProductImage getProductImage(String productImageId) {
//		List<ProductImage> productImageList = getProductImageList();
//		for (ProductImage productImage : productImageList) {
//			if (StringUtils.equals(productImageId, productImage.getId())) {
//				return productImage;
//			}
//		}
//		return null;
//	}
	// photo end ---------------------------------

}