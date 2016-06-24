package org.morozko.java.mod.web.servlet.sql;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.ent.servlet.context.ContextHelper;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.web.servlet.config.BasicConfig;

public class SqlModule extends BasicConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5510904149852039264L;

	public void configure(Properties props) throws ConfigException {
		try {
			String configParam = props.getProperty( "sql-config" );
			configure(configParam, this.getConfigContext().getContext() );
		} catch (Exception e) {
			LogFacade.getLog().error( "SqlConfig.configure() error "+e, e );
		}
	}	
	
	public static final String ATT_NAME = "sqlConfig";
	
	public static SqlConfig configure( String configParam, ServletContext context ) throws Exception {
		File configFile = ContextHelper.resolvePath( context, configParam );
		FileInputStream fis = new FileInputStream( configFile );
		SqlConfig sqlConfig = SqlConfig.configure( fis );
		fis.close();
		context.setAttribute( ATT_NAME , sqlConfig );
		return sqlConfig;
	}
		
}
