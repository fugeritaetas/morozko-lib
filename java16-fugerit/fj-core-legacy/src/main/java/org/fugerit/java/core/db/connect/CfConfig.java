package org.fugerit.java.core.db.connect;

import java.util.HashMap;
import java.util.Map;

public class CfConfig {

	public static final CfConfig EMPTY_CONFIG = new CfConfig();
	
	public CfConfig() {
		this.cfMap = new HashMap<String, ConnectionFactory>();
	}
	
	private Map<String, ConnectionFactory> cfMap;

	public Map<String, ConnectionFactory> getCfMap() {
		return cfMap;
	}
	
}
