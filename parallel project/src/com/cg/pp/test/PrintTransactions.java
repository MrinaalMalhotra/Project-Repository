package com.cg.pp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class PrintTransactions {

	@Test
	public void test() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		aserv.createAccount(acc);
		aserv.deposit(acc.getNumber(), 100);
		aserv.deposit(acc.getNumber(), 300);
		aserv.withdraw(acc.getNumber(), 50);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		aserv.createAccount(acc1);
		aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 50);
		String e=" \n"+"Deposit:100.0"+"\n"+"Deposit:300.0"+"\n"+"Withdraw:50.0"+"\n"+"Transfer: 50.0 to id:102";
		String a=aserv.printTransactions(acc.getNumber());
		assertEquals(e,a);
		
	}
	@Test( expected= AccountException.class)
	public void testWrounginput() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		
		aserv.deposit(acc.getNumber(), 100);
		aserv.deposit(acc.getNumber(), 300);
		aserv.withdraw(acc.getNumber(), 50);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		aserv.createAccount(acc1);
		aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 50);
		String e=" \n"+"Deposit:100.0"+"\n"+"Deposit:300.0"+"\n"+"Withdraw:50.0"+"\n"+"Transfer: 50.0 to id:102";
		String a=aserv.printTransactions(acc.getNumber());
		assertEquals(e,a);
		
	}
	@Test( expected= AccountException.class)
	public void testWrounginput2() throws AccountException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		aserv.createAccount(acc);
		aserv.deposit(acc.getNumber(), 100);
		aserv.deposit(acc.getNumber(), 300);
		aserv.withdraw(acc.getNumber(), 50);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		
		aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 50);
		String e=" \n"+"Deposit:100.0"+"\n"+"Deposit:300.0"+"\n"+"Withdraw:50.0"+"\n"+"Transfer: 50.0 to id:102";
		String a=aserv.printTransactions(acc.getNumber());
		assertEquals(e,a);
		
	}

}
