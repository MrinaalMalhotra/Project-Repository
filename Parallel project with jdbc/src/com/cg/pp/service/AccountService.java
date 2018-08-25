package com.cg.pp.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;

public interface AccountService {
	public Integer createAccount(Account acc) throws SQLException;
	public float showBalance(Integer accId) throws AccountException, SQLException;
	public float deposit(Integer id, float amount) throws AccountException, SQLException;
	public float withdraw(Integer id, float amount) throws AccountException, SQLException;
	public float fundTransfer(Integer senderAccId,Integer receiverAccId, float amount) throws AccountException, SQLException;
	public String printTransactions(Integer accId) throws AccountException, SQLException;
	public boolean validateDetails(Person p) throws AccountException;
	public Integer getDetails(Integer i) throws AccountException, SQLException;
}
