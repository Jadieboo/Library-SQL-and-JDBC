package JUnit_Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Database_Method.class, db_Close.class, db_Query.class })
public class db_AllTests {

}
