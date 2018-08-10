package com.cg.pp.beans;



public class Account {
	
	int number;
	Person Accholder;
	float balance;
	static int numGenerator=100;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(Person accholder, float balance) {
		super();
		numGenerator++;
		this.number = numGenerator;
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
