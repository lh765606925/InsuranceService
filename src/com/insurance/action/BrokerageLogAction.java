package com.insurance.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.insurance.model.AccountBalanceLog;
import com.insurance.model.BrokerageLog;
import com.insurance.model.OrderItem;
import com.insurance.model.Salesman;
import com.insurance.service.BrokerageLogService;
import com.insurance.util.PageUtil;
import com.insurance.util.ResponseUtil;
/**
 * 佣金发放日志
 * @author Administrator
 *
 */
@Controller
public class BrokerageLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private BrokerageLogService brokerageLogService;

	private String orderId;

	/**
	 * 查询所有日志列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		long total = brokerageLogService.getTotalCount();
		List<BrokerageLog> slist = brokerageLogService.find(PageUtil.getPageBean(offset, limit, total));
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);
		jsonObject.put("rows", rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * 查询当前订单所属佣金日志列表
	 * @param orderId
	 * @return List<BrokerageLog>
	 * @throws Exception
	 */
	public String listByOrderId() throws Exception {
		if (orderId == null || orderId == "")
			return "参数orderId不正确！";
		long total = brokerageLogService.getTotalCount(orderId);

		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		jsonObject.put("total", total);
		if (total != 0) {
			List<BrokerageLog> slist = brokerageLogService.find(PageUtil.getPageBean(offset, limit, total),
					new Object[] { orderId });

			JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
			jsonObject.put("rows", rows);
		} else {
			jsonObject.put("rows", "[]");
		}
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
