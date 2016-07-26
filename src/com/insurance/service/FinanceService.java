package com.insurance.service;

import java.util.List;

import com.insurance.model.Finance;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;

/**
 * 财务管理service
 * @author fly
 *
 */
public interface FinanceService {
	public List<Finance> findFinanceList(PageBean pageBean,Object[] param);
	public List<Finance> findFinanceList();

	public Long getFinanceCount();
	public Salesman findById(int i) throws Exception;

	public Finance getFinanceById(int sid) throws Exception;
	public Finance  modifyPass(String[] moneyupdate);//充值金额方法

	public Finance findFinanceById(int sid);
}
