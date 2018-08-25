package com.cg.pp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.dao.DatabaseConnection;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class FundTransfer {

	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Connection connection=DatabaseConnection.getConnection();
		
		String query="select number from account where account.name in ('Mrinaal')";
		Statement st=connection.createStatement();
	
		ResultSet rs=st.executeQuery(query);
		int n1=0;
		while(rs.next()){
			n1=rs.getInt("number");
		}
		
		
		
		String query1="select number from account where account.name in ('twinkle')";
		Statement st1=connection.createStatement();
	
		ResultSet rs1=st.executeQuery(query);
		int n2=0;
		while(rs1.next()){
			n2=rs1.getInt("number");
		}
		
		assertEquals(700, aserv.fundTransfer(n1, n2, 300), 0);
	}
	@Test( expected = AccountException.class)
	public void testWrounginput() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Connection connection=DatabaseConnection.getConnection();
		
		String query="select number from account where account.name in ('Mrinaal')";
		Statement st=connection.createStatement();
	
		ResultSet rs=st.executeQuery(query);
		int n=0;
		while(rs.next()){
			n=rs.getInt("number");
		}
		Person p1=new Person("Geetika",27,"1345987614763964","9811971146","geetika@gmail.com");
		Account acc1=new Account(p1,200);
		
		assertEquals(70, aserv.fundTransfer(n, acc1.getNumber(), 30), 0);
	}
	
	@Test( expected = AccountException.class)
	public void testWrounginput3() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		Connection connection=DatabaseConnection.getConnection();
		String name="Mrinaal";
		String query="select number from account where account.name in ('Mrinaal')";
		Statement st=connection.createStatement();
	
		ResultSet rs=st.executeQuery(query);
		int n=0;
		while(rs.next()){
			n=rs.getInt("number");
		}
		
		assertEquals(700, aserv.fundTransfer(acc.getNumber(), n, 300), 0);
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
