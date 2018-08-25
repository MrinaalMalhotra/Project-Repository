package com.cg.pp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;

import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class PrintTransactions {

	
	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em=emf.createEntityManager();
		Account acc=em.find(Account.class, 5);
		aserv.deposit(acc.getNumber(), 100);
		aserv.deposit(acc.getNumber(), 300);
		aserv.withdraw(acc.getNumber(), 50);
		
		String e="deposit:100.0 deposit:300.0 withdraw:50.0";
		String a=aserv.printTransactions(acc.getNumber());
		assertEquals(e,a);
		
	}
	@Test( expected= AccountException.class)
	public void testWrounginput() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		
		aserv.deposit(acc.getNumber(), 100);
		aserv.deposit(acc.getNumber(), 300);
		aserv.withdraw(acc.getNumber(), 50);
		
		String e=" \n"+"Deposit:100.0"+"\n"+"Deposit:300.0"+"\n"+"Withdraw:50.0"+"\n";
		String a=aserv.printTransactions(acc.getNumber());
		assertEquals(e,a);
		
	}
	

}
