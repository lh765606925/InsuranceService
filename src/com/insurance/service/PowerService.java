package com.insurance.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.insurance.model.PageBean;
import com.insurance.model.Power;

public interface PowerService {
	public List<Power> finFunctionList(PageBean pageBean);

	public Long getPowerCount();

	public Power getFunctionById(int powerIds) throws Exception;

	public int addPower(Power power);

	public void deletePower(Power power);

	public void updatePower(Power power);
	
	public Power findPowerById(int id);

	/**
	 * 判断是否为叶子
	 * 
	 * @param powerId
	 * @return
	 */
	public boolean isLeaf(int powerId);

	public void updateStateByPowerId(String state, int powerId);

	public JSONArray getTreeGridPowerByParentId(int parentId);

	public JSONArray getListByParentId(int parentId);

}
