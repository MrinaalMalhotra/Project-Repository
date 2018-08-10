package com.cg.pp.service;

import java.util.List;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;

public interface AccountService {
	public Integer createAccount(Account acc);
	public float showBalance(Integer accId) throws AccountException;
	public float deposit(Integer id, float amount) throws AccountException;
	public float withdraw(Integer id, float amount) throws AccountException;
	public float fundTransfer(Integer senderAccId,Integer receiverAccId, float amount) throws AccountException;
	public String printTransactions(Integer accId) throws AccountException;
	public boolean validateDetails(Person p) throws AccountException;
	public boolean getDetails(Integer i) throws AccountException;
}
