package org.morozko.java.mod.dbsrc.junit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

import org.morozko.java.core.util.PropsIO;
import org.morozko.java.mod.dbsrc.config.ConfigurationData;
import org.morozko.java.mod.dbsrc.config.Dbsrc;

public class DbsrcTestCase extends TestCase {

	private Properties props;
	
	public DbsrcTestCase() {
		this.setName( "Dbsrc Test Case" );
		try {
			this.props = PropsIO.loadFromClassLoader( "/org/morozko/java/mod/dbsrc/junit/dbsrc-test-case.properties" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testOperation() throws Exception {
		if ( "true".equalsIgnoreCase( this.props.getProperty( "test-operation" ) ) ) {
			File config = new File( this.props.getProperty( "dbsrc-config-file" ) );
			FileReader fr = new FileReader( config );
			Dbsrc dbsrc = ConfigurationData.parseConfiguration( fr );
			fr.close();
			dbsrc.exec( this.props.getProperty( "dbsrc-operation-name" ) );			
		}
	}
	
}
