package com.cg.pp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class FundTransfer {

	@Test
	public void test() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		aserv.createAccount(acc);
		aserv.createAccount(acc1);
		assertEquals(70, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 30), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		aserv.createAccount(acc);
		assertEquals(70, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 30), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput2() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		aserv.createAccount(acc1);
		assertEquals(70, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 30), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput3() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		
		assertEquals(70, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 30), 0);
	}
	@Test (expected=AccountException.class)
	public void testWroungInput4() throws AccountException{
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		aserv.createAccount(acc);
		aserv.createAccount(acc1);
		assertEquals(70, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 0), 0);
	}


}
