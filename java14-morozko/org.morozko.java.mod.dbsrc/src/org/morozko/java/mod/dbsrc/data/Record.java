package org.morozko.java.mod.dbsrc.data;


public class Record {

	@Override
	public String toString() {
		return this.getClass().getName()+"[columns:"+this.columns.length+";fields:"+this.fieldList.length+"]";
	}

	private Field[] fieldList;

	private ColumnDescriptor[] columns;
	
	private Record(Field[] fieldList, ColumnDescriptor[] columns) {
		super();
		this.fieldList = fieldList;
		this.columns = columns;
	}
	
	public static Record newRecord( Field[] fieldList, ColumnDescriptor[] columns ) {
		return new Record( fieldList, columns );
	}
	
	public ColumnDescriptor[] getColumns() {
		return columns;
	}

	public int length() {
		return this.fieldList.length;
	}
	
	public Field getField( int k ) {
		return this.fieldList[k];
	}
	
}
