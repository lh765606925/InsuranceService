package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.Deposit;
import com.insurance.model.Function;
import com.insurance.model.PageBean;
import com.insurance.model.Power;
import com.insurance.service.PowerService;

@Service("powerService")
public class PowerServiceImpl extends BaseServiceImpl<Deposit, String> implements PowerService {
	@Resource
	private BaseDao<Power,String> baseDao;

	@Override
	public List<Power> finFunctionList(PageBean pageBean) {
		StringBuffer hql = new StringBuffer("FROM Function");
		if (pageBean != null) {
			return baseDao.find(hql.toString(), pageBean,new Object[]{});
		} else {
			return null;
		}
	}

	@Override
	public Long getPowerCount() {
		StringBuffer hql = new StringBuffer("select count(*) FROM Function");
	    Long powerCount= baseDao.count(hql.toString());
		return powerCount;
	}

	@Override
	public void deletePower(Power power) {
		baseDao.delete(power);
		if(isLeaf(power.getParentId())){
			updateStateByPowerId("open", power.getParentId());
		}
		
	}

	@Override
	public Power getFunctionById(int id) throws Exception {
		Power power = null;
			power = baseDao.get(Power.class, id);
		return power;
	}

	@Override
	public void updatePower(Power power) {
		baseDao.update(power);
	}

	@Override
	public JSONArray getTreeGridPowerByParentId(int parentId) {
		List<Power> plist=baseDao.find("from Power p where p.parentId=?", new Object[]{parentId});
		return JSONArray.fromObject(plist);
	}

	@Override
	public JSONArray getListByParentId(int parentId) {
		JSONArray jsonArray=this.getTreeGridPowerByParentId(parentId);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getListByParentId(jsonObject.getInt("pid")));
			}
		}
		return jsonArray;
	}

	@Override
	public boolean isLeaf(int powerId) {
		List<Power> list=baseDao.find("from Power p where p.parentId=?", new Object[]{powerId});
		return list.size()<1;
	}

	@Override
	public int addPower(Power power) {
		if(isLeaf(power.getParentId())){
			updateStateByPowerId("closed",power.getParentId());
		}
			power.setState("open");
			return (Integer) baseDao.save(power);
		
	}

	@Override
	public void updateStateByPowerId(String state,int powerId) {
		baseDao.executeSql("update t_power set state=? where pid=?",new Object[]{state,powerId});
	}

	@Override
	public Power findPowerById(int id) {
		try {
		   Power p=	baseDao.get(Power.class, id);
		   return p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("findPowerById:Error");
		}
		return null;
	}


}
