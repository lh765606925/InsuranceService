package com.insurance.service;

import java.util.List;

import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.Pager;
import com.insurance.model.Salesman;

/**
 * 财务管理service
 * @author fly
 *
 */
public interface DepositService extends BaseService<Deposit, String> {
	public List<Deposit> findDepositList(PageBean pageBean,String salesman_id);
	public List<Deposit> findDepositList();

	public Long getDepositCount();
	public Salesman findById(int i) throws Exception;

	public Deposit getDepositById(int sid) throws Exception;
	public Deposit  modifyPass(String[] moneyupdate);//充值金额方法

	public List<Deposit> findDepositById(String sid);
	public String addDeposit(String depositBean);
	public int insert(Deposit deposit);
	

	
	/**
	 * 根据Salesman、Pager获取充值记录分页对象
	 * 
	 * @param member
	 *            Salesman对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 充值记录分页对象
	 */
	public Pager getDepositPager(Salesman salesman, Pager pager);
	
	
	/**
	 * 根据Salesman、Pager获取充值记录分页对象
	 * 
	 * @param member
	 *            Salesman对象
	 *            
	 * @param pager
	 *            Pager对象
	 *            
	 * @return 充值记录分页对象
	 */
	public List<Deposit> findDepositList(PageBean pageBean);
}
