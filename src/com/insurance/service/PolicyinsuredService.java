package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Policyinsured;

public interface PolicyinsuredService extends BaseService<Policyinsured, String> {
	public List<Policyinsured> findPolicyinsured(PageBean pageBean, Object[] param);
	public List<Policyinsured> findPolicyinsured();

	public Long getPolicyinsuredCount();

	public Policyinsured getPolicyinsuredById(int PolicyinsuredIds) throws Exception;

	public int addPolicyinsured(Policyinsured policyinsured);

	public void deletePolicyinsured(Policyinsured policyinsured);

	public void updatePolicyinsured(Policyinsured policyinsured);

	public Policyinsured findPolicyinsuredById(String id);

	public List<Policyinsured> findPolicyinsuredList();
	public void add1(String testjson2);

	/**
	 * 插入受益人信息
	 * 
	 * @param Policyinsured
	 * @return
	 */
	public String insert(Policyinsured policyinsured);

	/**
	 * 更新受益人信息信息
	 * 
	 * @param Policyinsured
	 */
	public void update(Policyinsured policyinsured);

	/**
	 * 删除受益人信息信息
	 * 
	 * @param ids
	 */
	public void deletes(String ids);

	/**
	 * 根据ids查询受益人信息信息
	 * 
	 * @param ids
	 * @return
	 */
	public Policyinsured findById(String ids);

	/**
	 * 删除受益人信息
	 * 
	 * @param a
	 */
	public void delete(Policyinsured a);
}
