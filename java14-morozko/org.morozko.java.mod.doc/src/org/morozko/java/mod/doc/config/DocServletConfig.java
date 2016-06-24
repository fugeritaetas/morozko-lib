package org.morozko.java.mod.doc.config;

import java.io.File;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.mod.doc.filter.DocRequestFacade;
import org.morozko.java.mod.web.servlet.config.BasicConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DocServletConfig extends BasicConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5671884706527956909L;
	
	public static final String ATT_NAME_DOCFACADE = "DocServletConfig.ATT_NAME_DOCFACADE";
	
	public void configure(Properties props) throws ConfigException {
		try {
			this.getLog().info( "Doc Module Config : VERSION : "+DocConfig.VERSION );
			String configPath = props.getProperty( "config" );
			File configFile = this.getConfigContext().resolvePath( configPath );
			Document doc = DOMIO.loadDOMDoc( configFile );
			Element root = doc.getDocumentElement();
			DocRequestFacade configFacade = new DocRequestFacade();
			configFacade.configure( root , this.getConfigContext() );
			this.getConfigContext().setAttribute( ATT_NAME_DOCFACADE , configFacade );
		} catch (Throwable t) {
			this.getLog().error( t );
		}	
	}

}
