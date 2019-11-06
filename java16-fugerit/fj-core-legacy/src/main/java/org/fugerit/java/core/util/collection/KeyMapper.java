package org.fugerit.java.core.util.collection;

import java.io.Serializable;

public interface KeyMapper<K,T> extends Serializable {

	public K createKey( T key );
	
}
