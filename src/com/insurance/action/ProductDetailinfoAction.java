package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.model.PageBean;
import com.insurance.model.ProductDetailinfo;
import com.insurance.model.Role;
import com.insurance.service.ProductDetailinfoService;
import com.insurance.util.DateUtil;
import com.insurance.util.ResponseUtil;

/**
 * 产品详情ACTION
 * 
 * @author laohu
 * 
 */
public class ProductDetailinfoAction extends BaseAction {
	private static final long serialVersionUID = 6119483774845476243L;

	@Resource
	private ProductDetailinfoService productDetailinfoService;

	private ProductDetailinfo productDetailinfo;

	private List<String> detailname; // 责任详情名称
	private List<String> detailcontent; // 责任详情名称
	private List<String> detailamout; // 责任详情名称
	private String product_id;// 查询产品详情时产品ID

	private String ids;
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	/**
	 * 查询产品详情信息
	 * 
	 */
	public String addDetailProduct() throws Exception {
		request.setAttribute("leftPage", "product/left.jsp");
		request.setAttribute("mainPage", "product/addDetailProductInfo.jsp");
		return SUCCESS;
	}
	/**
	 * 查询产品详情信息
	 * 
	 */
	public String showlist() throws Exception {
		request.setAttribute("leftPage", "product/left.jsp");
		request.setAttribute("mainPage", "product/ProductDetailinfoManage.jsp");
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
			System.out.println(product_id);
			List<ProductDetailinfo> flist = productDetailinfoService.findProductDetailinfoByProductId(product_id);
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			JSONArray rows = JSONArray.fromObject(flist, jsonConfig);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String findlist() throws Exception {
		try {
			if (offset == null) {
				offset = "0";
			}
			if (limit == null) {
				limit = "10";
			}
			int offSet = Integer.parseInt(offset);
			int liMit = Integer.parseInt(limit);
			long total = productDetailinfoService.getProductCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			// List<Product> flist = productService.findProductList(new
			// PageBean(liMit, offSet));

			List<ProductDetailinfo> flist = productDetailinfoService.findProductDetailinfo(new PageBean(liMit, offSet),
					null);
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
					"yyyy-MM-dd HH:mm:ss"));
			jsonConfig.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"roleId",
					"roleName"}, Role.class));
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
	 * 删除产品产品详情
	 * 
	 * @return 是否成功
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			JSONObject result = new JSONObject();
			if (ids.contains(",")) {
				productDetailinfoService.deletes(ids);
			} else {
				ProductDetailinfo a = productDetailinfoService.findById(ids);
				productDetailinfoService.delete(a);
			}
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加新产品详情
	 * 
	 * @return
	 * @throws Exception
	 */

	public String insert() throws Exception {
		// 查询产品是否存在
		try {
			productDetailinfoService.insert(productDetailinfo);
			JSONObject result = new JSONObject();
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			ProductDetailinfo pr = productDetailinfoService.findById(productDetailinfo.getId());
			if (pr == null) {
				return "产品不存在！";
			} else {

				// 获取当前时间
				productDetailinfo.setModifyDate(DateUtil.getCurrentDateStr1());
				productDetailinfoService.update(productDetailinfo);
				JSONObject result = new JSONObject();
				result.put("success", true);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getDetailname() {
		return detailname;
	}

	public void setDetailname(List<String> detailname) {
		this.detailname = detailname;
	}

	public List<String> getDetailcontent() {
		return detailcontent;
	}

	public void setDetailcontent(List<String> detailcontent) {
		this.detailcontent = detailcontent;
	}

	public List<String> getDetailamout() {
		return detailamout;
	}

	public void setDetailamout(List<String> detailamout) {
		this.detailamout = detailamout;
	}

	public ProductDetailinfo getProductDetailinfo() {
		return productDetailinfo;
	}

	public void setProductDetailinfo(ProductDetailinfo productDetailinfo) {
		this.productDetailinfo = productDetailinfo;
	}
}
