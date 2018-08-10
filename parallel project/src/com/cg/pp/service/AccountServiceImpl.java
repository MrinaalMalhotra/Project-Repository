package com.cg.pp.service;

import java.util.List;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.dao.AccountDao;
import com.cg.pp.dao.AccountDaoImpl;
import com.cg.pp.exception.AccountException;

public class AccountServiceImpl implements AccountService {

	AccountDao accdao=new AccountDaoImpl();
	@Override
	public Integer createAccount(Account acc) {
	
		return accdao.createAccount(acc);
	}

	@Override
	public float showBalance(Integer accId) throws AccountException {
		
		return accdao.showBalance(accId);
	}

	@Override
	public float deposit(Integer id, float amount) throws AccountException {
		
		return accdao.deposit(id, amount);
	}

	@Override
	public float withdraw(Integer id, float amount) throws AccountException {
	
		return accdao.withdraw(id, amount);
	}

	@Override
	public float fundTransfer(Integer senderAccId, Integer receiverAccId,
			float amount) throws AccountException {
		return accdao.fundTransfer(senderAccId, receiverAccId, amount);
		
	}

	@Override
	public String printTransactions(Integer accId) throws AccountException {
		
		return accdao.printTransactions(accId);
	}

	@Override
	public boolean validateDetails(Person p) throws AccountException {
	
		return accdao.validateDetails(p);
	}

	@Override
	public boolean getDetails(Integer i) throws AccountException {
		
		return accdao.getDetails(i);
	}

}
