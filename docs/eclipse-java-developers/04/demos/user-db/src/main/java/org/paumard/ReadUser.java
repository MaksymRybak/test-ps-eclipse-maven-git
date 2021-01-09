package org.paumard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.paumard.model.User;

public class ReadUser {

	public static void main(String[] args) throws SQLException {

		String databaseURL = "jdbc:mysql://localhost/pluralsight";
		String dbUser = "pluralsight";
		String password = "pluralsight";
		Connection connection = 
			DriverManager.getConnection(databaseURL, dbUser, password);
		
		Statement smt = connection.createStatement();
		ResultSet resultSet = smt.executeQuery("select name, age from user");
		
		List<User> users = new ArrayList<>();
		while(resultSet.next()) {
			
			String name = resultSet.getString(1);
			int age = resultSet.getInt(2);
			User user = new User(name, age);
			users.add(user);
		}
		users.forEach(System.out::println);
	}
}
