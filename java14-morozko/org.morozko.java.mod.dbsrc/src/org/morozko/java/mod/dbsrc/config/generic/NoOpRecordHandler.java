package org.morozko.java.mod.dbsrc.config.generic;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Record;

public class NoOpRecordHandler extends BasicRecordHandler {

	private int counter;
	
	public NoOpRecordHandler(ConfigTag config) {
		super(config);
		this.counter = 0;
	}

	@Override
	public int release() throws DbsrcException {
		LogFacade.getLog().info( "NoOpRecordHandler.release()" );
		return this.counter;
	}

	@Override
	public void setRecord(Record record) throws DbsrcException {
		this.counter++;
		LogFacade.getLog().info( "NoOpRecordHandler.setRecord() : "+this.counter );
		super.setRecord(record);
		
	}

}
