package com.cg.pp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;

public class AccountDaoImpl implements AccountDao {

	
	HashMap<Integer,Account> database=new HashMap<Integer,Account>();
	HashMap<Integer,String> Transactions =new HashMap<Integer,String>();
	
	@Override
	public Integer createAccount(Account acc) {
		database.put(acc.getNumber(),acc);
		Transactions.put(acc.getNumber(), " ");
		return acc.getNumber();
	}

	@Override
	public float showBalance(Integer accId) throws AccountException {
		
		Account acc=new Account();
		if(database.containsKey(accId)){
			acc=database.get(accId);
			return acc.getBalance();
		}
		else
			throw new AccountException("please enter a valid Account number");
			
			
			
	}

	@Override
	public float deposit(Integer id,float amount) throws AccountException {
		Account acc=new Account();
		if(amount<=0){
			throw new AccountException("Amount invalid");
		}
		if(database.containsKey(id)){
			acc=database.get(id);
			acc.setBalance(acc.getBalance()+amount);
			String t="Deposit:"+amount;
			if(Transactions.containsKey(id)){
				String s=Transactions.get(id);
				s=s+"\n"+t;
				Transactions.put(id, s);
			}
			return acc.getBalance();
		}
		else
			throw new AccountException("please enter a valid Account number");
		
	}

	@Override
	public float withdraw(Integer id, float amount) throws AccountException {
		Account acc=new Account();
		if(database.containsKey(id)){
			acc=database.get(id);
			float b=acc.getBalance()-amount;
			if(b<0)
				throw new AccountException("Not enough amount");
			
			acc.setBalance(acc.getBalance()-amount);
			String t="Withdraw:"+amount;
			
			if(Transactions.containsKey(id)){
				String s=Transactions.get(id);
				s=s+"\n"+t;
				Transactions.put(id, s);
			}
			
			return acc.getBalance();
		}
		else
			throw new AccountException("please enter a valid Account number");
		
		
	}

	@Override
	public float fundTransfer(Integer senderAccId, Integer receiverAccId, float amount) throws AccountException {
		if(amount<=0)
			throw new AccountException("Invalid Exception ");
		Account sender=new Account();
		Account receiver =new Account();
		if(database.containsKey(senderAccId)&&database.containsKey(receiverAccId)){
			sender=database.get(senderAccId);
			receiver=database.get(receiverAccId);
			sender.setBalance(sender.getBalance()-amount);
			receiver.setBalance(receiver.getBalance()+amount);
			String t="Transfer: "+amount+" to id:"+receiverAccId;
			
			if(Transactions.containsKey(senderAccId)){
				String s=Transactions.get(senderAccId);
				s=s+"\n"+t;
				Transactions.put(senderAccId, s);
			}
			
			return sender.getBalance();
			
		}
		else
			throw new AccountException("please enter a valid account number");
		
		
	}

	@Override
	public String printTransactions(Integer accId) throws AccountException {
		
		String lm;
		if(Transactions.containsKey(accId)){
			return Transactions.get(accId);
		}
		else
			throw new AccountException("please enter a valid Account number");
		
		
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
	public boolean getDetails(Integer i) throws AccountException {
		if(database.containsKey(i)){
			return true;
		}
		else
			throw new AccountException("Please enter a valid number");
	}

}
