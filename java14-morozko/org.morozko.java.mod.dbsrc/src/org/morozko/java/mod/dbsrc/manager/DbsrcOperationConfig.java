package org.morozko.java.mod.dbsrc.manager;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.mod.dbsrc.config.op.DbsrcOperation;
import org.morozko.java.mod.ws.manager.config.InfoResult;
import org.morozko.java.mod.ws.manager.config.OperationConfig;
import org.morozko.java.mod.ws.manager.config.OperationResult;

public class DbsrcOperationConfig extends OperationConfig {

	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5123017696336834898L;

	@Override
	public OperationResult apply(ParamMap paramMap) {
		OperationResult result = null;
		DbsrcModuleConfig module = (DbsrcModuleConfig)this.getModule();
		try {
			int code = module.getDbsrc().exec( this.getName() );
			if ( code == DbsrcOperation.EXIT_CODE_OK ) {
				result = OperationResult.newResultOk();
			} else {
				result = OperationResult.newResultKo();
			}
		} catch (Exception e) {
			result = OperationResult.newResultKo();
			this.getLog().error( e );
		}
		result.getInfoList().add( new InfoResult( "operation-name" , this.getName() ) );
		result.getInfoList().add( new InfoResult( "operation-description" , this.getDescription() ) );
		return result;
	}

}
