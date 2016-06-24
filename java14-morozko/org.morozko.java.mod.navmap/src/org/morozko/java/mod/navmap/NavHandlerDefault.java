/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
 * @(#)NavHandlerDefault.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap
 * @creation   : 7-giu-2006
 */
package org.morozko.java.mod.navmap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.log.BasicLogObject;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class NavHandlerDefault extends BasicLogObject implements NavHandler {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavHandler#getHandlerVersion()
	 */
	public int getHandlerVersion() {
		return CURRENT_HANDLER_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavHandler#isCurrent(org.morozko.java.mod.navmap.NavEntry, javax.servlet.http.HttpServletRequest)
	 */
	public boolean isCurrent(NavEntry navEntry, HttpServletRequest request) throws Exception {
		boolean ok = false;
		NavNode current = NavNode.getFromSession( request );
		String url1 = current.getNavEntry().getUrl();
		String url2 = navEntry.getUrl();
		this.getLog().debug( "isCurrent url 1 : '"+url1+"'" );
		this.getLog().debug( "isCurrent url 2 : '"+url2+"'" );
		ok = url1.equals( url2 );
		this.getLog().debug( "isCurrent check : '"+ok+"'" );
		return ok;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavHandler#handleNavNode(org.morozko.java.mod.navmap.NavNode, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public NavNode handleNavNode(NavNode navNode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return navNode;
	}





}
