package org.fugerit.java.core.db;

import org.fugerit.java.core.cfg.BaseModuleVersion;

public class DbModuleVersion extends BaseModuleVersion {

	public static final String NAME = "Module Db";
	public static final String VERSION = "1.0.5";
	public static final String DATE = "2016-07-06";
	public static final String DEPENDANCIES = "/org/morozko/java/mod/db/dependancies.properties";
	
	public DbModuleVersion() {
		super( NAME, VERSION, DATE, DEPENDANCIES );
	}
	
}
