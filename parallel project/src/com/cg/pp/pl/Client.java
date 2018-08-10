package com.cg.pp.pl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.pp.beans.Account;
import com.cg.pp.beans.Person;
import com.cg.pp.exception.AccountException;
import com.cg.pp.service.AccountService;
import com.cg.pp.service.AccountServiceImpl;

public class Client {
	public static void main(String args[]){
		
		AccountService aserv=new AccountServiceImpl();
		
		int option;
		Scanner sc=new Scanner(System.in);
		do{
			System.out.println("main menu:");
			System.out.println("1.create Account");
			System.out.println("2.Show balance");
			System.out.println("3.withdraw money");
			System.out.println("4.Deposit money");
			System.out.println("5.Funds transfer");
			System.out.println("6.print Transactions");
			System.out.println("7.Exit");
			System.out.println("Choose option");
			option=sc.nextInt();
			switch(option){
			case 1: System.out.println("enter your name:");
					String name;
					name=sc.next();
					System.out.println("Enter your age");
					int age;
					age=sc.nextInt();
					System.out.println("enter your 16 digit Aadhar number");
					String aadhar=sc.next();
					System.out.println("enter your mobile no");
					String mobile=sc.next();
					System.out.println("enter your emailId");
					String emailid=sc.next();
					int balance=0;
					Person p=new Person(name,age,aadhar,mobile,emailid);
				try {
					if(aserv.validateDetails(p)){
					
						Account acc=new Account(p,balance);
						System.out.println("account created with number:"+aserv.createAccount(acc));
					}
					
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
					
					break;
			case 2: System.out.println("enter your Account number");
				    int number=sc.nextInt();
				try {
					System.out.println("your balance is:"+aserv.showBalance(number));
				} catch (AccountException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 3:System.out.println("Enter your account number");
			       int num=sc.nextInt();
			       System.out.println("Enter the amount you want to withdraw");
			       int amount=sc.nextInt();
			       try {
					System.out.println("After withdrawal your balance is:"+aserv.withdraw(num, amount));
				} catch (AccountException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 4:System.out.println("Enter your Account number");
				   int num1=sc.nextInt();
				   try {
					aserv.getDetails(num1);
				   } catch (AccountException e1) {
					
					System.out.println(e1.getMessage());
					break;
				   }
				   System.out.println("Enter the amount you want to deposit");
				   int amount1=sc.nextInt();
				   try {
					System.out.println("Your Account balance after deposit is:"+aserv.deposit(num1, amount1));
				   } catch (AccountException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 5:System.out.println("Enter your account number");
			       int sendernum=sc.nextInt();
			       try {
						aserv.getDetails(sendernum);
					   } catch (AccountException e1) {
						
						System.out.println(e1.getMessage());
						break;
					   }
			       System.out.println("enter beneficiary's account number");
			       int receivernum=sc.nextInt();
			       try {
						aserv.getDetails(receivernum);
					   } catch (AccountException e1) {
						
						System.out.println(e1.getMessage());
						break;
					   }
			       System.out.println("Enter the amount you want to transfer");
			       int amount2=sc.nextInt();
			       try {
					System.out.println("your balance after transfer:"+aserv.fundTransfer(sendernum, receivernum, amount2));
				} catch (AccountException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				break;
			case 6:System.out.println("Enter your account number");
					int id1=sc.nextInt();
					String lm;
				try {
					lm=aserv.printTransactions(id1);
					System.out.println(lm);
				} catch (AccountException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
					break;
			case 7:System.out.println("Thank you for using our application");
				   System.exit(0);
			default:System.out.println("please enter a valid option");
			}
			
			
			
		
	}while(option>=1&&option<=7);
	}

}
