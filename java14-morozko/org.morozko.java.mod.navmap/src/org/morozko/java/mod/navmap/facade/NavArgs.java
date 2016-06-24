/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)NavArgs.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap.facade
 * @creation	: 11/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavArgs {

	private static List getNavArgList( HttpServletRequest request, String navUrl ) {
		Map map = (Map)request.getSession().getAttribute( "org.morozko.java.mod.navmap.servlet.ArgMap" );
		if ( map == null ) {
			map = new HashMap();
			request.getSession().setAttribute( "org.morozko.java.mod.navmap.servlet.ArgMap" , map );
		}
		List list = (List)map.get( navUrl );
		if ( list == null ) {
			list = new ArrayList();
			map.put( navUrl , list );
		}
		return list;
	}
	
	public static void addNavArg( HttpServletRequest request, String navUrl, Object arg ) {
		List list = getNavArgList( request, navUrl );
		list.add( arg );
	}
	
	public static Object[] getNavArg( HttpServletRequest request, String navUrl ) {
		return getNavArgList(request, navUrl).toArray();
	}
	
	public static void resetNavArg( HttpServletRequest request, String navUrl ) {
		getNavArgList(request, navUrl).clear();
	}
	
}
