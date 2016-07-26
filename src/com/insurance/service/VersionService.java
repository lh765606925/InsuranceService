package com.insurance.service;

import java.util.List;

import com.insurance.model.Version;
import com.insurance.model.PageBean;

/**
 * 财务管理service
 * 
 * @author fly
 * 
 */
public interface VersionService {
	public List<Version> findVersionList(PageBean pageBean, Object[] param);

	public List<Version> findVersionList();

	public Long getVersionCount();


	public Version getVersionById(int sid) throws Exception;

	public Version modifyPass(String[] moneyupdate);

	public List<Version> findVersionById(String sid);

	public String addVersion(String VersionBean);

	public int insert(Version Version);

	public Version findNewVersion();
}
