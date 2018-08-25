package com.cg.pp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
public class Account {
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	int number;
	@JoinColumn (name="aadharNo")
	@OneToOne(cascade= CascadeType.ALL)
	Person Accholder;
	float balance;
	
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(Person accholder, float balance) {
		super();
		
		Accholder = accholder;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [number=" + number + ", Accholder=" + Accholder
				+ ", balance=" + balance + "]";
	}
	public int getNumber() {
		return number;
	}
	
	public Person getAccholder() {
		return Accholder;
	}
	public void setAccholder(Person accholder) {
		Accholder = accholder;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	

}
