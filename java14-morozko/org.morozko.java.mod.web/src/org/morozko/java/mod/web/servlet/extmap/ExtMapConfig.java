package org.morozko.java.mod.web.servlet.extmap;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.web.servlet.config.BasicConfig;
import org.w3c.dom.Element;

public class ExtMapConfig extends BasicConfig {

	

	public void configure(Properties props) throws ConfigException {
		throw new ConfigException( "Properties configuration not supported" );
	}

	public void configure(Element tag) throws ConfigException {
		this.getLog().info( "configure() START" );
		try {
			ExtMapProvider provider = new ExtMapProvider();
			provider.configure( tag );
			this.getConfigContext().getContext().setAttribute( ExtMapProvider.ATT_NAME , provider );
		} catch (Exception e) {
			this.getLog().error( "configure() ERROR "+e, e );
		}
		this.getLog().info( "configure() END" );
	}

}
