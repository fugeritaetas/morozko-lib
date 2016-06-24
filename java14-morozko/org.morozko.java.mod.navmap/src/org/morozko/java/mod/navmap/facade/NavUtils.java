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
 * @(#)NavUtils.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap.facade
 * @creation	: 21/ago/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap.facade;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.navmap.NavEntry;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.NavSwitch;
import org.morozko.java.mod.navmap.auth.AuthException;
import org.morozko.java.mod.navmap.auth.AuthHandler;
import org.morozko.java.mod.navmap.servlet.NavController;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavUtils {

	public static int checkAuth( HttpServletRequest request, String auth ) throws AuthException {
		NavFacade navFacade = NavFacade.getFromRequest( request );
		AuthHandler authHandler = navFacade.getAuthHandler();
		return authHandler.checkAuth( request , auth );
	}	
	
	public static String getNavSwitchKey( HttpServletRequest request ) {
		return (String)request.getAttribute( "NavController.NavSwitchKey" );
	}
	
	public static void setNavSwitchKey( String key, HttpServletRequest request ) {
		request.setAttribute( "NavController.NavSwitchKey" , key );
	}
	
	public static void setNavNode( HttpServletRequest request, String navUrl ) {
		NavFacade navFacade = NavFacade.getFromRequest( request );
		NavMap navMap = navFacade.getNavMap();
		request.getSession().setAttribute( NavController.ATT_NAME_NAVNODE , navMap.getNavNodeByNavUrl( navUrl ) );
	}
	
	public static String parseNavUrl( HttpServletRequest request ) {
		String uri =  request.getRequestURI();
		String navUrl = uri.substring( request.getContextPath().length()+1, uri.length()-3 );
		return navUrl;
	}
	
	public static void changeNavSwitchKey( ServletContext context, HttpServletRequest request, String key ) throws Exception {
		String navUrl = parseNavUrl( request );
		LogFacade.getLog().info( "NavUtils.changeNavSwitchKey : key="+key+" navUrl="+navUrl );
		NavMap navMap = (NavMap)context.getAttribute( NavController.ATT_NAME_NAVMAP );
		NavSwitch navSwitch = navMap.getNavSwitchByUrl( navUrl );
		NavEntry navEntry = navSwitch.findNavSwitch( key );
		NavNode navNode = navMap.getNavNodeByNavUrl( navEntry.getUrl() );
		LogFacade.getLog().info( "NavUtils.changeNavSwitchKey : navNode="+navNode );
		request.setAttribute( NavController.ATT_NAME_NAVNODE, navNode );
	}
	
}
