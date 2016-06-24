package org.morozko.java.mod.dbsrc.data;

import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public class BasicRecordHandler implements RecordHandler {

	private ConfigTag config;
	
	public BasicRecordHandler(ConfigTag config) {
		this.config = config;
	}

	public int release() throws DbsrcException {
		return REL_NORECORDCOUNT;
	}

	public void setRecord(Record record) throws DbsrcException {

	}

	public ConfigTag getConfig() {
		return config;
	}

}
