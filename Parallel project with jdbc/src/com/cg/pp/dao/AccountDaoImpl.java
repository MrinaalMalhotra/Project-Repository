package com.cg.pp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;

public class AccountDaoImpl implements AccountDao {

	
		Connection connection=DatabaseConnection.getConnection();
		String query;
	@Override
	public Integer createAccount(Account acc) throws SQLException {
		query="select max(number) from account";
		int number = 0;
		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()){
			number=rs.getInt(1)+1;
		}
		acc.setNumber(number);
		query="Insert into Account values (?,?,?,?,?,?,?) ";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1, acc.getAccholder().getName());
		pst.setInt(2, acc.getNumber());
		pst.setString(3, acc.getAccholder().getMobileNo());
		pst.setString(4, acc.getAccholder().getAadharNo());
		pst.setString(5,acc.getAccholder().getEmailId());
		pst.setInt(6, acc.getAccholder().getAge());
		pst.setFloat(7, acc.getBalance());
		pst.executeUpdate();
		return acc.getNumber();
	}

	@Override
	public float showBalance(Integer accId) throws AccountException, SQLException {
		int count=getDetails(accId);
		
		if(count==0)
			throw new AccountException("Please enter a valid account number");
		
		query="Select balance from Account where number="+accId;
		Statement st=connection.createStatement();
		float b=0;
		ResultSet rs=st.executeQuery(query);
		while(rs.next()){
			b=rs.getFloat(1);
		}
		
		return b;
	}

	@Override
	public float deposit(Integer id, float amount) throws AccountException, SQLException {
		
		
		if(amount<=0){
			throw new AccountException("please enter valid amount");
		}
		
		float b=showBalance(id);
		query="update account set balance=? where number=?";
		PreparedStatement pst= connection.prepareStatement(query);
		pst.setFloat(1,b+amount);
		pst.setInt(2, id);
		int r=pst.executeUpdate();
		if(r==0){
			throw new AccountException("please enter a valid Account number");
		}
		query="Insert into transactions values(?,?)";
		PreparedStatement pst1= connection.prepareStatement(query);
		pst1.setInt(1, id);
		pst1.setString(2, "Deposit:"+amount);
		pst1.executeUpdate();
		
		return b+amount;
	}

	@Override
	public float withdraw(Integer id, float amount) throws AccountException, SQLException {
		float b=showBalance(id);
		if(b-amount<=0){
			
			throw new AccountException("Not enough balance");
		}
		
		query="update account set balance=? where number=?";
		PreparedStatement pst= connection.prepareStatement(query);
		pst.setFloat(1,b-amount);
		pst.setInt(2, id);
		int r=pst.executeUpdate();
		if(r==0)
			throw new AccountException("pleas eneter a valid Account number");
		query="Insert into transactions values(?,?)";
		PreparedStatement pst1= connection.prepareStatement(query);
		pst1.setInt(1, id);
		pst1.setString(2, "Withdraw:"+amount);
		pst1.executeUpdate();
		
		return b-amount;
	}

	@Override
	public float fundTransfer(Integer senderAccId, Integer receiverAccId,
			float amount) throws AccountException, SQLException {
		if(amount<=0)
			throw new AccountException("Invalid amount");
		
		
		float bs=showBalance(senderAccId);
		float br=showBalance(receiverAccId);
		query ="update account set balance=? where number=?";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setFloat(1, bs-amount);
		pst.setInt(2,senderAccId);
		int r=pst.executeUpdate();
		pst.setFloat(1, br+amount);
		pst.setInt(2,receiverAccId);
		r=r+pst.executeUpdate();
		if(r<2){
			throw new AccountException("please enter a valid Account number");
		}
		query="Insert into transactions values(?,?)";
		PreparedStatement pst1= connection.prepareStatement(query);
		pst1.setInt(1, senderAccId);
		pst1.setString(2, "Transfer:"+amount+" to :"+ receiverAccId);
		pst1.executeUpdate();
		pst1.setInt(1, receiverAccId);
		pst1.setString(2, "Transferred"+amount+" from: "+senderAccId);
		pst1.executeUpdate();
		return bs-amount;
	}

	@Override
	public String printTransactions(Integer accId) throws AccountException, SQLException {
		query="Select name from Transactions where id="+accId;
		String s="";
		Statement st=connection.createStatement();
		ResultSet rs= st.executeQuery(query);
		int count=0;
		while(rs.next()){
			s=s+rs.getString("name")+"\n";
			count++;
		}
		if(count==0)
			throw new AccountException("please enter a valid number");
		return s;
	}

	@Override
	public boolean validateDetails(Person p) throws AccountException {
		int ct=0;
		String s=null;
		if(p.getAadharNo().length()==16){
			ct++;
			
		}
		else
			s=" Aadhar details invalid\n";
			
		if(p.getEmailId().contains("@")&&p.getEmailId().contains(".com")){
			ct++;
			
		}
		else
			s=s+"email id invalid\n";
		if(p.getMobileNo().length()==10){
			ct++;
			
		}
		else
			s=s+"mobile number invalid\n";
		if(ct==3)
			return true;
		else
		    throw new AccountException(s);
	}

	@Override
	public Integer getDetails(Integer i) throws AccountException, SQLException {
		query="select * from account where number="+i;
		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery(query);
		int count=0;
		while(rs.next())
			count++;
		if(count==0)
			throw new AccountException("Please enter a valid account number");
		return count;
	}

}
