package org.morozko.java.core.cfg;

import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.LogFacade;

public class VersionUtils {

	public static Properties MODULES = new Properties();
	static {
		try {
			MODULES.load( VersionUtils.class.getResourceAsStream( "/org/morozko/java/core/cfg/module.properties" ) );
		} catch (Throwable e) {
			LogFacade.getLog().error( e );
		}
	}
	
	public synchronized static void registerModule( String name, String className ) {
		MODULES.setProperty( name , className );
	}
	
	public synchronized static Properties getModuleList() {
		return MODULES;
	}
	
	public static String getVersionString( String moduleName ) {
		String versionString = null;
		String type = getModuleList().getProperty( moduleName );
		if ( type != null ) {
			try {
				Object o = ClassHelper.newInstance( type );
				try {
					ModuleVersion vc = (ModuleVersion) o;
					versionString = vc.getName()+" "+vc.getVersion()+" "+vc.getDate();
				} catch ( Throwable t2 ) {
					versionString = "[03] Impossible to find module version";
				}
			} catch (Exception t1) {
				versionString = "[02] Class module isn't loaded : ("+type+") - "+t1;
				t1.printStackTrace();
			}	
		} else {
			versionString = "[01] Module does not exist";
		}
		return versionString;
	}

	public static void main( String[] args ) {
		try {
			Properties moduleList = getModuleList();
			Iterator moduleIt = moduleList.keySet().iterator();
			while ( moduleIt.hasNext() ) {
				String moduleName = (String) moduleIt.next();
				System.out.println( moduleName+" -> "+getVersionString( moduleName ) );
			}
		} catch (Exception e)  {
			e.printStackTrace();
		}
	}
	
}
