package org.morozko.java.mod.db.backup.adaptor;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.morozko.java.core.xml.dom.SearchDOM;
import org.w3c.dom.Element;

public class MaskBackupAdaptor extends BackupAdaptorWrapper {

	private final static String MASK1 = "AEIOU";
	private final static String MASK2 = "BCDFGHJKLMNPQRSTVXYWZ";
	private final static String MASK3 = "0123456789";
	
	private static Character[] toChar( char[] f ) {
		Character[] t = new Character[f.length];
		for ( int k=0; k<f.length; k++ ) {
			t[k] = new Character( f[k] );
		}
		return t;
	}
	
	private static void createTranslation( HashMap t, String mask ) {
		char[] c1 = mask.toCharArray();
		char[] c2 = mask.toCharArray();
		for ( int k=0; k<c1.length; k++ ) {
			int newPos = (int)((Math.random())*c2.length);
			char cFrom = c1[k];
			char cTo = c2[newPos];
			t.put( new Character( cFrom ) , new Character( cTo ) );
			String current = new String( c2 );
			current = current.substring( 0, newPos )+current.substring( newPos+1 );
			c2 =current.toCharArray();
		}
	}
	
	public MaskBackupAdaptor() {
		super();
		this.fieldList = new ArrayList();
	}

	private HashMap translation;;
	
	private List fieldList;
	
	public void set(PreparedStatement ps, ResultSetMetaData rsmd, Object obj, int index) throws SQLException {
		Object newValue = obj;
		if ( newValue instanceof String ) {
			String oldValue = (String)obj;
			String name = rsmd.getColumnName( index ).toLowerCase();
			if ( this.fieldList.contains( name ) ) {
				newValue = translate( oldValue );	
			}
		}
		super.set(ps, rsmd, newValue, index);
	}

	private String translate( String value ) {
		StringBuffer buffer = new StringBuffer();
		for ( int k=0; k<value.length(); k++ ) {
			char c = value.charAt( k );
			char nc = c;
			Character key = new Character( c );
			if ( this.translation.containsKey( key ) ) {
				nc = ((Character)this.translation.get( key )).charValue();
			}
			buffer.append( nc );
		}
		return buffer.toString();
	}
	
	public void configure(Element config) throws Exception {
		super.configure( config );
		SearchDOM searchDOM = SearchDOM.newInstance( true, true );
		Element matchFieldList = searchDOM.findTag( config , "match-field" );
		if ( matchFieldList != null ) {
			List fieldList = searchDOM.findAllTags( matchFieldList , "field" );
			for ( int k=0; k<fieldList.size(); k++ ) {
				Element fieldTag = (Element)fieldList.get( k );
				String name = fieldTag.getAttribute( "name" );
				this.fieldList.add( name.toLowerCase() );
			}
		}
		this.translation = new HashMap();
		createTranslation( translation , MASK1 );
		createTranslation( translation , MASK2 );
		createTranslation( translation , MASK3 );
		System.out.println( "FIELD LIST "+this.fieldList );
	}

}
