/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
 * @(#)ParameterUtils.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.http
 * @creation   : 8-giu-2006
 */
package org.morozko.java.core.ent.servlet.http;

import java.util.Enumeration;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ParameterUtils {

	public static String encodeParameterString( Properties params ) throws Exception {
		StringBuffer result = new StringBuffer();
		if ( params != null ) {
			Enumeration e = params.keys();
			boolean first = true;
			while ( e.hasMoreElements() ) {
				String key = (String) e.nextElement();
				String value = params.getProperty( key );
				if ( first ) {
					first = false;
					result.append( "?" );
				} else {
					result.append( "&" );
				}
				result.append( key );
				result.append( "=" );
				result.append( value );
			}
		}
		return result.toString();
	}
	
}
