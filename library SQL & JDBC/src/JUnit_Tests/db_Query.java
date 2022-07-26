package JUnit_Tests;

import static org.junit.Assert.*;
import com.testing.junit.Database;
import org.junit.Test;

public class db_Query {
	
	// not null
	
	@Test
	public void ResultSetShouldNotBeNull() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		db.query("SELECT ISBN FROM Books");
		db.printResults();
		assertNotNull(db.getRs());
	}
	
	@Test
	public void ResultSetMetaDataShouldNotBeNull() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		db.query("SELECT title FROM books");
		db.printResults();
		assertNotNull(db.getRsmd());
	}
	
	@Test
	public void StatementShouldNotBeNull() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		db.query("SELECT first_name FROM users");
		db.printResults();
		assertNotNull(db.getStmt());
	}
	
	
	@Test
	public void allBooks() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		System.out.println("All users:");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
		db.query("SELECT * FROM books;");
		db.printResults();
		assertNotNull(db.getRs());
		assertNotNull(db.getRsmd());
		assertNotNull(db.getStmt());
	
	}
	
	@Test
	public void booksLowInStock() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		System.out.println("Books low in stock");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		db.query("SELECT title FROM books WHERE stock <= 3;");
		db.printResults();
		assertNotNull(db.getRs());
		assertNotNull(db.getRsmd());
		assertNotNull(db.getStmt());
	}

}
