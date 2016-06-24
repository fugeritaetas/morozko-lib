package org.morozko.java.mod.db;

import org.morozko.java.core.cfg.BaseModuleVersion;

public class DbModuleVersion extends BaseModuleVersion {

	public static final String NAME = "Module Db";
	public static final String VERSION = "1.0.4";
	public static final String DATE = "2014-05-13";
	public static final String DEPENDANCIES = "/org/morozko/java/mod/db/dependancies.properties";
	
	public DbModuleVersion() {
		super( NAME, VERSION, DATE, DEPENDANCIES );
	}
	
}
