package org.morozko.java.mod.dbsrc.config;

import java.util.Properties;

import org.morozko.java.mod.dbsrc.config.xml.GeneralProperty;
import org.morozko.java.mod.dbsrc.config.xml.Property;

public class ConfigUtils {

	public static Properties getProperties( Property[] properties ) {
		Properties props = new Properties();
		for ( int k=0; k<properties.length; k++ ) {
			String name = properties[k].getKey();
			String value = properties[k].getContent();
			props.setProperty( name , value );
		}
		return props;
	}
	
	public static Properties getProperties( GeneralProperty[] properties ) {
		Properties props = new Properties();
		for ( int k=0; k<properties.length; k++ ) {
			String name = properties[k].getKey();
			String value = properties[k].getContent();
			props.setProperty( name , value );
		}
		return props;
	}
	
}
