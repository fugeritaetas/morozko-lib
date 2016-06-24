package org.morozko.java.core;

import org.morozko.java.core.cfg.BaseModuleVersion;

public class CoreModuleVersion extends BaseModuleVersion {

	public static final String NAME = "Module Core";
	public static final String VERSION = "1.0.5";
	public static final String DATE = "2011-10-11";
	public static final String DEPENDANCIES = "/org/morozko/java/core/dependancies.properties";
	
	public CoreModuleVersion() {
		super( NAME, VERSION, DATE, DEPENDANCIES );
	}
	
}
