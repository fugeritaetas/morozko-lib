package org.morozko.java.core.lang.reflect.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListToMapHelper {
	
	public static Map mapToList( List list, String keyProperty, boolean keyAsString ) throws Exception {
		Map map = new HashMap();
		Iterator it = list.iterator();
		while ( it.hasNext() ) {
			Object current = it.next();
			Object key = ReflectHelper.getProperty( current, keyProperty );
			if ( keyAsString ) {
				key = String.valueOf( key );
			}
			map.put( key , current );
		}
		return map;
	}
	
}
