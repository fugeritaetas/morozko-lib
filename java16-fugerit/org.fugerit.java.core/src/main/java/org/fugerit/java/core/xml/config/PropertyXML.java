package org.fugerit.java.core.xml.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

public class PropertyXML {

	private Properties props;
	
	private List list;
	
	public PropertyXML() {
		this.list = new ArrayList();
		this.props = new Properties();
	}
	
	public void add( String name, String value ) {
		this.props.setProperty(name, value);
		this.list.add( new Property( name, value ) );
	}
	
	public Properties getProperties() {
		return this.props;
	}
	
	public List getList() {
		return this.list;
	}
	
	public String get( String name ) {
		return this.getProperties().getProperty( name );
	}
	
	public static PropertyXML parse( Element propXml ) {
		PropertyXML xml = new PropertyXML();
		SearchDOM searchDOM = SearchDOM.newInstance( true , true );
		List propList = searchDOM.findAllTags( propXml , "property" );
		for ( int k=0; k<propList.size(); k++ ) {
			Element current = (Element) propList.get( k );
			String name = current.getAttribute( "name" );
			String value = current.getAttribute( "value" );
			xml.add( name , value );
		}
		return xml;
	}
	
}
 