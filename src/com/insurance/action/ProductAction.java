package com.insurance.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.insurance.model.PageBean;
import com.insurance.model.Product;
import com.insurance.model.Role;
import com.insurance.service.ProductService;
import com.insurance.util.DateUtil;
import com.insurance.util.ListToPageByJson;
import com.insurance.util.PageUtil;
import com.insurance.util.ResponseUtil;
import com.insurance.util.StringUtil;
import com.insurance.util.SundryTest;
import com.opensymphony.xwork2.ActionSupport;
import com.foresealife.damsfront.webservice.AsyncOrderData;
import com.foresealife.damsfront.webservice.AsyncOrderDataResponse;
import com.foresealife.damsfront.webservice.PlantformserviceStub;
import com.insurance.util.MD5Utils;

/**
 * @name 产品Action
 * @author huzhihong
 *
 * @modifyDate：2015年11月13日
 */
public class ProductAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int HOT_ARTICLE_DEFAULT_TOTAL_VALUE = 2;// 热门文章默认条数
	private HttpServletRequest request;
	private String offset;
	private String limit;
	private String[] ids;

	private String product_id;
	private String productBrand;// 产品品牌

	private File idPic;// 产品缩略图
	@Resource
	private ProductService productService;
	private String hot_total;
	private String pagesize;
	private String pageindex;
	private long total;

	/**
	 * 产品查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showProduct() throws Exception {
		request.setAttribute("leftPage", "product/left.jsp");
		request.setAttribute("mainPage", "product/Manage.jsp");
		return SUCCESS;
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showProductDetail() throws Exception {
		if (product_id != null) {
			Product product = productService.getProduct(product_id);
			request.setAttribute("product", product);
		}
		// request.setAttribute("leftPage", "product/left.jsp");
		// request.setAttribute("mainPage", "product/Detail.jsp");
		return "Detail";
	}

	/**
	 * @category   后台添加产品信息
	 */
	public String addProduct() throws Exception {
		request.setAttribute("leftPage", "product/left.jsp");
		request.setAttribute("mainPage", "product/addProduct.jsp");
		return SUCCESS;
	}

	/**
	 * 获取产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		try {
			if (offset == null) {
				offset = "0";
			}
			if (limit == null) {
				limit = "10";
			}
			int offSet = Integer.parseInt(offset);
			int liMit = Integer.parseInt(limit);
			long total = productService.getProductCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<Product> flist = productService.findProductList(new PageBean(liMit, offSet));
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			jsonConfig.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[] { "roleId",
					"roleName" }, Role.class));
			JSONArray rows = JSONArray.fromObject(flist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @category APP查询最新产品。按是否置顶排名，默认2条，
	 * @case http://113.105.94.20:8080/InsuranceService/back/product_App_show_hot_list.action?hot_total=2
	 * @return 
	 * @throws Exception
	 */
	public String App_show_hot_list() throws Exception {
		int total;
		if (StringUtil.isNotEmpty(hot_total)) {
			try {
				total = Integer.parseInt(hot_total);
			} catch (Exception e) {
				total = HOT_ARTICLE_DEFAULT_TOTAL_VALUE;
			}
		} else {
			total = HOT_ARTICLE_DEFAULT_TOTAL_VALUE;
		}
		List<Product> slist = productService.getHotProductList(total);// 查询2天热门数据，
		ListToPageByJson<Product> toPageByJson = new ListToPageByJson<Product>();
		toPageByJson.putToJson(total, slist);
		return null;
	}

	/**
	 * @category APP按品牌查询最新产品
	 * @case http://192.168.1.150:8080/InsuranceService/back/product_app_show_list_brand.action?productBrand=0B527D837CC74139B21CD4A3DB85D06E
	 * @param productBrand (产品品牌)
	 * @前海:  0B527D837CC74139B21CD4A3DB85D06E
	 * @平安:  0B527D837CC74139B21CD4A3DB85D063
	 * @throws Exception
	 */
	public String app_show_list_brand() throws Exception {
		List<Product> slist = productService.findProduct_by_brand(PageUtil.getApp_PageBean(pagesize, pageindex, total),
				new Object[] { productBrand });//
		ListToPageByJson<Product> toPageByJson = new ListToPageByJson<Product>();
		toPageByJson.putToJson(slist);
		return null;
	}

	/**
	 * @name 移动端查询产品列表
	 * @return
	 * @throws Exception
	 */
	public String mobile_showProduct() throws Exception {
		try {
			// 产品分类查询 人寿险 车险 综合险
			List<Product> plist_rsx = productService.findProductListByProductType("人寿险");
			List<Product> plist_cx = productService.findProductListByProductType("车险");
			List<Product> plist_zhx = productService.findProductListByProductType("综合险");

			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows_rsx = JSONArray.fromObject(plist_rsx, jsonConfig);
			JSONArray rows_cx = JSONArray.fromObject(plist_cx, jsonConfig);
			JSONArray rows_zhx = JSONArray.fromObject(plist_zhx, jsonConfig);
			jsonObject.put("rows_cx", rows_cx);
			jsonObject.put("rows_rsx", rows_rsx);
			jsonObject.put("rows_zhx", rows_zhx);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取人寿险列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mobile_showProductRSX() throws Exception {
		try {
			List<Product> plist_rsx = productService.findProductListByProductType("人寿险");
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows_rsx = JSONArray.fromObject(plist_rsx, jsonConfig);
			jsonObject.put("rows_rsx", rows_rsx);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取综合险列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mobile_showProductZHX() throws Exception {
		try {
			List<Product> plist_zhx = productService.findProductListByProductType("综合险");
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows_zhx = JSONArray.fromObject(plist_zhx, jsonConfig);
			jsonObject.put("rows_zhx", rows_zhx);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *@name  获取车险列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String mobile_showProductCX() throws Exception {
		try {
			List<Product> plist_cx = productService.findProductListByProductType("车险");
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray rows_cx = JSONArray.fromObject(plist_cx, jsonConfig);
			jsonObject.put("rows_cx", rows_cx);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@SuppressWarnings("static-access")
	public String sendToWebService() throws Exception {

		String testjson2 = "{'policyInfo': {'amount': '0.01','applyCode': '9702004173','channelId': '0e3c5166ac54470cb1ba7b8d6d3ec126','endDate': '2015-07-16','salesCode': '300000030','flowCode': '04','insuredAndBeneficiaryList': [{'insuredRule': {'benefits': {},'choiceDuty': {},'outPut': {}},'policyInsured': {'birthdate': '1989-11-12','certEndDate': '','certStartDate': '','certValidFlag': '1','certificateNo': '360424198911322318','certificateType': '201','chiefInsuredRelation': '','city': '','count': 1,'county': '','detailAddress': '','email': '','holderRelation': '301','id': '"
				+ SundryTest.uuid()
				+ "','isChiefInsured': '','isLegal': '1','moblie': '15879041340','name': 'kuangyu','nationality': '','policyId': '"
				+ SundryTest.uuid()
				+ "','premium': '0.01','profession': '','province': '','residentCity': '440300','residentProvince': '440000','sex': '1','sort': '','supplierBranch': '440300','telephone': '','zipNo': ''}}],'insuredSelect': '2','isLegal': '1','orderCharge': {'buyerAccountUsername': '','payAmount': '','payBusinessCode': '','payCode': '','payMethod': '','payer': '','sellerAccountUsername': ''},'orderCode': '9702004173','orderId': '"
				+ SundryTest.uuid()
				+ "','policyHolder': {'birthdate': '1989-11-12','certEndDate': '','certStartDate': '','certValidFlag': '2','certificateNo': '360424198911122318','certificateType': '201','city': '','county': '','detailAddress': '',"
				+ "'email': '327127640@qq.com',"
				+ "'id': '"
				+ SundryTest.uuid()
				+ "','moblie': '13268527439','name': 'laohu','nationality': '','policyId': '"
				+ SundryTest.uuid()
				+ "','profession': '','province': '','residentCity': '','residentProvince': '','sex': '2','supplierBranch': '440300','telephone': '','zipNo': ''},'policyId': '"
				+ SundryTest.uuid() + "','productId': '292ea6c4ea86419799e3e40ce6d68a22','startDate': '2014-07-17'}}";

		System.out.println(testjson2);
		// UTF-8传输
		try {
			// testjson = new String(testjson.getBytes("UTF-8"));
			testjson2 = new String(testjson2.getBytes("UTF-8"));

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		/**
		 * 功能方法测试
		 */
		PlantformserviceStub plantformservicestub = new PlantformserviceStub();
		AsyncOrderData asyncorderdata = new AsyncOrderData();

		// MD5
		MD5Utils md5utils = new MD5Utils();
		String md5str = "";
		try {
			md5str = md5utils.createMD5(testjson2 + "qinghaimd5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		testjson2 = "JSON||04||" + testjson2;
		asyncorderdata.setJsonstr(testjson2);
		asyncorderdata.setMd5Salt(md5str);

		AsyncOrderDataResponse asyncorderdataresponse;
		asyncorderdataresponse = plantformservicestub.asyncOrderData(asyncorderdata);
		System.out.println("第三方客户端测试:+++++" + asyncorderdataresponse.get_return());

		ResponseUtil.write(ServletActionContext.getResponse(), asyncorderdataresponse.get_return());

		return null;
	}

	/**
	 * 添加新产品
	 * @category 插入产品信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		try {
			if (product == null)
				return "param  product is null~";

			// 判断如果有产品ID，查询ID对应产品是否存在
			if (product.getId() != null) {
				Product pr = productService.findById(product.getId());
				if (pr != null)
					return "产品已存在！";
			}
			int sid = productService.insert(product);
			String dir = "/InsuranceData/product/" + sid;
			String path = StringUtil.getWebApp() + dir;
			if (idPic != null) {
				// 写到指定的路径中
				File file = new File(path);
				// 如果指定的路径没有就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					// 获取当前16位随机数为命名参数
					String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(DateUtil.GetDateRandom16());
					FileUtils.copyFile(idPic, new File(file, fileName1));
					product.setProductImgList(dir + "/" + fileName1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productService.update(product);
			JSONObject result = new JSONObject();
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加新产品
	 * @category jsp插入产品信息
	 * @return
	 * @throws Exception
	 */
	public String insert_jsp() throws Exception {
		try {
			if (product == null)
				return "param  product is null~";

			// 判断如果有产品ID，查询ID对应产品是否存在
			if (product.getId() != null) {
				Product pr = productService.findById(product.getId());
				if (pr != null)
					return "产品已存在！";
			}
			int sid = productService.insert(product);
			String dir = "/InsuranceData/product/" + sid;
			String path = StringUtil.getWebApp() + dir;
			if (idPic != null) {
				// 写到指定的路径中
				File file = new File(path);
				// 如果指定的路径没有就创建
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					// 获取当前16位随机数为命名参数
					String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(DateUtil.GetDateRandom16());
					FileUtils.copyFile(idPic, new File(file, fileName1));
					product.setProductImgList(dir + "/" + fileName1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productService.update(product);
			JSONObject result = new JSONObject();
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除产品
	 * 
	 * @return 是否成功
	 * @throws Exception
	 */
//	public String delete() throws Exception {
//		try {
//			JSONObject result = new JSONObject();
//			if (ids.contains(",")) {
//				productService.deletes(ids);
//			} else {
//				Product a = productService.findById(ids);
//				productService.delete(a);
//			}
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	public String delete() throws Exception {
		String ids1 = "";
		JSONObject result = new JSONObject();
		if (ids.length == 1) {
			ids1 = ids[0].replace("[", "");
			ids1 = ids1.replace("]", "");
			ids = ids1.split(",");
		}
		if (ids.length == 1) {
			productService.deletes(ids[0]);
		} else if (ids.length > 1) {
			productService.deletes(ids1);
		} else {
			result.put("success", false);
			result.put("msg", "用户ID不能为空");
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public String sendToQianHai() {
		return SUCCESS;
	}

	/**
	 * 修改产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		try {
			// 查询产品是否存在
			Product pr = productService.findById(product.getId());
			if (pr == null) {
				return "产品不存在！";
			} else {

				// 获取当前时间
				product.setModifyDate(DateUtil.getCurrentDateStr1());
//				productService.update(product);
				String dir = "/InsuranceData/product/";
				String path = StringUtil.getWebApp() + dir;
				if (idPic != null) {
					// 写到指定的路径中
					File file = new File(path);
					// 如果指定的路径没有就创建
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						String fileName1 = DateUtil.getDateKey() + StringUtil.getSuffix(DateUtil.GetDateRandom16());
						FileUtils.copyFile(idPic, new File(file, fileName1));
						product.setProductImgList(dir + "/" + fileName1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					Product product_forimg = productService.findById(product.getId());
					product.setProductImgList(product_forimg.getProductImgList());
				}
				productService.update(product);
//				JSONObject result = new JSONObject();
//				result.put("success", true);
//				ResponseUtil.write(ServletActionContext.getResponse(), result);
//				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public File getIdPic() {
		return idPic;
	}

	public void setIdPic(File idPic) {
		this.idPic = idPic;
	}

	private PageBean pageBean;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	private Product product;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getHot_total() {
		return hot_total;
	}

	public void setHot_total(String hot_total) {
		this.hot_total = hot_total;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
