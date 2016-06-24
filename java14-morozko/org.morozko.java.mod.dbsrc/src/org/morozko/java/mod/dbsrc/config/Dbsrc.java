package org.morozko.java.mod.dbsrc.config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.log.StreamAppender;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.config.log.DbsrcLogger;
import org.morozko.java.mod.dbsrc.config.op.DbsrcOperation;
import org.morozko.java.mod.dbsrc.config.xml.ConfigurationDs;
import org.morozko.java.mod.dbsrc.config.xml.DataOperation;
import org.morozko.java.mod.dbsrc.config.xml.DataOperationList;
import org.morozko.java.mod.dbsrc.config.xml.DataSource;
import org.morozko.java.mod.dbsrc.config.xml.DataSourceList;
import org.morozko.java.mod.dbsrc.config.xml.DbsrcConfigType;

public class Dbsrc extends BasicLogObject {
	
	private Properties generalProperties;
	
	public Properties getGeneralProperties() {
		return generalProperties;
	}

	private DbsrcLogger errorLogger;
	
	public DbsrcLogger getErrorLogger() {
		return errorLogger;
	}
	
	public DbsrcOperation getOperation( String key ) {
		return this.dataOperationMap.get( key );
	}
	
	public Iterator<String> getOperationNames() {
		return this.dataOperationMap.keySet().iterator();
	}
	
	public Collection<DbsrcOperation> getOperationList() {
		return this.dataOperationMap.values();
	}
	
	public DbsrcDataSource getDataSource(String key) {
		return dataSourceMap.get(key);
	}

	public Iterator<String> getDataSourceNames() {
		return dataSourceMap.keySet().iterator();
	}

	public Collection<DbsrcDataSource> getDataSourceList() {
		return dataSourceMap.values();
	}

	public static Dbsrc buildConfig( DbsrcConfigType dbsrcConfig ) throws Exception {
		Dbsrc config = new Dbsrc();
		// general properties
		Properties generalProperties = ConfigUtils.getProperties( dbsrcConfig.getGeneralProps().getGeneralProperty() );
		config.generalProperties = generalProperties;
		String errorLoggerFile = generalProperties.getProperty( "error-logger-file" );
		if ( errorLoggerFile != null ) {
			File file = new File( errorLoggerFile );
			if ( file.exists() ) {
				file.renameTo( new File( file.getParent(), System.currentTimeMillis()+"_"+file.getName()  ) );
			}
			config.errorLogger = new DbsrcLogger( new StreamAppender( new FileOutputStream( file, false ) ) );
		} else {
			config.errorLogger = new DbsrcLogger( StreamAppender.out );
		}
		LogFacade.getLog().info( "error-logger : "+config.errorLogger );
		// data source
		DataSourceList dataSourceList = dbsrcConfig.getDataSourceList();
		for (int k=0; k<dataSourceList.getDataSourceCount(); k++ ) {
			DataSource currentDS = dataSourceList.getDataSource()[k];
			String dsname =  currentDS.getName();
			String dstype =  currentDS.getType();
			ConfigurationDs dataSourceConfiguration = currentDS.getConfigurationDs();
			DbsrcDataSource dataSource = (DbsrcDataSource)ClassHelper.newInstance( dstype );
			dataSource.setDbsrc( config );
			dataSource.configure( 
					ConfigMap.parse( dataSourceConfiguration.getName(),
							dataSourceConfiguration.getConfiguration(), 
							ConfigUtils.getProperties( dataSourceConfiguration.getProperty() ) ) ) ;
			config.dataSourceMap.put( dsname , dataSource );
		}
		DataOperationList dataOperationList = dbsrcConfig.getDataOperationList();
		Map<String, String> dependance = new HashMap<String, String>();
		for ( int k=0; k<dataOperationList.getDataOperationCount(); k++ ) {
			DataOperation currentOp = dataOperationList.getDataOperation( k );
			String name = currentOp.getName();
			String type = currentOp.getType();
			String from = currentOp.getFromDs();
			String to = currentOp.getToDs();
			String depends = currentOp.getDepends();
			DbsrcOperation dbsrcOperation = (DbsrcOperation)ClassHelper.newInstance( type );
			dbsrcOperation.setDbsrc( config );
			DbsrcDataSource fromDS = config.getDataSource( from );
			DbsrcDataSource toDS = config.getDataSource( to );
			Properties baseProps = ConfigUtils.getProperties( currentOp.getConfigurationOp().getProperty() );
			dbsrcOperation.configure( name, fromDS, toDS, 
						ConfigMap.parse( currentOp.getConfigurationOp().getName(),
								currentOp.getConfigurationOp().getConfiguration() , baseProps ) );
			config.dataOperationMap.put( name , dbsrcOperation );
			if ( depends != null ) {
				dependance.put( name , depends );
			}
		}
		Iterator<String> itDependance = dependance.keySet().iterator();
		while ( itDependance.hasNext() ) {
			String opName = itDependance.next();
			String depends = dependance.get( opName );
			DbsrcOperation[] depOp = new DbsrcOperation[1];
			depOp[0] = config.getOperation( depends );
			config.dataOperationDependanceMap.put( opName, depOp );
		}
		return config;
	}
	
	private Map<String, DbsrcDataSource> dataSourceMap;
	
	private Map<String, DbsrcOperation> dataOperationMap;
	
	private Map<String, DbsrcOperation[]> dataOperationDependanceMap;
	
	private Dbsrc() {
		this.dataSourceMap = new HashMap<String, DbsrcDataSource>();
		this.dataOperationMap = new HashMap<String, DbsrcOperation>();
		this.dataOperationDependanceMap = new HashMap<String, DbsrcOperation[]>();
	}
	
	public int exec( String opName ) throws DbsrcException {
		int result = DbsrcOperation.EXIT_CODE_OK;
		DbsrcOperation operation = this.getOperation( opName );
		if ( operation == null ) {
			throw ( new DbsrcException( "null operation : "+opName ) );
		} else {
			DbsrcOperation[] depOp = this.dataOperationDependanceMap.get( opName );
			if ( depOp != null ) {
				for ( int k=0; k<depOp.length && result != DbsrcOperation.EXIT_CODE_KO; k++ ) {
					result = this.exec( depOp[k].getName() );
				}
			}
			result = operation.apply();
		}
		String appendMessage = "";
		if ( result == DbsrcOperation.EXIT_CODE_OK ) {
			appendMessage = " [ Completed without errors ] ";
		} else if ( result == DbsrcOperation.EXIT_CODE_KO ) {
			appendMessage = " [ Fatal Error(s) ] ";
		} else {
			appendMessage = " [ Completed with "+result+" errors ] ";
		}
		this.getLog().info( "op.apply() : "+opName+" : "+result+appendMessage+" ["+operation.getDescription()+"]" );
		return result;
	}
	
}
