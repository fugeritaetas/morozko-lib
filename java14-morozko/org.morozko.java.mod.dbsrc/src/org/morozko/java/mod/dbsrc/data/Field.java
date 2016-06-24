package org.morozko.java.mod.dbsrc.data;

import java.util.Date;

import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public class Field {

	public final static int TYPE_STRING = 100;
	public final static int TYPE_LONG = 101;
	public final static int TYPE_DOUBLE = 102;
	public final static int TYPE_DATE = 103;
	
	private Object data;

	private int type;
	
	public static int parseType( String type ) {
		int result = 0;
		if ( "date".equalsIgnoreCase( type ) ) {
			result = TYPE_DATE;
		} else if ( "string".equalsIgnoreCase( type ) ) {
			result = TYPE_STRING;
		} else if ( "long".equalsIgnoreCase( type ) ) {
			result = TYPE_LONG;
		} else if ( "double".equalsIgnoreCase( type ) ) {
			result = TYPE_DOUBLE;
		}
		return result;
	}
	
	private Field(Object data, int type) {
		super();
		this.data = data;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public Object getData() {
		return data;
	}

	public boolean isLongField() {
		return this.getType()==TYPE_LONG;
	}
	
	public boolean isStringField() {
		return this.getType()==TYPE_STRING;
	}	
	
	public boolean isDoubleField() {
		return this.getType()==TYPE_DOUBLE;
	}
	
	public boolean isDateField() {
		return this.getType()==TYPE_DATE;
	}
	
	public static Field newDoubleField( Double data ) {
		return new Field( data, TYPE_DOUBLE );
	}
	
	public static Field newDateField( Date data ) {
		return new Field( data, TYPE_DATE );
	}
	
	public static Field newStringField( String data ) {
		return new Field( data, TYPE_STRING );
	}
	
	public static Field newLongField( Long data ) {
		return new Field( data, TYPE_LONG );
	}
	
	public static Field newField( Object data, int type ) throws DbsrcException {
		return new Field( data, type );
	}
	
	public String toString() {
		return this.getClass().getName()+"[type:"+this.getType()+",data:"+this.getData()+"]";
	}
	
}
