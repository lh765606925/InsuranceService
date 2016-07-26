package com.insurance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insurance.dao.BaseDao;
import com.insurance.model.Deposit;
import com.insurance.model.PageBean;
import com.insurance.model.Salesman;
import com.insurance.model.Version;
import com.insurance.service.VersionService;
import com.insurance.service.SalesmanService;
import com.insurance.util.Return_Code;
import com.insurance.util.SundryTest;

@Service("VersionService")
public class VersionServiceImpl extends BaseServiceImpl<Deposit, String> implements VersionService {
	@Resource
	private BaseDao<Version,String> baseDao;
	@Resource
	private SalesmanService salesmanService;

	@Override
	public List<Version> findVersionList(PageBean pageBean, Object[] param) {
		if (pageBean != null) {
			return baseDao.find("from Version", pageBean, param);
		}
		return baseDao.find("from Version");
	}

	@Override
	public List<Version> findVersionList() {
		return baseDao.find("from Version");
	}

	@Override
	public Long getVersionCount() {
		return baseDao.count("select count(*) from Version");
	}


	@Override
	public Version getVersionById(int sid) throws Exception {
		return null;
	}

	@Override
	public Version modifyPass(String[] moneyupdate) {
		return null;
	}

	@Override
	public List<Version> findVersionById(String sid) {
		int id = Integer.parseInt(sid);
		List<Version> slist = baseDao.find(
				"from Version s where s.salesman_id=? order by createDate", new Object[] { id });
		return slist;
	}

	@Override
	public String addVersion(String VersionBean) {
		try {
			// 获取json信息
			Version Version = Return_Code.getGson().fromJson(VersionBean,
					Version.class);
			if (Version == null) {
				return "param is error!";
			}

			// 设置销售人员余额信息
			// 更新数据库salesman表余额信息
			Version.setId(SundryTest.uuid());
			// insert(Version);
			//获取当前时间
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
	
			System.out.println("");
			baseDao.executeSql("", null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "充值成功";
	}

	@Override
	public int insert(Version version) {
		return (Integer) baseDao.save(version);
	}

	@Override
	public Version findNewVersion() {
		List<Version> slist = baseDao.find("from Version order by releasedate Desc");
		if (slist.size() > 0) {
			return slist.get(0);
		} else {
			return null;
		}
	}

}
