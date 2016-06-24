package org.morozko.java.mod.web.servlet.config;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.util.PropsIO;

public class LogMorozkoConfig extends BasicConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775414520947736132L;

	public void configure(Properties props) throws ConfigException {
		this.getLog().info( "configure start" );
		String configPath = props.getProperty( "log-config" );
		try {
			this.getLog().info( "configure path : "+configPath );
			LogFacade.configure( PropsIO.loadFromStream( this.getConfigContext().resolveStream( configPath ) ) );
		} catch (Exception e) {
			throw ( new ConfigException( e ) );
		}
		this.getLog().info( "configure [OK]" );
	}

}
