package org.morozko.java.mod.dbsrc.config;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.dbsrc.config.xml.DbsrcConfig;
import org.morozko.java.mod.dbsrc.config.xml.DbsrcConfigType;

public class ConfigurationData {

	private static ConfigurationData instance = new ConfigurationData();
	
	public static ConfigurationData getInstance() {
		return instance;
	}
	
	public static Dbsrc parseConfiguration( Reader reader ) throws ConfigException {
		Dbsrc result = null;
		try {
			LogFacade.getLog().debug( "ConfigurationData.parseConfiguration start" );
			LogFacade.getLog().debug( "ConfigurationData.parseConfiguration test 0" );
			DbsrcConfigType dbsrcConfig = DbsrcConfig.unmarshal( reader );
			String name = dbsrcConfig.getGeneralProps().getName();
			Map<String, Dbsrc> map = ConfigurationData.getInstance().map;
			if ( map.containsKey( name ) ) {
				LogFacade.getLog().debug( "ConfigurationData.parseConfiguration test 1" );
				throw (new ConfigException( "Config name : "+name+" already exists", 10 ));
			} else {
				LogFacade.getLog().debug( "ConfigurationData.parseConfiguration test 2" );
				result = Dbsrc.buildConfig( dbsrcConfig );
				map.put( name , result );
			}
			LogFacade.getLog().debug( "ConfigurationData.parseConfiguration end" );
		} catch (MarshalException e) {
			throw new ConfigException( e, 1 );
		} catch (ValidationException e) {
			throw new ConfigException( e, 2 );
		} catch (Throwable e) {
			throw new ConfigException( e, 3 );
		}
		return result;
	}
	
	private ConfigurationData() {
		this.map = new HashMap<String, Dbsrc>();
	}
	
	private HashMap<String, Dbsrc> map;
	
	public void removeConfig( String configName ) {
		this.map.remove( configName );
	}
	
	public Dbsrc getConfig( String configName ) {
		return (Dbsrc)this.map.get( configName );
	}
	
}
