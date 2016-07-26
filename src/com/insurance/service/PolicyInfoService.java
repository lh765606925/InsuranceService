package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.PolicyInfo;

public interface PolicyInfoService extends BaseService<PolicyInfo, String> {
	public List<PolicyInfo> findPolicyInfo(PageBean pageBean, Object[] param);

	public List<PolicyInfo> findPolicyInfo();

	public Long getPolicyInfoCount();

	public PolicyInfo getPolicyInfoById(int policyInfoIds) throws Exception;

	public int addPolicyInfo(PolicyInfo policyInfo);

	public void deletePolicyInfo(PolicyInfo policyInfo);

	public void updatePolicyInfo(PolicyInfo policyInfo);

	public PolicyInfo findPolicyInfoById(String id);

	public List<PolicyInfo> findPolicyInfoList();

	public void add1(String testjson2);

	/**
	 * 插入保单信息
	 * 
	 * @param PolicyInfo
	 * @return
	 */
	public String insert(PolicyInfo policyInfo);

	/**
	 * 更新保单信息信息
	 * 
	 * @param PolicyInfo
	 */
	public void update(PolicyInfo policyInfo);

	/**
	 * 删除保单信息信息
	 * 
	 * @param ids
	 */
	public void deletes(String ids);

	/**
	 * 根据ids查询保单信息信息
	 * 
	 * @param ids
	 * @return
	 */
	public PolicyInfo findById(String ids);

	/**
	 * 删除保单信息
	 * 
	 * @param a
	 */
	public void delete(PolicyInfo a);

	public List<PolicyInfo> findPolicyInfo(PageBean pageBean);

	// 根据手机号码查询订单总数
	public Long getPolicyInfoCount(String phone);

	// 根据会员ID查询该会员的订单列表
	public List<PolicyInfo> findPolicyInfo(PageBean app_PageBean, String salesman_id);
}
