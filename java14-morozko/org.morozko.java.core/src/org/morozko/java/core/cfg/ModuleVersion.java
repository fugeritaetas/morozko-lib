package org.morozko.java.core.cfg;

import java.util.Properties;

public interface ModuleVersion {

	public String getName();
	
	public String getVersion();
	
	public String getDate();
	
	public Properties getDependancies();
	
	public String getLoadTime();
	
}
