package org.morozko.java.mod.dbsrc.config.ds.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicColumnDescriptor;
import org.morozko.java.mod.dbsrc.data.Field;

public class CSVColumn extends BasicColumnDescriptor {
	
	private String format;

	private SimpleDateFormat sdf;
	
	public CSVColumn(String name, int type, String format) {
		super( name, type );
		this.format = format;
		if ( type == Field.TYPE_DATE ) {
			this.sdf = new SimpleDateFormat( format );
		}
	}

	public String getFormat() {
		return format;
	}
	
	public Field getField( String value ) throws DbsrcException {
		Field f = null;
		if ( this.getType() == Field.TYPE_STRING ) {
			f = Field.newStringField( value );
		} else if ( this.getType() == Field.TYPE_LONG ) {
			value = value.trim().replaceAll( "," , "" );
			if ( this.getFormat() != null ) {
				String[] f1 = this.getFormat().split( "=" );
				value = value.replaceAll( f1[0] ,f1[1] );
			}
			if ( value.trim().length() > 0 ) {
				f = Field.newLongField( Long.parseLong( value ) );	
			} else {
				f = Field.newLongField( null );
			}
		} else if ( this.getType() == Field.TYPE_DOUBLE ) {
			if ( value.trim().length() > 0 ) {
				value = value.trim().replaceAll( "," , "." );
				f = Field.newDoubleField( Double.parseDouble( value ) );	
			} else {
				f = Field.newDoubleField( null );
			}
		} else if ( this.getType() == Field.TYPE_DATE ) {
			try {
				if ( value.trim().length() > 0 ) {
					f = Field.newDateField( this.sdf.parse( value ) );	
				} else {
					f = Field.newDateField( null );	
				}
			} catch (ParseException e) {
				throw ( new DbsrcException( e ) );
			}
		}
		return f;
	}
	
	public static void main( String[] args ) {
		String test = "*";
		test = test.replaceAll( "\\*" , "0" );
		System.out.println(  "test: "+test );
	}
	
}
