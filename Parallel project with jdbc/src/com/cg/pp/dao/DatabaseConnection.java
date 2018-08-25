package com.cg.pp.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
public static java.sql.Connection getConnection(){
		
		java.sql.Connection connection=null;
		String url="jdbc:mysql://localhost:3306/test";
		String user="root";
		String pass="Capgemini123";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			connection=DriverManager.getConnection(url, user, pass);
			System.out.println("Connection established");
			Statement st=connection.createStatement();/*
			ResultSet rs=st.executeQuery("Select * from emp");
			while(rs.next()){
				System.out.print("Eno:"+rs.getInt("Empno")+"\t");
				System.out.println("Ename:"+rs.getString("ename"));
			}
			System.out.println("result fetched");*/
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver not loaded...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:"+e.getMessage());
		}
		
		return connection;
		
		
		
		
		
		
	}

}
