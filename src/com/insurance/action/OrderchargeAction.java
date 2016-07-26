package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import com.insurance.model.OrderCharge;
import com.insurance.model.PageBean;
import com.insurance.service.OrderchargeService;
import com.insurance.util.ResponseUtil;
import com.insurance.util.Return_Code;
/**
 * 支付信息action
 * 
 * @author laohu
 *
 */
public class OrderchargeAction extends BaseAction {

	private static final long serialVersionUID = -3557259634225802008L;

	@Resource
	private OrderchargeService orderchargeService;
	
	private OrderCharge orderCharge;
	
	//支付信息
	private String orderchargeString;
	public String getOrderchargeString() {
		return orderchargeString;
	}

	public void setOrderchargeString(String orderchargeString) {
		this.orderchargeString = orderchargeString;
	}

	/**
	 * 显示所有支付信息
	 * @return
	 * @throws Exception
	 */
	public String showList() throws Exception{
		request.setAttribute("leftPage", "common/policymanage_left.jsp");
		request.setAttribute("mainPage", "order/orderchargeManage.jsp");
		return SUCCESS;
	}

	/**
	 * 获取数据list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		if (offset == null) {
			offset = "0";
		}
		if (limit == null) {
			limit = "10";
		}
		int offSet = Integer.parseInt(offset);
		int liMit = Integer.parseInt(limit);
		try {
			long total = orderchargeService.getOrderChargeCount();
			if (offSet >= total && total != 0) {
				offSet = offSet - liMit;
			}
			List<OrderCharge> slist = orderchargeService.findOrderCharge(new PageBean(liMit, offSet), new Object[]{});
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("total", total);
			jsonObject.put("rows", rows);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 添加支付信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		JSONObject result = new JSONObject();
		// orderchargeString json对象 受益人信息
		System.out.println("添加受益人接收：" + orderchargeString);
		if (orderchargeString == null) {
			return "orderchargeString is null";
		} else {
			try {
				orderCharge = Return_Code.getGson().fromJson(orderchargeString, OrderCharge.class);
				// 插入数据
				String rs = orderchargeService.insert(orderCharge);
				result.put("insert", rs);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			} catch (Exception e) {
				e.printStackTrace();
				return "orderchargeString fromJson failure";
			}
		}
		return null;
	}

}
