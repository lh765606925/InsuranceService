package com.insurance.dao;

import java.util.List;

import com.insurance.model.AccountBalanceLog;
import com.insurance.model.PageBean;

public interface AccountBalanceLogDao extends BaseDao<AccountBalanceLog, String> {

	public List<AccountBalanceLog> find(PageBean pageBean, Object[] objects);

	public List<AccountBalanceLog> find(PageBean pageBean);

	public long getTotalCount(int sid);
}
