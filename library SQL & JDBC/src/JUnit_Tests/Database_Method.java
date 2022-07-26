package JUnit_Tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import com.testing.junit.Database;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Database_Method {

	@Test
	public void connectionShouldNotBeNull() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		assertNotNull(db.getConn());
	}
	
	@Test
	public void connectionShouldBeNull() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		db.close();
		assertNull(db.getConn());
	}
	
	@Test
	public void usernameShouldEqualRoot() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		assertEquals("root", db.getUsername());
	}
	
	@Test
	public void passwordShouldEqualRoot() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		assertEquals("root", db.getPassword());
	}
	
	
}
