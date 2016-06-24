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
 * @(#)NavPath.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap.servlet
 * @creation   : 8-mag-2006
 */
package org.morozko.java.mod.navmap.servlet;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.mod.navmap.NavNode;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class NavPath {

	public static NavPath handleNavPath( NavNode navNode, HttpServletRequest request ) {
		Properties params = new Properties();
		Enumeration e = request.getParameterNames();
		while ( e.hasMoreElements() ) {
			String k = (String)e.nextElement();
			params.setProperty( k, request.getParameter( k ) );
		}
		List path = navNode.getPath();
		NavPath currentNavPath = (NavPath)request.getSession().getAttribute( NavFilter.ATT_NAME_NAVPATH );
		NavPath navPath = null;
		if ( path.size()>2 ) {
			NavNode parentNavNode = (NavNode)path.get( path.size()-2 );
			if ( currentNavPath.getNavNode()==parentNavNode ) {
				navPath = new NavPath( currentNavPath, navNode, params );
			} else {
				navPath = new NavPath( null, navNode, params );
			}
		}
		request.getSession().setAttribute( NavFilter.ATT_NAME_NAVPATH, navPath );
		return navPath;
	}	
	
	private NavPath( NavPath parent, NavNode navNode, Properties params ) {
		this.params = params;
		this.parent = parent;
		this.navNode = navNode;
		this.livello = new Integer(0);
		if ( parent != null ) {
			livello = new Integer( parent.livello.intValue()+1 );
		}
	}
	
	private NavPath parent;
	
	private NavNode navNode;
	
	private Properties params;
	
	private Integer livello;

	/**
	 * @return Restituisce il valore di livello.
	 */
	public Integer getLivello() {
		return livello;
	}

	/**
	 * @return Restituisce il valore di navNode.
	 */
	public NavNode getNavNode() {
		return navNode;
	}

	/**
	 * @return Restituisce il valore di params.
	 */
	public Properties getParams() {
		return params;
	}

	/**
	 * @return Restituisce il valore di parent.
	 */
	public NavPath getParent() {
		return parent;
	}
	
	
}
