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

public class ShowBalance {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Connection connection=DatabaseConnection.getConnection();
		String name="Mrinaal";
		String query="select number from account where account.name in ('Mrinaal')";
		Statement st=connection.createStatement();
	
		ResultSet rs=st.executeQuery(query);
		int n=0;
		while(rs.next()){
			n=rs.getInt("number");
		}
		
		assertEquals(1000, aserv.showBalance(n), 0);
		
	}
	@Test (expected= AccountException.class)
	public void testWrongInput() throws AccountException, SQLException {
		AccountService aserv=new AccountServiceImpl();
		Person p=new Person("Twinkle",23,"1345724698373583","9999707207","twinkle@gmail.com");
		Account acc=new Account(p,100);
		
		assertEquals(100, aserv.showBalance(acc.getNumber()), 0);
		
	}
}
