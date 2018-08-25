package com.cg.pp.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class CreateAccount {

	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Mrinaal",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,0);
		if(aserv.validateDetails(p)){
			int b=aserv.createAccount(acc);
			assertEquals(acc.getNumber(),b);
		}
	}
	@Test(expected= AccountException.class)
	public void testwrounginput() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Mrinaal",23,"1345724698373","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,0);
		if(aserv.validateDetails(p)){
			int b=aserv.createAccount(acc);
			assertEquals(acc.getNumber(),b);
		}
	}

	@Test(expected =AccountException.class)
	public void testWronginput2() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Mrinaal",23,"1345724698373583","999970","twinkle@gmail.com");
		Account acc=new Account(p,0);
		if(aserv.validateDetails(p)){
			int b=aserv.createAccount(acc);
			assertEquals(acc.getNumber(),b);
		}
	}
	@Test(expected=AccountException.class)
	public void testWrounginput3() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Mrinaal",23,"1345724698373583","9999707207","twinkle");
		Account acc=new Account(p,0);
		if(aserv.validateDetails(p)){
			int b=aserv.createAccount(acc);
			assertEquals(acc.getNumber(),b);
		}
	}
}
