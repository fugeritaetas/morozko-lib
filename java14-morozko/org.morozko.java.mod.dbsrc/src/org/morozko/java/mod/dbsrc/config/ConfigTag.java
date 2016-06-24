package org.morozko.java.mod.dbsrc.config;

import java.util.Properties;

public class ConfigTag {

	public static ConfigTag newConfigTag( String name, Properties props, ConfigMap configMap ) {
		return new ConfigTag( props, configMap, name );
	}
	
	private ConfigTag(Properties props, ConfigMap configMap, String name) {
		super();
		this.name = name;
		this.props = props;
		this.configMap = configMap;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	private Properties props;
	
	private ConfigMap configMap;

	public Properties getProps() {
		return props;
	}

	public ConfigMap getConfigMap() {
		return configMap;
	}

	
}
