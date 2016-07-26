package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.Deposit;
import com.insurance.model.Finance;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.service.FinanceService;

@Service("FinanceService")
public class FinanceServiceImpl extends BaseServiceImpl<Finance, String> implements FinanceService {
	@Resource
	private BaseDao<Finance, String> baseDao;

	@Override
	public List<Finance> findFinanceList(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from Finance", pageBean, param);
		}
		return baseDao.find("from Finance");
	}

	@Override
	public List<Finance> findFinanceList() {
		return baseDao.find("from Finance");
	}

	@Override
	public Long getFinanceCount() {
		return baseDao.count("select count(*) from Finance");
	}

	@Override
	public Finance getFinanceById(int sid) throws Exception {
		return null;
	}

	@Override
	public Finance findFinanceById(int sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salesman findById(int i) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Finance modifyPass(String[] moneyupdate) {
		// TODO Auto-generated method stub
		return null;
	}

}
