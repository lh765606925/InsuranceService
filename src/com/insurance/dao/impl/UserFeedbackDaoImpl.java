package com.insurance.dao.impl;

import java.util.List;

import com.insurance.dao.UserFeedbackDao;
import com.insurance.model.PageBean;
import com.insurance.model.UserFeedback;

public class UserFeedbackDaoImpl extends BaseDaoImpl<UserFeedback, String> implements UserFeedbackDao {
	@Override
	public List<UserFeedback> find(PageBean pageBean) {
		String hql = "from " + UserFeedback.class.getSimpleName() + " as b order by b.createDate Desc";
		List<UserFeedback> userFeedback = find(hql, pageBean, null);
		if (userFeedback != null && userFeedback.size() > 0) {
			return userFeedback;
		}
		return null;
	}

	@Override
	public String save(UserFeedback userFeedback) {
		try {
			super.saveorUpdate(userFeedback);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
