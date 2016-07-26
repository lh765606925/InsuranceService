package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.google.gson.reflect.TypeToken;
import com.insurance.model.Deposit;
import com.insurance.model.Finance;
import com.insurance.model.Role;
import com.insurance.service.FinanceService;
import com.insurance.service.SalesmanService;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;
import com.opensymphony.xwork2.ActionSupport;

@Controller
/**
 * 管理员Action
 * @author laohu
 * 2014-11-18
 */
public class FinanceAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Resource
	private FinanceService financeService;

	public SalesmanService getSalesmanService() {
		return salesmanService;
	}

	public void setSalesmanService(SalesmanService salesmanService) {
		this.salesmanService = salesmanService;
	}

	private HttpServletRequest request;
	private String ids;
	private List<Finance> financeList;
	private List<Deposit> depositList;

	public List<Deposit> getDepositList() {
		return depositList;
	}

	public void setDepositList(List<Deposit> depositList) {
		this.depositList = depositList;
	}

	private SalesmanService salesmanService;
	private Finance finance;

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	private Deposit deposit;
	private String offset;
	private String limit;

	/**
	 * 业务表单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showFinance() throws Exception {
		request.setAttribute("leftPage", "common/finance_left.jsp");
		request.setAttribute("mainPage", "financeManage.jsp");
		return SUCCESS;
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

	/**
	 * 余额查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showmoney() throws Exception {
		request.setAttribute("leftPage", "common/finance_left.jsp");
		return SUCCESS;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	/**
	 * 获取财务列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		/**
		 * 之前版本20150508
		 */
		try {
			if (offset == null) {
				offset = "0";
			}
			if (limit == null) {
				limit = "10";
			}
			int offSet = Integer.parseInt(offset);
			int liMit = Integer.parseInt(limit);
			long total = financeService.getFinanceCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<Finance> flist = financeService.findFinanceList();
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			jsonConfig.registerJsonValueProcessor(Role.class,
					new ObjectJsonValueProcessor(new String[] { "roleId",
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

	

	public List<Finance> getFinanceList() {
		return financeList;
	}

	public void setFinanceList(List<Finance> financeList) {
		this.financeList = financeList;
	}

	public Finance getFinance() {
		return finance;
	}

	public void setFinance(Finance finance) {
		this.finance = finance;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
