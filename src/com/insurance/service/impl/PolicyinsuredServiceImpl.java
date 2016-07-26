package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.PolicyHolder;
import com.insurance.model.Policyinsured;
import com.insurance.model.PageBean;
import com.insurance.service.PolicyinsuredService;
import com.insurance.util.SundryTest;

@Service("PolicyinsuredService")
public class PolicyinsuredServiceImpl extends BaseServiceImpl<Policyinsured, String> implements PolicyinsuredService{

	@Resource
	private BaseDao<Policyinsured, String> baseDao;

	@Override
	public List<Policyinsured> findPolicyinsured(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from Policyinsured p order by p.createDate Desc", pageBean, param);
		}
		return null;
	}

	@Override
	public List<Policyinsured> findPolicyinsured() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getPolicyinsuredCount() {
		return baseDao.count("select count(*) from Policyinsured");
	}

	@Override
	public Policyinsured getPolicyinsuredById(int policyinsuredIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPolicyinsured(Policyinsured policyinsured) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePolicyinsured(Policyinsured policyinsured) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePolicyinsured(Policyinsured policyinsured) {
		// TODO Auto-generated method stub

	}

	@Override
	public Policyinsured findPolicyinsuredById(String id) {
		String hql = "from Policyinsured p where p.id = ?";
		List<Policyinsured> list = baseDao.find(hql, new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Policyinsured> findPolicyinsuredList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add1(String testjson2) {
		// TODO Auto-generated method stub

	}

	/**
	 * 添加受益人
	 */
	@Override
	public String insert(Policyinsured policyinsured) {
		policyinsured.setId(SundryTest.uuid());
		policyinsured.setCertificateType("201");
		policyinsured.setZipNo("100110");
		policyinsured.setCounty("CHN");
		policyinsured.setId(SundryTest.uuid());
		baseDao.save(policyinsured);
		//添加数据验证
		return "添加受益人成功";
	}

	@Override
	public void update(Policyinsured policyinsured) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletes(String ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public Policyinsured findById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Policyinsured a) {
		// TODO Auto-generated method stub

	}
}
