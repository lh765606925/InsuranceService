package com.insurance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.AccountBalanceLogDao;
import com.insurance.dao.BaseDao;
import com.insurance.dao.BrokerageLogDao;
import com.insurance.model.AccountBalanceLog;
import com.insurance.model.BrokerageLog;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.service.AccountBalanceLogService;
import com.insurance.service.SalesmanService;

@Service("AccountBalanceLogService")
public class AccountBalanceLogServiceImpl extends BaseServiceImpl<AccountBalanceLog, String> implements
		AccountBalanceLogService {
	@Resource
	private AccountBalanceLogDao accountBalanceLogDao;

	@Resource
	private BaseDao<AccountBalanceLog, String> baseDao;

	@Resource
	private SalesmanService salesmanService;

	@Override
	public List<AccountBalanceLog> find(PageBean pageBean, Object[] objects) throws Exception {

		List<AccountBalanceLog> logs = accountBalanceLogDao.find(pageBean, objects);
		if (logs != null) {
			for (int i = 0; i < logs.size(); i++) {
				AccountBalanceLog log = logs.get(i);
				Salesman salesman = salesmanService.findById(log.getSid());
				log.setSalesman(salesman);
				logs.set(i, log);
			}
		}
		return logs;
	}

	@Override
	public List<AccountBalanceLog> find(PageBean pageBean) {
		List<AccountBalanceLog> logs = accountBalanceLogDao.find(pageBean);
		if (logs != null) {
			for (int i = 0; i < logs.size(); i++) {
				AccountBalanceLog log = logs.get(i);
				try {
					Salesman salesman = salesmanService.findById(log.getSid());
					log.setSalesman(salesman);
					logs.set(i, log);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return logs;
	}

	@Override
	public long getTotalCount(int sid) {
		String hql = "select count(*) from " + AccountBalanceLog.class.getSimpleName() + " as o where o.sid = ?";
		return baseDao.count(hql, new Object[] { sid });
	}

	@Override
	public Long getTotalCount() {
		String hql = "select count(*) from " + AccountBalanceLog.class.getSimpleName();
		return baseDao.count(hql, null);
	}
}
