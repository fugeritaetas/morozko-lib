package org.morozko.java.mod.dbsrc.data;

import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public abstract class BasicRecordIterator implements RecordIterator {

	private ColumnDescriptor[] columns;
	
	protected BasicRecordIterator( ColumnDescriptor[] columns ) {
		this.columns = columns;
	}
	
	public ColumnDescriptor[] describe()  throws DbsrcException {
		return this.columns;
	}

}
