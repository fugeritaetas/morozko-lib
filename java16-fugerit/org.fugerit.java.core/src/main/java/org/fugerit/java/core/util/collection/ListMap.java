package org.fugerit.java.core.util.collection;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListMap<K,T> extends AbstractList<T> implements Serializable {
	
	/**
	 * Add mode  STRICT ( raise a runtime exception in case of duplicate keys )
	 */
	public static final int ADD_MODE_STRICT = 1;
	
	/**
	 * Add mode LOOSE ( allow for key duplicate)
	 */
	public static final int ADD_MODE_LOOSE = 2;
	
	/**
	 * Default add mode (LOOSE)
	 */
	public static final int ADD_MODE_DEFAULT = ADD_MODE_LOOSE;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -10202423525249408L;
	
	private List<T> list;
	private Map<K, T> map;
	private KeyMapper<K,T> keyMapper;
	private int addMode;
	
	public KeyMapper<K, T> getKeyMapper() {
		return keyMapper;
	}

	public void setKeyMapper(KeyMapper<K, T> keyMapper) {
		this.keyMapper = keyMapper;
	}

	
	public ListMap( KeyMapper<K,T> keyMapper, int addMode ) {
		this.list = new ArrayList<T>();
		this.map = new HashMap<K, T>();
		this.keyMapper = keyMapper;
		this.addMode = addMode;
	}
	
	public ListMap( KeyMapper<K,T> keyMapper ) {
		this( keyMapper, ADD_MODE_DEFAULT ); 
	}
	
	public ListMap( int addMode ) {
		this( null, addMode ); 
	}
	
	public ListMap() {
		this( null );
	}
	
	private K getKey( T element ) {
		K key = null;
		if ( this.keyMapper != null ) {
			key = this.keyMapper.createKey( element );
		} else  if ( element instanceof KeyObject ) {
			key = ((KeyObject<K>)element).getKey();
		} else {
			throw new RuntimeException( "No key rule for object : "+element );
		}
		return key;
	}
	
	private void putWorker( T element ) {
		this.map.put( this.getKey( element ) , element );
	}
	
	private void removeWorker( T element ) {
		this.map.remove( this.getKey( element ) );
	}

	public T getProperty( K key ) {
		return this.getProperty( key );
	}
	
	@Override
	public void add(int index, T element) {
		if ( this.getAddMode() == ADD_MODE_STRICT && this.map.containsKey( this.getKey( element ) ) ) {
			throw new RuntimeException( "Key already exists for element : "+element );
		} else {
			this.putWorker( element );
			this.list.add(index, element);
		}
		
	}

	@Override
	public T remove(int index) {
		T res = this.list.remove(index);
		this.removeWorker( res );
		return res;
	}

	@Override
	public T set(int index, T element) {
		this.removeWorker( element );
		this.putWorker( element );
		return this.list.set( index, element );
	}

	@Override
	public T get(int index) {
		return this.list.get( index );
	}

	@Override
	public int size() {
		return this.list.size();
	}
 
	public T get( Object key ) {
		return this.map.get( key );
	}

	public Iterator<K> getKeyIterator() {
		return this.map.keySet().iterator();
	}
	
	public Map<K, T> getMap() {
		return Collections.unmodifiableMap( this.map );
	}

	public int getAddMode() {
		return addMode;
	}

	public void setAddMode(int addMode) {
		this.addMode = addMode;
	}
	
	
	
}

