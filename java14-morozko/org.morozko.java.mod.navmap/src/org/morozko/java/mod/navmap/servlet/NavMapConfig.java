package org.morozko.java.mod.navmap.servlet;

import java.util.Properties;


import javax.servlet.ServletException;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.navmap.Version;
import org.morozko.java.mod.web.servlet.config.BasicConfig;


public class NavMapConfig extends BasicConfig {

	public void configure(Properties props) throws ConfigException {
		try {
			String validate = props.getProperty( "validate-dtd" );
			String noAccessPage = props.getProperty( "no_access" );
			String authHandlerType = props.getProperty( "auth_handler" );
			String navMapConfig = props.getProperty( "nav_map" );
			try {
				this.getLog().info( "navController version "+Version.getVersion() );
				NavController navController = new NavController();
				navController.initController( validate, noAccessPage, authHandlerType, navMapConfig, this.getConfigContext() );
				this.getLog().info( "navController config [OK]" );	
				this.getConfigContext().setAttribute( NavController.ATT_NAME_NAVCONTROLLER, navController );
			} catch ( Exception e ) {
				this.getLog().info( "navController config [KO]" );
				this.getLog().fatal( "Errore generale creazione NavFilter", e );
				throw ( new ServletException( e ) );
			}	
		} catch (Exception e) {
			throw ( new ConfigException( e ) );
		}
	}

}
