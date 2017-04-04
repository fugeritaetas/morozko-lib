package db.connect;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Properties;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.junit.Test;

public class TestConnectionFactory {

	@Test
	public void testDirectConnectionDataSource() {
		try {
			String drv = "org.apache.derby.jdbc.EmbeddedDriver"; 
			String url = "jdbc:derby:memory:myDB;create=true";
			String usr = "dbTestUser";
			String pwd = "dbTestPass";
			String mode = ConnectionFactoryImpl.PROP_CF_MODE_DC;
			
			Properties props = new Properties();
			props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE , mode );
			props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_DRV , drv );
			props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_URL , url );
			props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_USR , usr );
			props.setProperty( ConnectionFactoryImpl.PROP_CF_MODE_DC_PWD , pwd );
			
			ConnectionFactory cf = ConnectionFactoryImpl.newInstance( props );
		
			Connection conn = cf.getConnection();
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println( "TEST OK "+dbmd.getDatabaseProductName() );
			conn.close();
			
		} catch (Exception e) {
			fail( "Exception "+e );	
		}
		
	}

}
