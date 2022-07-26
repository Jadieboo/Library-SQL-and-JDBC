package JUnit_Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.testing.junit.Database;

public class db_Close {


	@Test
	public void isOpenEqualsTrue() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		assertTrue(db.getIsOpen());
		System.out.println(db.getIsOpen());
	}
	
	@Test
	public void isOpenEqualsFalse() throws Exception {
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library", "root", "root");
		db.close();
		assertFalse(db.getIsOpen());
		assertNull(db.getConn());
		System.out.println(db.getIsOpen());

	}


}
