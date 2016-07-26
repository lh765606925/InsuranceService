package com.insurance.dao;

import java.util.List;

import com.insurance.model.Salesman;


public interface SalesmanDao extends BaseDao<Salesman, String> {

	List<Salesman> findByInvate(String invate);
}
