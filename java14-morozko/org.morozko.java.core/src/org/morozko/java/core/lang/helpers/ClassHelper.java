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
 * @(#)ClassHelper.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.lang.helpers
 * @creation   : 11-mag-2006
 */
package org.morozko.java.core.lang.helpers;

import java.io.InputStream;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ClassHelper {
	
	public static InputStream getResourceStream( String path, Class c ) throws Exception {
		InputStream is = getDefaultClassLoader().getResourceAsStream( path );
		if ( is == null && c != null ) {
			is = c.getResourceAsStream( path );
		}
		return is;
	}
	
	public static ClassLoader getDefaultClassLoader() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if ( classLoader == null ) {
			classLoader = ClassHelper.class.getClassLoader();
		}
		return classLoader;
	}
	 
	public static Object newInstance( String type ) throws Exception {
		Object result = null;
		ClassLoader classLoader = getDefaultClassLoader();
		Class c = classLoader.loadClass( type );
		result = c.newInstance();
		return result;
	}
	
}
