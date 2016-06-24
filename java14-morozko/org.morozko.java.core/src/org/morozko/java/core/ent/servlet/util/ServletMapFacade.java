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
 * @(#)RequestFacade.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.request
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.util;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ServletMapFacade {

	public static void putAttributes( PageContext context, int scope, Map map ) {
		Enumeration e = context.getAttributeNamesInScope( scope );
		while ( e.hasMoreElements() ) {
			String name = (String)e.nextElement();
			Object value = context.getAttribute( name );
			map.put( name, value );
		}
	}		
	
	public static void putAttributes( PageContext context, Map map ) {
		putAttributes( context, PageContext.PAGE_SCOPE, map);
	}			
	
	public static void putAttributes( ServletContext context, Map map ) {
		Enumeration e = context.getAttributeNames();
		while ( e.hasMoreElements() ) {
			String name = (String)e.nextElement();
			Object value = context.getAttribute( name );
			map.put( name, value );
		}
	}		
	
	public static void putAttributes( HttpSession session, Map map ) {
		Enumeration e = session.getAttributeNames();
		while ( e.hasMoreElements() ) {
			String name = (String)e.nextElement();
			Object value = session.getAttribute( name );
			map.put( name, value );
		}
	}		
	
	public static void putAttributes( HttpServletRequest request, Map map ) {
		Enumeration e = request.getAttributeNames();
		while ( e.hasMoreElements() ) {
			String name = (String)e.nextElement();
			Object value = request.getAttribute( name );
			map.put( name, value );
		}
	}	
	
	public static void putParameters( HttpServletRequest request, Map map ) {
		Enumeration e = request.getParameterNames();
		while ( e.hasMoreElements() ) {
			String name = (String)e.nextElement();
			String value = request.getParameter( name );
			map.put( name, value );
		}
	}
	
}
