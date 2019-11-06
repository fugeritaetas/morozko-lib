package org.fugerit.java.core.util.collection;

import java.util.Collection;

public class ListMapStringKey<T> extends ListMap<String, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3178662537960623081L;

	public ListMapStringKey() {
		
	}
	
	public ListMapStringKey( Collection<T> c ) {
		this.addAll( c );
	}

	public ListMapStringKey(int addMode) {
		super(addMode);
	}

	public ListMapStringKey(KeyMapper<String, T> keyMapper, int addMode) {
		super(keyMapper, addMode);
	}

	public ListMapStringKey(KeyMapper<String, T> keyMapper) {
		super(keyMapper);
	}
	
	
	
}
