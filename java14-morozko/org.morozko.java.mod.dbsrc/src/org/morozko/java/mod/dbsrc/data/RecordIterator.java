package org.morozko.java.mod.dbsrc.data;

import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;


public interface RecordIterator {

	public boolean start()  throws DbsrcException;
	
	public boolean hasNext()  throws DbsrcException;
	
	public Record next()  throws DbsrcException;
	
	public boolean end() throws DbsrcException;
	
	public ColumnDescriptor[] describe() throws DbsrcException;
	
}
