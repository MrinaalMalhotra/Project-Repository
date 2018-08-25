package com.cg.pp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Deposit {

	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em=emf.createEntityManager();
		Account acc=em.find(Account.class, 3);
		assertEquals(100, aserv.deposit(acc.getNumber(), 100), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("pooja",34,"9878123234233423","989911474787","pooja@gmail.com");
		Account acc=new Account(p,100);
		
		assertEquals(300, aserv.deposit(acc.getNumber(), 200), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput2() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Mrinaal",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,0);
		
		assertEquals(300, aserv.deposit(acc.getNumber(), -45), 0);
	}

}
