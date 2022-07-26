package com.testing.junit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
	private String driver;
	private String database;
	private String username;
	private String password;
	
	private Boolean isOpen = true;
	
	public Database (String driver, String database, String username, String password) throws ClassNotFoundException {
		this.driver = driver;
		this.database = database;
		this.username = username;
		this.password = password;
		
		
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
			
	}//end of constructor
	
	public void query(String query) throws SQLException {
		this.stmt = this.conn.createStatement();
		this.rs = stmt.executeQuery(query);
		this.rsmd = this.rs.getMetaData();
		
	}//end of query method
	
	public void printResults() throws SQLException {
		int totalFields = this.rsmd.getColumnCount();
		
		while(rs.next()) {
			for(int i = 1; i <= totalFields; i++) {
				if(i > 1) System.out.println(",");
				String fieldValue  = rs.getString(i);
				System.out.println(fieldValue);
			}//end of for loop
			System.out.println();
		}//end of while loop
		
	}// end of printResults 
	
	// method to close the connection
	public void close() {
		if (isOpen == false) return;
		try {
		    if (conn != null) {
		    conn.close();
		    }
		} catch (Exception e) {
		    // do something
		}
		isOpen = false;
		conn = null;
		}
	
	// getters and setters

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ResultSetMetaData getRsmd() {
		return rsmd;
	}

	public void setRsmd(ResultSetMetaData rsmd) {
		this.rsmd = rsmd;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}
