package com.insurance.dao;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.UserFeedback;

public interface UserFeedbackDao extends BaseDao<UserFeedback, String> {
	public List<UserFeedback> find(PageBean pageBean);

	public String save(UserFeedback article);
}
