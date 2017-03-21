package org.fugerit.java.core.cfg;

import java.util.Properties;

public class CoreModuleVersion extends BaseModuleVersion {

	public static final String MODULE_NAME = "Fugerit Java Core";
	public static final String MODULE_VERSION = "1.0.0";
	public static final String MODULE_DATE = "2017-01-02";
	public static final Properties DEPENDANCIES = new Properties();
	
	public CoreModuleVersion() {
		super(MODULE_NAME, MODULE_VERSION, MODULE_DATE, DEPENDANCIES);
	}

}
