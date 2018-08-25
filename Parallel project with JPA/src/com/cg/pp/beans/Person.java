package com.cg.pp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	String name;
	Integer age;
	@Id
	String aadharNo;
	String mobileNo;
	String emailId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, Integer age, String aadharNo, String mobileNo,
			String emailId) {
		super();
		this.name = name;
		this.age = age;
		this.aadharNo = aadharNo;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", aadharNo="
				+ aadharNo + ", mobileNo=" + mobileNo + ", emailId=" + emailId
				+ "]";
	}
	

}
