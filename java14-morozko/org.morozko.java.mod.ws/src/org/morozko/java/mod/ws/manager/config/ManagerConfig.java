package org.morozko.java.mod.ws.manager.config;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

public class ManagerConfig implements Serializable {

	private File baseDir;
	
	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public static final SearchDOM XML_SEARCH = SearchDOM.newInstance( true , true );
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6273090794852739928L;

	public ManagerConfig() {
		this.configMap = new HashMap<String, ContextConfig>();
	}
	
	private Map<String, ContextConfig> configMap;

	public Map<String, ContextConfig> getConfigMap() {
		return configMap;
	}
	
	public OperationResult applyOperation( String context, String module, String operation, ParamMap paramMap ) {
		OperationResult result = null;
		ContextConfig contextConfig = this.getConfigMap().get( context );
		if ( contextConfig == null ) {
			result = new OperationResult( "91", "Context does not exist : "+context );
		} else {
			ModuleConfig moduleConfig = contextConfig.getModuleMap().get( module );
			if ( moduleConfig == null ) {
				result = new OperationResult( "92", "Module does not exist : "+module );
			} else {
				OperationConfig operationConfig = moduleConfig.getOperationMap().get( operation );
				if ( operationConfig == null ) {
					result = new OperationResult( "93", "Operation does not exist : "+operation );
				} else {
					result = operationConfig.apply( paramMap );
				}
			}
		}
		return result;
		
	}
	
	public OperationConfig findOperation( String context, String module, String operation ) {
		return this.getConfigMap().get( context ).getModuleMap().get( module ).getOperationMap().get( operation );
	}

	public void readConfig( Element config ) throws Exception {
		Element contextListTag = XML_SEARCH.findTag( config , "context-list" );
		List<Element> listContextTag = XML_SEARCH.findAllTags( contextListTag , "context" );
		Iterator<Element> itContextTag = listContextTag.iterator();
		while ( itContextTag.hasNext() ) {
			Element contextTag = itContextTag.next();
			Properties contextAtt = DOMUtils.attributesToProperties( contextTag );
			String contextName = contextAtt.getProperty( "name" );
			String contextType = contextAtt.getProperty( "type", "org.morozko.java.mod.ws.manager.config.ContextConfig" );
			ContextConfig context = (ContextConfig)ClassHelper.newInstance( contextType );
			context.setName( contextName );
			context.setManager( this );
			context.configure( contextTag ); 
			this.getConfigMap().put( contextName , context );
			List<Element> listModuleTag = XML_SEARCH.findAllTags( contextTag , "module" );
			Iterator<Element> itModuleTag = listModuleTag.iterator();
			while ( itModuleTag.hasNext() ) {
				Element moduleTag = itModuleTag.next();
				Properties moduleAtt = DOMUtils.attributesToProperties( moduleTag );
				String moduleName = moduleAtt.getProperty( "name" );
				String moduleType = moduleAtt.getProperty( "type", "org.morozko.java.mod.ws.manager.config.ModuleConfig" );
				ModuleConfig module = (ModuleConfig)ClassHelper.newInstance( moduleType );
				module.setName( moduleName );
				module.setContext( context );
				module.configure( moduleTag );
				context.getModuleMap().put( moduleName , module );
				List<Element> listOperationtag = XML_SEARCH.findAllTags( moduleTag , "operation" );
				Iterator<Element> itOperationTag = listOperationtag.iterator();
				while ( itOperationTag.hasNext() ) {
					Element operationTag = itOperationTag.next();
					Properties operationAtt = DOMUtils.attributesToProperties( operationTag );
					String operationName = operationAtt.getProperty( "name" );
					String operationType = operationAtt.getProperty( "type", "org.morozko.java.mod.ws.manager.config.OperationConfig" );
					OperationConfig operation = (OperationConfig)ClassHelper.newInstance( operationType );
					operation.setModule( module );
					operation.setName( operationName );
					operation.configure( operationTag );
					module.getOperationMap().put( operationName , operation );
				}
			}
		}
	}
	
}
