package org.morozko.java.core;

import org.morozko.java.core.cfg.BaseModuleVersion;

public class CoreModuleVersion extends BaseModuleVersion {

	public static final String NAME = "Module Core";
	public static final String VERSION = "1.1.0";
	public static final String DATE = "2016-06-24";
	public static final String DEPENDANCIES = "/org/morozko/java/core/dependancies.properties";
	
	public CoreModuleVersion() {
		super( NAME, VERSION, DATE, DEPENDANCIES );
	}
	
}
