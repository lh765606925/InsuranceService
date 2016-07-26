package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.PageBean;
import com.insurance.model.PolicyHolder;
import com.insurance.model.PolicyInfo;
import com.insurance.service.PolicyholderService;
import com.insurance.util.SundryTest;

@Service("PolicyholderService")
public class PolicyholderServiceImpl  extends BaseServiceImpl<PolicyHolder, String> implements PolicyholderService {
	@Resource
	private BaseDao<PolicyHolder, String> baseDao;
	

	public List<PolicyHolder> findPolicyHolder(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from PolicyHolder p order by p.createDate Desc", pageBean, param);
		}
		return null;
	}

	@Override
	public List<PolicyHolder> findPolicyHolder() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Long getPolicyHolderCount() {
		String hql="select count(*) from PolicyHolder";
		return baseDao.count(hql);
	}
	
	

	@Override
	public PolicyHolder getPolicyHolderById(int policyHolderIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPolicyHolder(PolicyHolder policyHolder) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePolicyHolder(PolicyHolder policyHolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePolicyHolder(PolicyHolder policyHolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PolicyHolder findPolicyHolderById(String id) {
		String hql = "from PolicyHolder p where p.id = ?";
		List<PolicyHolder> list = baseDao.find(hql, new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<PolicyHolder> findPolicyHolderList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add1(String testjson2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String insert(PolicyHolder policyholder) {		

		// 数据验证
		if (policyholder.getBirthdate() != null && policyholder.getBirthdate().length() > 10) {
			policyholder.setBirthdate(policyholder.getBirthdate().substring(0, 10));
		}
		if (policyholder.getCertEndDate() != null && policyholder.getCertEndDate().length() > 10) {
			policyholder.setCertEndDate(policyholder.getCertEndDate().substring(0, 10));
		}
		if (policyholder.getCertStartDate() != null && policyholder.getCertStartDate().length() > 10) {
			policyholder.setCertStartDate(policyholder.getCertStartDate().substring(0, 10));
		}
		policyholder.setCertificateType("201");
		policyholder.setZipNo("100110");
		policyholder.setCounty("CHN");
		baseDao.save(policyholder);
//		baseDao.saveorUpdate(policyHolder);
		//添加数据验证
		return "添加投保人成功";
	}

	@Override
	public void deletes(String ids) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 根据ID查询产品信息
	 */
	@Override
	public PolicyHolder findById(String parseInt) {
		// TODO Auto-generated method stub
		List<PolicyHolder> slist = baseDao.find("from PolicyHolder s where s.id=?", new Object[]{parseInt});
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	@Override
	public PolicyHolder findByMobile(String mobile) {
		List<PolicyHolder> slist = baseDao.find("from PolicyHolder s where s.moblie=?", new Object[]{mobile});
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

}
