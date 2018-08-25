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

public class FundTransfer {

	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em=emf.createEntityManager();
		Account acc=em.find(Account.class, 3);
		
		
		
		Account acc1=em.find(Account.class, 4);
		assertEquals(60, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 30), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em=emf.createEntityManager();
		Account acc=em.find(Account.class, 3);
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		
		assertEquals(70, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 30), 0);
	}
	
	@Test( expected = AccountException.class)
	public void testWrounginput3() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em=emf.createEntityManager();
		Account acc1=em.find(Account.class, 3);
		
		assertEquals(700, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 300), 0);
	}
	@Test (expected=AccountException.class)
	public void testWroungInput4() throws AccountException, SQLException{
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1234567834567892","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,230);
		Person p1=new Person("mrinaal",24,"4567234598761234","8295145194","mrinaal@gmail.com");
		Account acc1=new Account(p1,60);
		
		assertEquals(700, aserv.fundTransfer(acc.getNumber(), acc1.getNumber(), 300), 0);
	}


}
