package org.morozko.java.mod.dbsrc.config.generic;

import org.morozko.java.mod.dbsrc.data.BasicRecordIterator;
import org.morozko.java.mod.dbsrc.data.ColumnDescriptor;
import org.morozko.java.mod.dbsrc.data.Record;

public class EmptyRecordIterator extends BasicRecordIterator {

	private final static ColumnDescriptor[] EMPTY = new ColumnDescriptor[0];
	
	public EmptyRecordIterator() {
		super( EMPTY );
	}
	
	public boolean end() {
		return true;
	}

	public boolean hasNext() {
		return false;
	}

	public Record next() {
		return null;
	}

	public boolean start() {
		return true;
	}

}
