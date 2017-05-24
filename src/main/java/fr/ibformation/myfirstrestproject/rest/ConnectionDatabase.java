package fr.ibformation.myfirstrestproject.rest;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
	
	private static ConnectionDatabase connectionDatabase;

	private Connection connection;
	
	private ConnectionDatabase() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			this.connection = 
					DriverManager.getConnection("jdbc:mysql://localhost/world",
							"root",
							"root");
		} catch (Exception e) {
			

		}
	}
	
	public Connection getConnection() {
		return connection;
	}


	public static ConnectionDatabase getConnectionDatabase() {
		if (connectionDatabase == null)
			connectionDatabase = new ConnectionDatabase();
		
		return connectionDatabase;
	}
	

}
