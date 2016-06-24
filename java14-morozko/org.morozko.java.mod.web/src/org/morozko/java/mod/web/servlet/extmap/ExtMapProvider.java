package org.morozko.java.mod.web.servlet.extmap;

import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.XMLConfigurableObject;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

public class ExtMapProvider extends XMLConfigurableObject {

	public static final String ATT_NAME = "JspMapProvider";
	
	private Properties extMapping;
	
	private String mapPath;
	
	private String baseUrl;
	
	public void configure(Element tag) throws ConfigException {
		this.baseUrl = tag.getAttribute( "base-url" );
		this.mapPath = tag.getAttribute( "map-path" );
		Properties props = new Properties();
		SearchDOM search = SearchDOM.newInstance( true , true );
		Iterator itMap = (Iterator)search.findAllTags( tag , "map" ).iterator();
		while ( itMap.hasNext() ) {
			Element map = (Element)itMap.next();
			String fromExt = map.getAttribute( "from-ext" );
			String toExt = map.getAttribute( "to-ext" );
			props.setProperty( fromExt , toExt );
		}
		this.extMapping = props;
	}

	public String map( String url ) {
		String res = url;
		int indexExt = url.lastIndexOf( "." );
		if ( indexExt != -1 ) {
			String extFrom = url.substring( indexExt+1 );
			String extTo = this.extMapping.getProperty( extFrom );
			res = url.substring( 0, indexExt+1 )+extTo;
		}
		res = res.replaceAll( this.baseUrl , this.mapPath );
		return res;
	}
	
}
