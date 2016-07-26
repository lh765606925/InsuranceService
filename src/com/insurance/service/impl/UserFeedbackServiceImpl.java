package com.insurance.service.impl;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.UserFeedback;
import com.insurance.service.UserFeedbackService;

public class UserFeedbackServiceImpl extends BaseServiceImpl<UserFeedback, String> implements UserFeedbackService{

	@Override
	public List<UserFeedback> find(PageBean pageBean, Object[] objects) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getTotalCount(int sid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserFeedback findById(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
