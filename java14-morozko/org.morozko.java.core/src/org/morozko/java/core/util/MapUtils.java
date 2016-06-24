/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)MapUtils.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.util
 * @creation   : 8-giu-2006
 */
package org.morozko.java.core.util;

import java.util.Iterator;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class MapUtils {

	public static boolean equalsAll( Map map1, Map map2 ) {
		boolean ok = true;
		if ( map1 != null && map2 != null ) {
			if ( map1.size() == map2.size() ) {
				Iterator it = map1.keySet().iterator();
				while ( it.hasNext() && ok ) {
					Object key = it.next();
					Object value1 = map1.get( key );
					Object value2 = map2.get( key );
					ok = value1.equals( value2 );
				}
			}
		} else {
			ok = ( map1 == null && map2 == null );
		}
		return ok;
	}
	
}
