package org.morozko.java.mod.text.bbcode;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.web.servlet.config.BasicConfig;

public class BBCodeConfig extends BasicConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1199850818638237007L;

	@Override
	public void configure(Properties props) throws ConfigException {
		try {
			this.getLog().info( "config start" );
			BBCodeFactory factory = new BBCodeFactory();
			Iterator<Object> keys = props.keySet().iterator();
			while ( keys.hasNext() ) {
				String key = (String) keys.next();
				if ( key.indexOf( "config-" ) == 0 ) {
					String filePath = props.getProperty( key );
					File file = new File( filePath );
					if ( !file.exists() ) {
						file = new File( this.getConfigContext().getContext().getRealPath( "/" ), filePath );
					}
					factory.registerConfigFile( key , file.getAbsolutePath() );	
				}
			}
			this.getConfigContext().getContext().setAttribute( BBCodeFactory.ATT_NAME , factory );
			this.getLog().info( "config end" );
		} catch (Exception e) {
			this.getLog().error( "config error : "+e, e );
		}
	}

}
