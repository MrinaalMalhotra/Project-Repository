package com.cg.pp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class ShowBalance {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		int b=aserv.createAccount(acc);
		assertEquals(100, aserv.showBalance(acc.getNumber()), 0);
		
	}
	@Test (expected= AccountException.class)
	public void testWrongInput() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		
		assertEquals(100, aserv.showBalance(acc.getNumber()), 0);
		
	}
}
