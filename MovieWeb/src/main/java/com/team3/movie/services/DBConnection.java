package com.team3.movie.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;

public class DBConnection {

	@Test
	public void test() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver"); // 마리아DB
		// Class.forName("com.mysql.jdbc.Driver"); MySQL
		// 이게 추가 될까요?
		Connection con = DriverManager.getConnection("jdbc:mariadb://114.200.5.121:65533/cgv", "CGV", "1234");// 마리아DB
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from mytable");


		// DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","passwd");
		// MySQL
		System.out.println("DBConnected");
	}
}
