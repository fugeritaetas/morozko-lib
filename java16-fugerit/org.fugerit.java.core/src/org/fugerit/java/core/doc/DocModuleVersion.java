package org.fugerit.java.core.doc;

import org.fugerit.java.core.cfg.BaseModuleVersion;

public class DocModuleVersion extends BaseModuleVersion {

	public static final String NAME = "Module Doc";
	public static final String VERSION = "1.4.0";
	public static final String DATE = "2016-06-24";
	public static final String DEPENDANCIES = "/org/morozko/java/mod/doc/dependancies.properties";
	
	public DocModuleVersion() {
		super( NAME, VERSION, DATE, DEPENDANCIES );
	}

}