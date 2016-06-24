package org.morozko.java.mod.ws.manager.context;

import java.util.Iterator;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.mod.ws.manager.config.ContextConfig;
import org.morozko.java.mod.ws.manager.config.InfoResult;
import org.morozko.java.mod.ws.manager.config.ManagerConfig;
import org.morozko.java.mod.ws.manager.config.ModuleConfig;
import org.morozko.java.mod.ws.manager.config.OperationConfig;
import org.morozko.java.mod.ws.manager.config.OperationResult;

public class ListOperation extends OperationConfig {

	public static final String PARAM_CONTEXT = "context";
	
	public static final String PARAM_MODULE = "module";
	
	@Override
	public OperationResult apply(ParamMap paramMap) {
		OperationResult result = null;
		String context = paramMap.getParam( PARAM_CONTEXT );
		String module = paramMap.getParam( PARAM_MODULE);
		ManagerConfig managerConfig =  this.getModule().getContext().getManager();
		if ( context != null ) {
			ContextConfig contextConfig = managerConfig.getConfigMap().get( context );
			if ( contextConfig == null ) {
				result = OperationResult.newResultKo();
			} else {
				if ( module != null ) {
					ModuleConfig moduleConfig = contextConfig.getModuleMap().get( module );
					if ( moduleConfig == null ) {
						result = OperationResult.newResultKo();
					} else {
						result = OperationResult.newResultOk();
						Iterator<String> itOperation = moduleConfig.getOperationMap().keySet().iterator();
						int countOp = 0;
						while ( itOperation.hasNext() ) {
							countOp++;
							String current = itOperation.next();
							result.getInfoList().add( new InfoResult( "operation-"+countOp , current ) );
						}
					}
				} else {
					result = OperationResult.newResultOk();
					Iterator<String> itModule = contextConfig.getModuleMap().keySet().iterator();
					int countMod = 0;
					while ( itModule.hasNext() ) {
						countMod++;
						String current = itModule.next();
						result.getInfoList().add( new InfoResult( "module-"+countMod , current ) );
					}
				}
			}			
		}  else {
			result = OperationResult.newResultOk();
			Iterator<String> itContext = managerConfig.getConfigMap().keySet().iterator();
			int countCon = 0;
			while ( itContext.hasNext() ) {
				countCon++;
				String current = itContext.next();
				result.getInfoList().add( new InfoResult( "context-"+countCon , current ) );
			}
		}
		return result;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4880188060519860593L;

}
