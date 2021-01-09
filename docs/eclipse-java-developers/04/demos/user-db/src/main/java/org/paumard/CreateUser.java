package org.paumard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CreateUser {

	public static void main(String[] args) throws SQLException {

		String databaseURL = "jdbc:mysql://localhost/pluralsight";
		String dbUser = "pluralsight";
		String password = "pluralsight";
		Connection connection = 
			DriverManager.getConnection(databaseURL, dbUser, password);
		
		PreparedStatement psmt = 
			connection.prepareStatement("insert into user (name, age) values(?, ?)");
		
		psmt.setString(1, "Mary");
		psmt.setInt(2, 32);
		psmt.addBatch();
		
		psmt.setString(1, "James");
		psmt.setInt(2, 27);
		psmt.addBatch();
		
		psmt.setString(1, "Patricia");
		psmt.setInt(2, 29);
		psmt.addBatch();
		
		psmt.setString(1, "John");
		psmt.setInt(2, 42);
		psmt.addBatch();
		
		int[] executeBatch = psmt.executeBatch();
		for (int n : executeBatch) {
			System.out.println("n = " + n);
		}
	}
}
