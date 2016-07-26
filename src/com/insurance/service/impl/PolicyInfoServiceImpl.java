package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.PageBean;
import com.insurance.model.PolicyInfo;
import com.insurance.model.Product;
import com.insurance.service.PolicyInfoService;
import com.insurance.service.ProductService;

@Service("PolicyInfoService")
public class PolicyInfoServiceImpl extends BaseServiceImpl<PolicyInfo, String> implements PolicyInfoService {
	@Resource
	private BaseDao<PolicyInfo, String> baseDao;

	@Resource
	private ProductService productService;

	/**
	 * @name 根据 用户
	 */
	@Override
	public List<PolicyInfo> findPolicyInfo(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from PolicyInfo p where p.salesman_id = ? order by createDate desc", pageBean, param);
		} else {
			return baseDao.find("from PolicyInfo p where p.salesman_id = ? order by createDate desc", param);
		}
	}

	@Override
	public List<PolicyInfo> findPolicyInfo(PageBean pageBean) {
		if (pageBean != null) {
			return baseDao.find("from PolicyInfo order by createDate desc", pageBean, null);
		} else {
			return baseDao.find("from PolicyInfo order by createDate desc");
		}
	}

	@Override
	public List<PolicyInfo> findPolicyInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getPolicyInfoCount() {
		String hql = "select count(*) from PolicyInfo";
		return baseDao.count(hql);
	}

	@Override
	public Long getPolicyInfoCount(String salesman_id) {
		String hql = "select count(*) from PolicyInfo where salesman_id=" + salesman_id;
		return baseDao.count(hql);
	}

	@Override
	public PolicyInfo getPolicyInfoById(int policyInfoIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPolicyInfo(PolicyInfo policyInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePolicyInfo(PolicyInfo policyInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePolicyInfo(PolicyInfo policyInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public PolicyInfo findPolicyInfoById(String id) {
		String hql = "from PolicyInfo p where p.policyId = ?";
		List<PolicyInfo> list = baseDao.find(hql, new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	@Override
	public List<PolicyInfo> findPolicyInfoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add1(String testjson2) {
		// TODO Auto-generated method stub

	}

	@Override
	public String insert(PolicyInfo policyInfo) {
		baseDao.saveorUpdate(policyInfo);
		return "添加保单成功";
	}

	@Override
	public void deletes(String ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public PolicyInfo findById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PolicyInfo> findPolicyInfo(PageBean app_PageBean, String salesman_id) {
		String hql = "from PolicyInfo p where p.salesman_id = ? order by createDate desc";
		if (app_PageBean != null) {
			List<PolicyInfo> list = baseDao.find(hql, app_PageBean, new Object[] { salesman_id });
			return getPolicyInfoList(list);

		} else {
			List<PolicyInfo> list = baseDao.find(hql, new Object[] { salesman_id });
			return getPolicyInfoList(list);
		}
	}

	// 获取保单名字
	private List<PolicyInfo> getPolicyInfoList(List<PolicyInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			PolicyInfo policyInfo = list.get(i);
			String product_id = policyInfo.getProductId();
			Product product = productService.findById(product_id);
			if (product != null) {
				policyInfo.setProduct_name(product.getName());
				list.set(i, policyInfo);
			}
		}
		return list;
	}

}
