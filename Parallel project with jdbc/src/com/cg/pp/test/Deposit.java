package com.cg.pp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class Deposit {

	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Connection connection=DatabaseConnection.getConnection();
		
		String query="select number from account where account.name in ('Mrinaal')";
		Statement st=connection.createStatement();
	
		ResultSet rs=st.executeQuery(query);
		int n=0;
		while(rs.next()){
			n=rs.getInt("number");
		}
		
	
	
		assertEquals(1100, aserv.deposit(n, 100), 0);
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
