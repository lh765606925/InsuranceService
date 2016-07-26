package com.insurance.dao;

import java.util.List;

import com.insurance.model.Deposit;

public interface DepositDao extends BaseDao<Deposit, String> {

	public List<Deposit> findDeposit_Salesman();


}
