package com.insurance.service;

import java.util.List;

import com.insurance.model.AccountBalanceLog;
import com.insurance.model.PageBean;

/**
 * Service接口 - 账户明细日志
 * 
 * @author Administrator
 * 
 */
public interface AccountBalanceLogService extends BaseService<AccountBalanceLog, String> {

	public List<AccountBalanceLog> find(PageBean pageBean, Object[] objects) throws Exception;

	public List<AccountBalanceLog> find(PageBean pageBean);

	public long getTotalCount(int sid);

	public Long getTotalCount();
}
