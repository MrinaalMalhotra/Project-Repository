package com.cg.pp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class WithDraw {

	@Test
	public void test() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		int b=aserv.createAccount(acc);
		assertEquals(50, aserv.withdraw(acc.getNumber(), 50), 0);
		
	}
	@Test (expected= AccountException.class)
	public void testWrongInput() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		
		assertEquals(50, aserv.withdraw(acc.getNumber(), 50), 0);
		
	}
	@Test (expected= AccountException.class)
	public void testWrongInput2() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		aserv.createAccount(acc);
		assertEquals(-50, aserv.withdraw(acc.getNumber(), 150), 0);
		
	}
}
