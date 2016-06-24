package org.morozko.java.mod.web.conf;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.db.connect.ConnectionFacade;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.servlet.config.BasicConfig;

public class WebModuleConfig extends BasicConfig {

	public void configure(Properties props) throws ConfigException {
		this.getLog().info( "configure start" );
		String moduleDS = props.getProperty( "ds-web-module" );
		try {
			this.getLog().info( "configure ds-web-module : "+moduleDS );
			WebDAOFactory.init( ConnectionFacade.getFactory( moduleDS ) );
		} catch (Exception e) {
			throw ( new ConfigException( e ) );
		}
		this.getLog().info( "configure [OK]" );
	}

}
