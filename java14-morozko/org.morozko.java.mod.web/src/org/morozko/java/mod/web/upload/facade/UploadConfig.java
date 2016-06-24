package org.morozko.java.mod.web.upload.facade;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.web.servlet.config.BasicConfig;

public class UploadConfig extends BasicConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6796187083250087275L;

	public void configure(Properties props) throws ConfigException {
		try {
			this.getLog().info( "config start" );
			String savePath = props.getProperty( "save-path" );
			this.getLog().info( "savePath : "+savePath );
			String prefix = props.getProperty( "prefix" );
			this.getLog().info( "prefix : "+savePath );
			String dsName = props.getProperty( "db-mode-ds-name" );
			this.getLog().info( "dsName : "+savePath );
			UploadFacade facade = new UploadFacade( savePath, prefix, ConnectionFactoryImpl.newInstance( dsName ) );
			this.getConfigContext().getContext().setAttribute( UploadFacade.ATT_NAME , facade );
			this.getLog().info( "config end" );
		} catch (Exception e) {
			this.getLog().error( "Error : "+e, e );
		}
	}

	
	
}
