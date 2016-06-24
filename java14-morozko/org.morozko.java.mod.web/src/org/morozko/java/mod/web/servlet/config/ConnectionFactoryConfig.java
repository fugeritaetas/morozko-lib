package org.morozko.java.mod.web.servlet.config;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.db.connect.ConnectionFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;

public class ConnectionFactoryConfig extends BasicConfig {

	public void configure(Properties props) throws ConfigException {
		this.getLog().info( "configure start" );
		String cfName = props.getProperty( "cf-name" );
		try {
			this.getLog().info( "configure cf-name : "+cfName );
			ConnectionFactory cf = ConnectionFactoryImpl.newInstance( props );
			if ( "true".equalsIgnoreCase( props.getProperty( "cf-test" ) ) ) {
				this.getLog().info( "configure testing connection..." );
				cf.getConnection().close();
				this.getLog().info( "configure connection test [OK]" );
			} else {
				this.getLog().info( "configure connection skipped" );
			}
			ConnectionFacade.registerFactory( cfName , cf );
		} catch (Exception e) {
			throw ( new ConfigException( e ) );
		}
		this.getLog().info( "configure [OK]" );
	}

}
