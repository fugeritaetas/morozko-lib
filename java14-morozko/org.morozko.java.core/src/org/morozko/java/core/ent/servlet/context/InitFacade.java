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
 * @(#)InitFacade.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.context
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.context;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class InitFacade {

	public static Properties toProperties( ServletConfig config ) {
		Properties props = new Properties();
		Enumeration e = config.getInitParameterNames();
		while ( e.hasMoreElements() ) {
			String key = (String)e.nextElement();
			String value = config.getInitParameter( key );
			props.setProperty( key, value );
		}
		return props;
	}	
	
	public static Properties toProperties( FilterConfig config ) {
		Properties props = new Properties();
		Enumeration e = config.getInitParameterNames();
		while ( e.hasMoreElements() ) {
			String key = (String)e.nextElement();
			String value = config.getInitParameter( key );
			props.setProperty( key, value );
		}
		return props;
	}
	
}
