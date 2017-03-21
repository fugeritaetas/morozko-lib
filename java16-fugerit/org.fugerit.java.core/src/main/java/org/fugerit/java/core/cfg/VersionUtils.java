package org.fugerit.java.core.cfg;

import java.util.Properties;

import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.log.LogFacade;

public class VersionUtils {

	public static Properties MODULES = new Properties();
	static {
		try {
			MODULES.load( VersionUtils.class.getResourceAsStream( "/org/fugerit/java/core/cfg/module.properties" ) );
		} catch (Throwable e) {
			LogFacade.handleError( e );
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
			System.out.println( "TEST 1" ); 
			Properties moduleList = getModuleList();
			for ( String key : moduleList.stringPropertyNames() ) {
				System.out.println( key+" -> "+getVersionString( key ) );
			}
		} catch (Exception e)  {
			e.printStackTrace();
		}
	}
	
}
