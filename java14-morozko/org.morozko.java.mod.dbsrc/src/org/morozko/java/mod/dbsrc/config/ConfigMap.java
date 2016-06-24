package org.morozko.java.mod.dbsrc.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.mod.dbsrc.config.xml.Configuration;

public class ConfigMap {

	private Map<String, ConfigTag> configMap;
	
	public static ConfigTag parse( String name, Configuration[] list, Properties props ) {
		ConfigMap map = new ConfigMap();
		for ( int k=0; k<list.length; k++ ) {
			Configuration current = list[k];
			Properties currentProps = ConfigUtils.getProperties( current.getProperty() );
			ConfigTag currentTag = parse( current.getName(), current.getConfiguration(), currentProps );
			map.configMap.put( current.getName() , currentTag );
		}
		return ConfigTag.newConfigTag( name, props, map );
	}
	
	private ConfigMap() {
		this.configMap = new HashMap<String, ConfigTag>();
	}
	
	public ConfigTag getConfig( String name ) {
		return this.configMap.get( name );
	}

	public Iterator<ConfigTag> getConfigs() {
		return this.configMap.values().iterator();
	}
	
	public String toString() {
		return this.configMap.toString();
	}
	
}

