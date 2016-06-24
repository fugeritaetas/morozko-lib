package org.morozko.java.mod.db.connect;

import java.util.HashMap;
import java.util.Map;

public class CfConfig {

	public static final CfConfig EMPTY_CONFIG = new CfConfig();
	
	public CfConfig() {
		this.cfMap = new HashMap();
	}
	
	private Map cfMap;

	public Map getCfMap() {
		return cfMap;
	}
	
}
