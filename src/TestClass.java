import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestClass {
	
	@Test
	public void testConnection() throws SQLException {
	  Login log = new Login();
	  assertNotNull(log.connect());	
	}
	@Test
	public void testExecuteQuery() throws SQLException  {
	
		Login log = new Login();
	 adminface adm = new adminface();
	 face cas =new face();
	 assertNotNullassertNotNull(adm.fillComboBox());	
	 assertNotNullassertNotNull(adm.run());	 
	 assertNotNullassertNotNull(adm.refreshTable());	
	 assertNotNullassertNotNull(cas.fillComboBox());	
	 assertNotNullassertNotNull(cas.run());	 
	 assertNotNullassertNotNull(cas.refreshTable());
	 assertNotNullassertNotNull(adm.executeQuery("select * from tickets;",adm.connect()));	
	 assertNotNullassertNotNull(adm.executeQuery("select * from users;",adm.connect()));
	 assertNotNullassertNotNull(cas.executeQuery("select * from tickets;",cas.connect()));	
	 assertNotNullassertNotNull(cas.executeQuery("select * from users;",cas.connect()));
	 assertNotNullassertNotNull(log.executeQuery("select * from tickets;",log.connect()));	
	 assertNotNullassertNotNull(log.executeQuery("select * from users;",log.connect()));
	}
	
	

	private void assertNotNullassertNotNull(ResultSet executeQuery) {
		// TODO Auto-generated method stub
		
	}
	@Test
	public void testDisconnect() throws SQLException {
		 adminface adm = new adminface();
		  face cas =new face();
	    Login log = new Login();
	    adm.disconnect(adm.connect());
	    cas.disconnect(cas.connect());
	    log.disconnect(log.connect());
	}
	}


