package org.morozko.java.core.lang.reflect.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListHelper {

	public static List attributeList( List list, String keyProperty, boolean keyAsString ) throws Exception {
		List result = new ArrayList();
		Iterator it = list.iterator();
		while ( it.hasNext() ) {
			Object current = it.next();
			Object key = ReflectHelper.getProperty( current, keyProperty );
			if ( keyAsString ) {
				key = String.valueOf( key );
			}
			result.add( key );
		}
		return result;
	}
	
	
}
