package com.cg.pp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions {
	
	@Id
	Integer id;
	
	String name;
	
	
	@Override
	public String toString() {
		return name ;
	}
	public Transactions(Integer id, String name) {
		super();
		this.id=id;
		this.name = name;
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
