package org.fugerit.java.core.util.property;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyList extends AbstractList<PropertyModel> implements Serializable {

	public static PropertyList toPropertyList( String[][] values ) {
		PropertyList list = new PropertyList();
		for ( int k=0; k<values.length; k++ ) {
			list.add( new PropertyModel( values[k][0] , values[k][1] ) );
		}
		return list;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1020665080525249408L;
	
	private List<PropertyModel> list;
	private Map<String, PropertyModel> map;

	public PropertyList() {
		this.list = new ArrayList<PropertyModel>();
		this.map = new HashMap<String, PropertyModel>();
	}
	
	private void put( PropertyModel element ) {
		this.map.put( element.getKey() , element );
	}
	
	private void remove( PropertyModel element ) {
		this.map.remove( element.getKey() );
	}

	public PropertyModel getProperty( String key ) {
		return this.map.get( key );
	}

	public String getPropertyValueDontDefaultToKey( String key ) {
		return this.getProperty( key ).getValue();
	}
	
	public String getPropertyValue( String key ) {
		String res = key;
		PropertyModel p = this.getProperty( key );
		if ( p != null ) {
			res = p.getValue();
		}
		return res;
	}
	
	@Override
	public void add(int index, PropertyModel element) {
		this.put( element );
		this.list.add(index, element);
	}

	@Override
	public PropertyModel remove(int index) {
		PropertyModel res = this.list.remove(index);
		this.remove( res );
		return res;
	}

	@Override
	public PropertyModel set(int index, PropertyModel element) {
		this.remove( this.get(index).getKey() );
		this.set(index, element);
		return this.list.set(index, element);
	}

	@Override
	public PropertyModel get(int index) {
		return this.list.get( index );
	}

	@Override
	public int size() {
		return this.list.size();
	}
	 
	public static void main( String[] args ) {
		System.out.println( "PLC : "+new Timestamp( 12911999848101656L ).toString() );
	}
	
}
