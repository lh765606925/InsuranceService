package com.insurance.service;

import java.util.List;

import com.insurance.model.PageBean;
import com.insurance.model.UserFeedback;

/**
 * 用户反馈service接口
 * @author huzhihong.com
 * 
 * 创建日期：2015年10月29日
 */
public interface UserFeedbackService extends BaseService<UserFeedback, String> {
	public List<UserFeedback> find(PageBean pageBean, Object[] objects) throws Exception;

	public List<UserFeedback> find(PageBean pageBean);

	public long getTotalCount(int sid);

	// 根据ID查文章
	public UserFeedback findById(String ids);

	public void delete(String ids);

	public void delete(String[] ids);

	public String save(UserFeedback article);

	public Long getTotalCount();

}
