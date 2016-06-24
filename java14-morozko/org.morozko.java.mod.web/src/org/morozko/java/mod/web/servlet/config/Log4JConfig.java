package org.morozko.java.mod.web.servlet.config;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.util.PropsIO;

public class Log4JConfig extends BasicConfig {

	public void configure(Properties props) throws ConfigException {
		this.getLog().info( "configure start" );
		String configPath = props.getProperty( "log4j-config" );
		try {
			this.getLog().info( "configure path : "+configPath );
			PropertyConfigurator.configure( PropsIO.loadFromStream( this.getConfigContext().resolveStream( configPath ) ) );
		} catch (Exception e) {
			throw ( new ConfigException( e ) );
		}
		this.getLog().info( "configure [OK]" );
	}

}
