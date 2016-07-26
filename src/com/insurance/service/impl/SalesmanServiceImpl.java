package com.insurance.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.hibernate.mapping.PrimaryKey;
import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.dao.SalesmanDao;
import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.service.SalesmanService;
import com.insurance.util.MD5;
import com.insurance.util.PropertiesUtil;
import com.insurance.util.QRCodeUtil;
import com.insurance.util.Return_Code;
import com.insurance.util.StringUtil;

@Service("salesmanService")
public class SalesmanServiceImpl extends BaseServiceImpl<Deposit, String> implements SalesmanService {
	@Resource
	private BaseDao<Salesman, String> baseDao;

	@Resource
	private SalesmanDao salesmanDao;

	private String table = "t_salesman";

	@Override
	public int insert(Salesman salesman) {
		return (Integer) baseDao.save(salesman);
	}

	@Override
	public void delete(Salesman salesman) {
		baseDao.delete(salesman);

	}

	@Override
	public void update(Salesman salesman) {
		baseDao.update(salesman);
	}

	@Override
	public Salesman findById(int i) throws Exception {
		List<Salesman> slist = baseDao.find("from Salesman s where s.sid=?", new Object[] { i });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Salesman> find() {
		return baseDao.find("from Salesman");
	}

	@Override
	public List<Salesman> find(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from Salesman order by regtime desc", pageBean, param);
		}
		return baseDao.find("from Salesman order by regtime desc", param);
	}

	@Override
	public Long getSalsmanCount() {
		return baseDao.count("select count(*) from Salesman");
	}

	@Override
	public Long getMerberFirstCount(String phone) {
		return baseDao.count("select count(*) from Salesman s where s.invate=?", new Object[] { phone });
	}

	@Override
	public Long getMerberSecondCount(String invate) {
		return baseDao.count("select count(*) from Salesman s where s.invate2=?", new Object[] { invate });
	}

	@Override
	public Long getMerberThreeCount(String invate) {
		return baseDao.count("select count(*) from Salesman s where s.invate3=?", new Object[] { invate });
	}

	@Override
	public Long getMerberFourCount(String invate) {
		return baseDao.count("select count(*) from Salesman s where s.invate4=?", new Object[] { invate });
	}

	@Override
	public Long getMerberFiveCount(String invate) {
		return baseDao.count("select count(*) from Salesman s where s.invate5=?", new Object[] { invate });
	}

	@Override
	public Salesman findByPhone(String phone) {
		List<Salesman> slist = baseDao.find("from Salesman s where s.phone=?", new Object[] { phone });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	public Salesman moneyById(String sids) {
		List<Salesman> slist = baseDao.find("from Salesman s where s.sid=?", new Object[] { sids });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deletes(String sids) {
		System.out.println("delete from Salesman s where s.sid in (" + sids + ")");
		baseDao.executeHql("delete from Salesman s where s.sid in (" + sids + ")");
	}

	@Override
	public String login(String salesman) {
		Salesman sale = null;
		try {
			sale = Return_Code.getGson().fromJson(salesman, Salesman.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "param fromJson failure";
		}
		if (findByPhone(sale.getPhone()) == null) {
			return "用户名错";// 用户名错误
		}
		String hql = "From Salesman s where s.phone=? and s.passWord=?";
		if (sale.getPassWord().length() < 32) {
			sale.setPassWord(MD5.getMD5(sale.getPassWord().getBytes()));
		}
		List<Salesman> slist = baseDao.find(hql, new Object[] { sale.getPhone(), sale.getPassWord() });
		if (slist.size() < 1) {
			return "密码错误";
		} else {// 返回个人资料
			return Return_Code.getGson().toJson(this.findByPhone(sale.getPhone()));
		}
	}

	@Override
	public String register(String salesman) throws Exception {
		Salesman sale = null;
		try {
			sale = Return_Code.getGson().fromJson(salesman, Salesman.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "param fromJson failure";
		}
		if (isExit("phone", sale.getPhone())) {
			return "该手机号已被注册";
		}
		if (isExit("email", sale.getEmail())) {
			return "该邮箱已被绑定";
		}
		if (isExit("name", sale.getName())) {
			return "该昵称已存在";
		}
		sale.setMoney(0.00);
		sale.setPassWord(MD5.getMD5(sale.getPassWord().getBytes()));
		insert(sale);
		// 完善注册信息
		this.perfectRegistration(sale);

		String dir = "/salesman/" + sale.getSid();
		String path = StringUtil.getWebApp() + "/InsuranceData" + dir;

		PropertiesUtil propertiesUtil = new PropertiesUtil("resouse/config.properties");
		String serverip = propertiesUtil.readValue("serverIP");
		String loginpath = propertiesUtil.readValue("logoPath");
		String filename = QRCodeUtil
				.encode("http://" + serverip + ":8080/InsuranceService/salesman/sale_register.jsp?invate="
						+ sale.getPhone(), loginpath, path, false);
		sale.setQrcode(dir + "/" + filename);

		baseDao.saveorUpdate(sale);

		return "注册成功";
	}

	void Test(Salesman sale) {

		// 判断传入会员信息内邀请人字段是否为空
		if (StringUtil.isNotEmpty(sale.getInvate())) {

			// 根据邀请人手机号码查询"邀请人"信息
			Salesman sinvate = findByPhone(sale.getInvate());
			// 判断邀请人是否存在
			if (sinvate != null) {
				// 邀请人1的邀请人数加1
				sinvate.setInvateNum(sinvate.getInvateNum() + 1);
				// 更新邀请人的信息
				update(sinvate);
				// 设置注册会员的邀请人
				// sale.setInvate(sale.getInvate());
				// 根据邀请人的邀请人查询邀请人2的信息
				// 判断"邀请人1"的邀请人字段是否为空
				if (StringUtil.isNotEmpty(sinvate.getInvate())) {
					// 根据邀请人1的邀请人字段获取邀请人2的信息
					Salesman sinvate2 = findByPhone(sinvate.getInvate());
					// 判断邀请人2是否存在
					if (sinvate2 != null) {
						// 邀请人2的邀请人数加1
						sinvate2.setInvateNum(sinvate2.getInvateNum() + 1);
						// 更新邀请人2的信息
						update(sinvate2);
						// 设置注册会员的邀请人2
						sale.setInvate2(sinvate2.getPhone());

						// 根据"邀请人2"的邀请人查询"邀请人3"的信息
						// 判断"邀请人2"的邀请人字段是否为空
						if (StringUtil.isNotEmpty(sinvate2.getInvate())) {
							// 根据"邀请人2"的邀请人字段获取"邀请人3"的信息
							Salesman sinvate3 = findByPhone(sinvate2.getInvate());
							if (sinvate3 != null) {
								// "邀请人3"的邀请人数加1
								sinvate3.setInvateNum(sinvate3.getInvateNum() + 1);
								// 更新邀请人3的信息
								update(sinvate3);
								// 设置注册会员的邀请人3
								sale.setInvate3(sinvate3.getPhone());

								if (StringUtil.isNotEmpty(sinvate3.getInvate())) {
									Salesman sinvate4 = findByPhone(sinvate3.getInvate());
									if (sinvate4 != null) {
										sinvate4.setInvateNum(sinvate4.getInvateNum() + 1);
										update(sinvate4);
										sale.setInvate4(sinvate4.getPhone());

										if (StringUtil.isEmpty(sinvate4.getInvate())) {
											Salesman sinvate5 = findByPhone(sinvate4.getInvate());
											if (sinvate5 != null) {
												sinvate5.setInvateNum(sinvate5.getInvateNum() + 1);
												update(sinvate5);
												sale.setInvate5(sinvate5.getPhone());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 完善注册信息
	 * 
	 * @param salesman
	 */
	private void perfectRegistration(Salesman sale) {
		// 判断传入会员信息内邀请人字段是否为空
		if (StringUtil.isNotEmpty(sale.getInvate())) {

			// 根据邀请人手机号码查询"邀请人"信息
			Salesman sinvate = findByPhone(sale.getInvate());
			// 判断邀请人是否存在
			if (sinvate != null) {
				// 邀请人1的邀请人数加1
				sinvate.setInvateNum(sinvate.getInvateNum() + 1);
				// 更新邀请人的信息
				update(sinvate);
				// 设置注册会员的邀请人
				// sale.setInvate(sale.getInvate());
				// 根据邀请人的邀请人查询邀请人2的信息
				// 判断"邀请人1"的邀请人字段是否为空
				if (StringUtil.isNotEmpty(sinvate.getInvate())) {
					// 根据邀请人1的邀请人字段获取邀请人2的信息
					Salesman sinvate2 = findByPhone(sinvate.getInvate());
					// 判断邀请人2是否存在
					if (sinvate2 != null) {
						// 邀请人2的邀请人数加1
						sinvate2.setInvateNum(sinvate2.getInvateNum() + 1);
						// 更新邀请人2的信息
						update(sinvate2);
						// 设置注册会员的邀请人2
						sale.setInvate2(sinvate2.getPhone());

						// 根据"邀请人2"的邀请人查询"邀请人3"的信息
						// 判断"邀请人2"的邀请人字段是否为空
						if (StringUtil.isNotEmpty(sinvate2.getInvate())) {
							// 根据"邀请人2"的邀请人字段获取"邀请人3"的信息
							Salesman sinvate3 = findByPhone(sinvate2.getInvate());
							if (sinvate3 != null) {
								// "邀请人3"的邀请人数加1
								sinvate3.setInvateNum(sinvate3.getInvateNum() + 1);
								// 更新邀请人3的信息
								update(sinvate3);
								// 设置注册会员的邀请人3
								sale.setInvate3(sinvate3.getPhone());

								// 根据"邀请人3"的邀请人查询"邀请人4"的信息
								// 判断"邀请人3"的邀请人字段是否为空
								if (StringUtil.isNotEmpty(sinvate3.getInvate())) {
									// 根据"邀请人3"的邀请人字段获取"邀请人4"的信息
									Salesman sinvate4 = findByPhone(sinvate3.getInvate());
									if (sinvate4 != null) {
										// "邀请人4"的邀请人数加1
										sinvate4.setInvateNum(sinvate4.getInvateNum() + 1);
										// 更新邀请人4的信息
										update(sinvate4);
										// 设置注册会员的邀请人4
										sale.setInvate4(sinvate4.getPhone());

										// 根据"邀请人4"的邀请人查询"邀请人5"的信息
										// 判断"邀请人4"的邀请人字段是否为空
										if (StringUtil.isNotEmpty(sinvate4.getInvate())) {
											// 根据"邀请人4"的邀请人字段获取"邀请人5"的信息
											Salesman sinvate5 = findByPhone(sinvate4.getInvate());
											if (sinvate5 != null) {
												// "邀请人4"的邀请人数加1
												sinvate5.setInvateNum(sinvate5.getInvateNum() + 1);
												// 更新邀请人4的信息
												update(sinvate5);
												// 设置注册会员的邀请人5
												sale.setInvate5(sinvate5.getPhone());

												// 根据"邀请人5"的邀请人查询"邀请人6"的信息
												// 判断"邀请人5"的邀请人字段是否为空
												if (StringUtil.isNotEmpty(sinvate5.getInvate())) {
													// 根据"邀请人5"的邀请人字段获取"邀请人6"的信息
													Salesman sinvate6 = findByPhone(sinvate5.getInvate());
													if (sinvate6 != null) {
														// "邀请人6"的邀请人数加1
														sinvate6.setInvateNum(sinvate6.getInvateNum() + 1);
														// 更新邀请人6的信息
														update(sinvate6);
														// 设置注册会员的邀请人6
														sale.setInvate6(sinvate6.getPhone());
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		// 更新用户信息
		update(sale);
	}

	@Override
	public Salesman findByEmail(String email) {
		List<Salesman> slist = baseDao.find("from Salesman s where s.email=?", new Object[] { email });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Salesman findByName(String name) {
		List<Salesman> slist = baseDao.find("from Salesman s where s.name=?", new Object[] { name });
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean isExit(String str, String strval) {
		return baseDao.isExit(table, str, strval);
	}

	/**
	 * 修改密码
	 */
	@Override
	public String modifyPass(String[] modpass) {
		Salesman sale = this.findByPhone(modpass[0]);
		if (sale != null) {
			sale.setPassWord(modpass[1]);
			baseDao.update(sale);
			return "密码修改成功";
		} else {
			return "密码修改失败";
		}
	}

	/**
	 * 修改余额
	 */
	@Override
	public String moneyPass(String[] moneypass) {
		Salesman sale = this.moneyById(moneypass[0]);
		if (sale != null) {
			/**
			 * 待修改，余额不能为string
			 */
			// sale.setMoney(moneypass[1]);
			baseDao.save(sale);
			return "充值成功";
		} else {
			return "充值失败";
		}
	}

	@Override
	public String moneyPass(Long moneypass) {
		return null;
	}

	@Override
	public Long getmoneyBySalesmanID(int sid) {
		return null;
	}

	@Override
	public String moneyUpdate(Long money) {
		return null;
	}

	@Override
	public List<Salesman> findByInvate(String invate) {
		return salesmanDao.findByInvate(invate);
	}

	@Override
	public void saveorUpdate(Salesman salesman) {
		baseDao.saveorUpdate(salesman);
	}

	/*
	 * 统计一级会员数量
	 */
	@Override
	public List<Salesman> findSalesmanList(PageBean pageBean, String invate) {
		if (pageBean != null) {
			return baseDao
					.find("from Salesman s where s.invate=? order by sid desc", pageBean, new Object[] { invate });
		}
		return baseDao.find("from Salesman s where s.invate=? order by sid desc", new Object[] { invate });
	}

	@Override
	public List<Salesman> findSalesmanSecondList(PageBean pageBean, String invate) {
		if (pageBean != null) {
			return baseDao.find("from Salesman s where s.invate2=? order by sid desc", pageBean,
					new Object[] { invate });
		}
		return baseDao.find("from Salesman s where s.invate2=? order by sid desc", new Object[] { invate });
	}

	@Override
	public List<Salesman> findSalesmanThreeList(PageBean pageBean, String invate) {

		if (pageBean != null) {
			return baseDao.find("from Salesman s where s.invate3=? order by sid desc", pageBean,
					new Object[] { invate });
		}
		return baseDao.find("from Salesman s where s.invate3=? order by sid desc", new Object[] { invate });
	}

	@Override
	public List<Salesman> findSalesmanFourList(PageBean pageBean, String invate) {

		if (pageBean != null) {
			return baseDao.find("from Salesman s where s.invate4=? order by sid desc", pageBean,
					new Object[] { invate });
		}
		return baseDao.find("from Salesman s where s.invate4=? order by sid desc", new Object[] { invate });
	}

	@Override
	public List<Salesman> findSalesmanFiveList(PageBean pageBean, String invate) {

		if (pageBean != null) {
			return baseDao.find("from Salesman s where s.invate5=? order by sid desc", pageBean,
					new Object[] { invate });
		}
		return baseDao.find("from Salesman s where s.invate5=? order by sid desc", new Object[] { invate });
	}

	/**
	 * 获取指定会员的相关邀请人
	 * 
	 * @param 指定会员的sid
	 * @return 返回:该会员，该会员的邀请人，及邀请人的邀请人
	 */
	@Override
	public List<Salesman> getInvateSalesmans(int sid) throws Exception {
		List<Salesman> slist = new ArrayList<Salesman>();
		Salesman salesman = findById(sid);
		slist.add(salesman);
		salesman = getInvate(salesman);
		if (salesman != null) {
			slist.add(salesman);
		}
		salesman = getInvate(salesman);
		if (salesman != null) {
			slist.add(salesman);
		}
		return slist;
	}

	/**
	 * 根据会员信息获取邀请人信息
	 * 
	 * @param salesman
	 * @return InvateSalesman
	 * @throws Exception
	 */
	public Salesman getInvate(Salesman salesman) throws Exception {
		if (salesman != null) {
			Salesman sn = findByPhone(salesman.getInvate());
			return sn;
		} else {
			return null;
		}
	}
}
