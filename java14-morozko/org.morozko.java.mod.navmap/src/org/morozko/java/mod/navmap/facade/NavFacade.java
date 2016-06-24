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
 * @(#)NavFacade.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap.facade
 * @creation	: 23/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap.facade;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.auth.AuthHandler;
import org.morozko.java.mod.navmap.servlet.NavController;
import org.morozko.java.mod.web.servlet.config.ConfigContext;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavFacade {

	public static final String ATT_NAME = "org.morozko.java.mod.navmap.facade.NavFacade";
	
	public static void putInRequest( HttpServletRequest request, NavController navController ) {
		request.setAttribute( ATT_NAME , new NavFacade( navController ) );
	}
	
	public static NavFacade getFromRequest( HttpServletRequest request ) {
		return (NavFacade)request.getAttribute( ATT_NAME );
	}
	
	private NavController navController;

	/**
	 * @return the navController
	 */
	public NavController getNavController() {
		return navController;
	}

	/**
	 * @param navController
	 */
	public NavFacade(NavController navController) {
		super();
		this.navController = navController;
	}

	/**
	 * @return
	 * @see org.morozko.java.mod.navmap.servlet.NavController#getAuthHandler()
	 */
	public AuthHandler getAuthHandler() {
		return navController.getAuthHandler();
	}

	/**
	 * @return
	 * @see org.morozko.java.mod.navmap.servlet.NavController#getContext()
	 */
	public ConfigContext getContext() {
		return navController.getContext();
	}

	/**
	 * @return
	 * @see org.morozko.java.mod.navmap.servlet.NavController#getNavMap()
	 */
	public NavMap getNavMap() {
		return navController.getNavMap();
	}
	
}
