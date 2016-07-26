package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.Salesman;

/**
 * 业务员Service
 * 
 * @author fly
 * 
 */
public interface SalesmanService {

	public String login(String salesman);

	public String register(String salesman) throws Exception;

	public String modifyPass(String[] modpass);

	/**
	 * 充值
	 * 
	 * @param moneypass
	 * @return
	 */
	public String moneyPass(String[] moneypass);

	public Salesman moneyById(String sids);

	public int insert(Salesman salesman);

	public void delete(Salesman salesman);

	public void deletes(String sids);

	public void update(Salesman salesman);

	public Salesman findById(int i) throws Exception;

	public Salesman findByPhone(String phone);

	public Salesman findByEmail(String email);

	public Salesman findByName(String name);

	public List<Salesman> find();

	public List<Salesman> find(PageBean pageBean, Object[] param);

	public Long getSalsmanCount();

	public boolean isExit(String str, String strval);

	String moneyPass(Long moneypass);

	String moneyUpdate(Long money);

	Long getmoneyBySalesmanID(int sid);

	public List<Salesman> findByInvate(String invate);

	public void saveorUpdate(Salesman salesman);

	// 统计一级会员人数
	public Long getMerberFirstCount(String phone);

	// 统计二级会员人数
	public Long getMerberSecondCount(String invate);

	// 统计会三级会员员人数
	public Long getMerberThreeCount(String invate);

	// 统计会四级会员员人数
	public Long getMerberFourCount(String invate);

	// 统计会五级会员员人数
	public Long getMerberFiveCount(String invate);

	// 我的一级会员
	public List<Salesman> findSalesmanList(PageBean pageBean, String phone);

	public List<Salesman> findSalesmanSecondList(PageBean pageBean, String phone);

	public List<Salesman> findSalesmanThreeList(PageBean pageBean, String phone);

	public List<Salesman> findSalesmanFourList(PageBean pageBean, String phone);

	public List<Salesman> findSalesmanFiveList(PageBean pageBean, String phone);

	public List<Salesman> getInvateSalesmans(int sid) throws Exception;

}
