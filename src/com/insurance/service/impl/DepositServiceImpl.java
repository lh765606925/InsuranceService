package com.insurance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.dao.DepositDao;
import com.insurance.dao.impl.BaseDaoImpl;
import com.insurance.model.PageBean;
import com.insurance.model.Pager;
import com.insurance.model.Salesman;
import com.insurance.model.Deposit;
import com.insurance.service.DepositService;
import com.insurance.service.SalesmanService;
import com.insurance.util.Return_Code;
import com.insurance.util.SundryTest;

@Service("depositService")
public class DepositServiceImpl extends BaseServiceImpl<Deposit, String> implements DepositService {
	@Resource
	private BaseDao<Deposit,String> baseDao;

//	@Resource
//	private DepositDao baseDao;
	
//	public DepositDao getBaseDao() {
//		return baseDao;
//	}
//
//	public void setBaseDao(DepositDao baseDao) {
//		this.baseDao = baseDao;
//	}

	@Resource
	private SalesmanService salesmanService;

	@Override
	public List<Deposit> findDepositList(PageBean pageBean, String salesman_id) {
		int did=0;
		if(salesman_id!=null&&!"".equals(salesman_id)){
			did = Integer.parseInt(salesman_id);
		}else{
			return null;
		}
//		int did = Integer.parseInt(salesman_id);
		if (pageBean != null) {
			return baseDao.find("from Deposit s where s.salesman_id=? order by createDate desc", pageBean, new Object[]{did});
		}
		return baseDao.find("from Deposit order by createDate desc");
	}

	@Override
	public List<Deposit> findDepositList() {
		List<Deposit> list= baseDao.find("from Deposit order by createDate desc");
		return list;
	}

	@Override
	public Long getDepositCount() {
		return baseDao.count("select count(*) from Deposit");
	}

	@Override
	public Salesman findById(int i) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deposit getDepositById(int sid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deposit modifyPass(String[] moneyupdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deposit> findDepositById(String sid) {
		int id = Integer.parseInt(sid);
		List<Deposit> slist = baseDao.find("from Deposit s where s.salesman_id=? order by createDate", new Object[]{id});
		return slist;
	}

	@Override
	public String addDeposit(String depositBean) {
		try {
			// 获取json信息
			Deposit deposit = Return_Code.getGson().fromJson(depositBean, Deposit.class);
			if (deposit == null) {
				return "param is error!";
			}
			Salesman salesman = salesmanService.findById(deposit.getSalesman_id());

			// 设置销售人员余额信息
			salesman.setMoney(salesman.getMoney() + deposit.getCredit());
			// 更新数据库salesman表余额信息
			salesmanService.update(salesman);
			deposit.setId(SundryTest.uuid());
			// insert(deposit);
			
			// 获取当前时间
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			deposit.setCreateDate(sdf1.format(d));
			deposit.setName(salesman.getRealName());
			baseDao.saveorUpdate(deposit);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "充值成功";
	}

	@Override
	public int insert(Deposit deposit) {
		return (Integer) baseDao.save(deposit);
	}

	@Override
	public Pager getDepositPager(Salesman salesman, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 后台分页查询充值记录
	 */
	@Override
	public List<Deposit> findDepositList(PageBean pageBean) {
		if (pageBean != null) {
			return baseDao.find("from Deposit order by createDate desc", pageBean,null);
		}
		return baseDao.find("from Deposit order by createDate desc");
	}

}
