package com.team2.movie.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class DBConnection {
	
	@Test
	public void test() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver"); // 마리아DB
		
		Connection con = DriverManager.getConnection("jdbc:mariadb://114.200.5.121:65533/cgv", "CGV", "1234");// 마리아DB
		Statement stmt = con.createStatement();
		System.out.println("DBConnected");
		
		ResultSet rs = stmt.executeQuery("select * from mytable");
		while(rs.next()) {
		        System.out.println(rs.getString(1)+" : "+rs.getString(2));
		}
	}
}
