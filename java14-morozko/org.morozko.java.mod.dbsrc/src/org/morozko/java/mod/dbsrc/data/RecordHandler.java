package org.morozko.java.mod.dbsrc.data;

import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public interface RecordHandler {

	public static final int REL_NORECORDCOUNT = -1; 
	
	public void setRecord(Record record) throws DbsrcException;
	
	public int release() throws DbsrcException;
	
}
