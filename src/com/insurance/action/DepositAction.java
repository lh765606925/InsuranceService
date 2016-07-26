package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.reflect.TypeToken;
import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.Role;
import com.insurance.service.DepositService;
import com.insurance.service.SalesmanService;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 存款Action
 * 
 * @author fly 2014-11-18
 */
public class DepositAction extends ActionSupport implements ServletRequestAware {

	@Resource
	private DepositService depositService;

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String ids;
	private List<Deposit> depositList;

	@Resource
	private SalesmanService salesmanService;
	private Deposit deposit;

	private String offset;
	private String limit;
	private String depositBean;

	private String page;
	private PageBean pageBean;
	public String getPage() {
		return page;
	}

	private String pagesize;
	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getPageindex() {
		return pageindex;
	}

	public void setPageindex(String pageindex) {
		this.pageindex = pageindex;
	}

	private String pageindex;

	public void setPage(String page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	private String salesman_id;

	public String getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(String salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getDepositBean() {
		return depositBean;
	}

	public void setDepositBean(String depositBean) {
		this.depositBean = depositBean;
	}

	/**
	 * 充值记录查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showDeposit() throws Exception {
		request.setAttribute("leftPage", "common/finance_left.jsp");
		request.setAttribute("mainPage", "depositManage.jsp");
		return SUCCESS;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	/**
	 * 移动端充值
	 * 
	 * @throws Exception
	 */
	public void addDepositByMobile() throws Exception {
		try {
			String result = null;
			System.out.println("充值接收：" + depositBean);
			if (depositBean == null) {
				result = "param is null";
			} else {
				// 充值动作
				result = depositService.addDeposit(depositBean);

			}
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			System.out.println("充值返回：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 移动端查询用户充值记录
	 * 
	 * @throws Exception
	 */
	public void selectSalesmanDepositByMobile() throws Exception {
		// String result = null;
		// System.out.println("移动端查询用户交易记录接收用户ID：" + salesman_id);
		// if (salesman_id == null) {
		// result = "salesman_id is null";
		// } else {
		// try {
		// List<Deposit> flist = depositService.findDepositById(salesman_id);
		//
		// JSONObject jsonObject = new JSONObject();
		// JsonConfig jsonConfig = new JsonConfig();
		// JSONArray rows = JSONArray.fromObject(flist, jsonConfig);
		// jsonObject.put("rows", rows);
		// ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// }
		// ResponseUtil.write(ServletActionContext.getResponse(), result);
		JSONObject jsonObject = new JSONObject();
		try {
			String result = null;
			if (salesman_id == null) {
				result = "salesman_id is null";
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			}
			if (pageindex == null) {
				pageindex = "0";
			}
			if (pagesize == null) {
				pagesize = "10";
			}

			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			long total = depositService.getDepositCount();
			int start = (pageIndex-1) * pageSize;
			if (start >= total && total != 0) {
				start = start - pageSize;
			}

			List<Deposit> dlist = depositService.findDepositList(new PageBean(pageSize, start), salesman_id);

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jsonConfig.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"roleId", "roleName"}, Role.class));
			JSONArray rows = JSONArray.fromObject(dlist, jsonConfig);
			// jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");
		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
	}

	/**
	 * 移动端分页查询用户充值记录
	 * 
	 * @throws Exception
	 */
	public void selectPagerDepositByMobile() throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (pagesize == null) {
				pagesize = "0";
			}
			if (pageindex == null) {
				pageindex = "10";
			}

			int pageSize = Integer.parseInt(pagesize);
			int pageIndex = Integer.parseInt(pageindex);
			long total = depositService.getDepositCount();
			int start = (pageIndex - 1) * pageSize;
			if (start >= total && total != 0) {
				start = start - pageSize;
			}
			List<Deposit> dlist = depositService.findDepositList(new PageBean(pageSize, start), salesman_id);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jsonConfig.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"roleId", "roleName"}, Role.class));
			JSONArray rows = JSONArray.fromObject(dlist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			jsonObject.put("success", "1");
		} catch (Exception e) {
			jsonObject.put("success", "0");
			e.printStackTrace();
		} finally {
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		}
	}

	/**
	 * 获取充值列表
	 * 
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
			long total = depositService.getDepositCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<Deposit> flist = depositService.findDepositList(new PageBean(liMit, offSet));
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jsonConfig.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"roleId", "roleName"}, Role.class));
			JSONArray rows = JSONArray.fromObject(flist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String recharge() throws Exception {
		return null;
	}

	// 修改金额接收字段 （id，money）
	private String moneypass;

	public String getMoneypass() {
		return moneypass;
	}

	public void setMoneypass(String moneypass) {
		this.moneypass = moneypass;
	}

	/**
	 * 移动端修改充值金额
	 * 
	 * @throws Exception
	 */
	public void modifyPass() throws Exception {
		String result = null;
		TypeToken<String[]> type = new TypeToken<String[]>() {
		};
		String[] pw = Return_Code.getGson().fromJson(moneypass, type.getType());
		System.out.println("修改密码接收phone：" + pw[0]);
		System.out.println("修改密码接收newpass：" + pw[1]);
		if (moneypass == null) {
			result = "param is null";
		} else {
			result = salesmanService.moneyPass(pw);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		System.out.println("金额保存后返回：" + result);
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Deposit> getDepositList() {
		return depositList;
	}

	public void setDepositList(List<Deposit> depositList) {
		this.depositList = depositList;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

}
