package com.cg.pp.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.dao.AccountDao;
import com.cg.pp.dao.AccountDaoImpl;
import com.cg.pp.exception.AccountException;

public class AccountServiceImpl implements AccountService {

	AccountDao accdao=new AccountDaoImpl();
	@Override
	public Integer createAccount(Account acc) throws SQLException {
	
		return accdao.createAccount(acc);
	}

	@Override
	public float showBalance(Integer accId) throws AccountException, SQLException {
		
		return accdao.showBalance(accId);
	}

	@Override
	public float deposit(Integer id, float amount) throws AccountException, SQLException {
		
		return accdao.deposit(id, amount);
	}

	@Override
	public float withdraw(Integer id, float amount) throws AccountException, SQLException {
	
		return accdao.withdraw(id, amount);
	}

	@Override
	public float fundTransfer(Integer senderAccId, Integer receiverAccId,
			float amount) throws AccountException, SQLException {
		return accdao.fundTransfer(senderAccId, receiverAccId, amount);
		
	}

	@Override
	public String printTransactions(Integer accId) throws AccountException, SQLException {
		
		return accdao.printTransactions(accId);
	}

	@Override
	public boolean validateDetails(Person p) throws AccountException {
	
		return accdao.validateDetails(p);
	}

	@Override
	public Integer getDetails(Integer i) throws AccountException, SQLException {
		
		return accdao.getDetails(i);
	}

}
