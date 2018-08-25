package com.cg.pp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.beans.Transactions;
import com.cg.pp.exception.AccountException;

public class AccountDaoImpl implements AccountDao {

	
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("JPA-PU");
	EntityManager em=emf.createEntityManager();
	
	@Override
	public Integer createAccount(Account acc) {
		em.getTransaction().begin();
		
		em.persist(acc);
		em.flush();
		em.getTransaction().commit();
		return acc.getNumber();
		
	}

	@Override
	public float showBalance(Integer accId) throws AccountException {
		em.getTransaction().begin();
		Account acc= em.find(Account.class, accId);
		if(acc==null){
			em.getTransaction().commit();
			throw new AccountException("please enter a valid account number");
		}
		float b=acc.getBalance();
		em.getTransaction().commit();
		return b;	
	}

	@Override
	public float deposit(Integer id,float amount) throws AccountException {
		
		
			em.getTransaction().begin();
			Account acc=em.find(Account.class, id);
			if(acc==null){
				em.getTransaction().commit();
				throw new AccountException("please enter a valid account number");
			}
			acc.setBalance(acc.getBalance()+amount);
			em.merge(acc);
			
			Transactions tt=em.find(Transactions.class, id);
			if(tt==null){
				em.persist(new Transactions(id,"deposit:"+amount));
			}
			else 
			{
				tt.setName(tt.getName()+" deposit:"+amount);
				em.merge(tt);
			}
			em.getTransaction().commit();
			return acc.getBalance();
		
	}

	

	@Override
	public float fundTransfer(Integer senderAccId, Integer receiverAccId, float amount) throws AccountException {
		em.getTransaction().begin();
		Account acc1=em.find(Account.class, senderAccId);
		if(acc1==null){
			em.getTransaction().commit();
			throw new AccountException("please enter a valid account number");
		}
		Account acc2=em.find(Account.class, receiverAccId);
		if(acc2==null){
			em.getTransaction().commit();
			throw new AccountException("please enter a valid account number");
		}
		acc1.setBalance(acc1.getBalance()-amount);
		acc2.setBalance(acc2.getBalance()+amount);
		em.merge(acc1);
		em.merge(acc2);
		Transactions tt=em.find(Transactions.class, senderAccId);
		
		if(tt==null){
			em.persist(new Transactions(senderAccId,"Transfer:"+amount +" to:"+receiverAccId));
		}
		else 
		{
			tt.setName(tt.getName()+"Transfer:"+amount +" to:"+receiverAccId);
			em.merge(tt);
		}
		Transactions tt1=em.find(Transactions.class, receiverAccId);
		
		if(tt1==null){
			em.persist(new Transactions(receiverAccId,"Transfered :"+amount +" from:"+senderAccId));
		}
		else 
		{
			tt1.setName(tt1.getName()+"Transfered :"+amount +" from:"+senderAccId);
			em.merge(tt1);
		}
		
		em.getTransaction().commit();
		return acc1.getBalance();
		
		
	}

	@Override
	public String printTransactions(Integer accId) throws AccountException {
		em.getTransaction().begin();
		Transactions t=em.find(Transactions.class, accId);
		if(t== null)
		{
			throw new AccountException("please enter a valid acount number");
		}
		em.getTransaction().commit();
		return t.toString();
		
		
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
		Account acc=em.find(Account.class, i);
		if(acc==null)
			throw new AccountException("please enter a valid account number");
		
		
		return true;
		
	}

	@Override
	public float withdraw(Integer id, float amount) throws AccountException {
		
		
			em.getTransaction().begin();
			Account acc=em.find(Account.class, id);
			if(acc==null){
				em.getTransaction().commit();
				throw new AccountException("please enter a valid account number");
			}
			if(acc.getBalance()-amount<0){
				throw new AccountException("not enough funds");
			}
			acc.setBalance(acc.getBalance()-amount);
			em.merge(acc);
			Transactions tt=em.find(Transactions.class, id);
			if(tt==null){
				em.persist(new Transactions(id,"withdraw:"+amount));
			}
			else 
			{
				tt.setName(tt.getName()+" withdraw:"+amount);
				em.merge(tt);
			}
			em.getTransaction().commit();
			return acc.getBalance();
	
		
	}
}
