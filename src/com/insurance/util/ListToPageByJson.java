package com.insurance.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.insurance.action.DateJsonValueProcessor;

/**
 * @author huzhihong
 * @name  LIST =>JSON
 * @case1 public JSONObject putToJson(long total, List<T> slist) throws Exception
 * @case2 public JSONObject putToJson(List<T> slist) throws Exception
 * @createDate 2015年11月13日
 * @param <T>
 */
public class ListToPageByJson<T> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 把list封装成json返回给页面
	 * 
	 * @param total
	 * @param slist
	 * @return
	 * @throws Exception
	 */
	public JSONObject putToJson(long total, List<T> slist) throws Exception {
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		jsonObject.put("total", total);

		if (total == 0) {
			jsonObject.put("rows", "[]");
		} else {
			jsonObject.put("rows", rows);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}

	/**
	 * 把list封装成json返回给页面
	 * 
	 * @param total
	 * @param slist
	 * @return
	 * @throws Exception
	 */
	public JSONObject putToJson(List<T> slist) throws Exception {
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
		JSONArray rows = JSONArray.fromObject(slist, jsonConfig);
		if (slist == null) {
			jsonObject.put("rows", "[]");
		} else {
			jsonObject.put("rows", rows);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		return null;
	}
}
