package com.insurance.service;

import java.util.List;

import com.insurance.model.PolicyHolder;
import com.insurance.model.PageBean;

/**
 * 投保人service
 * 
 * @author laohu
 * 
 */
public interface PolicyholderService extends BaseService<PolicyHolder, String> {
	public List<PolicyHolder> findPolicyHolder(PageBean pageBean, Object[] param);
	public List<PolicyHolder> findPolicyHolder();

	public Long getPolicyHolderCount();

	public PolicyHolder getPolicyHolderById(int policyHolderIds) throws Exception;

	public int addPolicyHolder(PolicyHolder policyHolder);

	public void deletePolicyHolder(PolicyHolder policyHolder);

	public void updatePolicyHolder(PolicyHolder policyHolder);

	public PolicyHolder findPolicyHolderById(String id);

	public List<PolicyHolder> findPolicyHolderList();
	public void add1(String testjson2);

	/**
	 * 插入投保人信息
	 * 
	 * @param PolicyHolder
	 * @return
	 */
	public String insert(PolicyHolder PolicyHolder);

	/**
	 * 更新投保人信息信息
	 * 
	 * @param PolicyHolder
	 */
	public void update(PolicyHolder policyHolder);

	/**
	 * 删除投保人信息信息
	 * 
	 * @param ids
	 */
	public void deletes(String ids);

	/**
	 * 根据ids查询投保人信息信息
	 * 
	 * @param ids
	 * @return
	 */
	public PolicyHolder findById(String ids);

	/**
	 * 删除投保人信息
	 * 
	 * @param a
	 */
	public void delete(PolicyHolder a);
	
	/**
	 * 通过手机号码查询投保人
	 * @param mobile
	 * @return
	 */
	public PolicyHolder findByMobile(String mobile);
}
