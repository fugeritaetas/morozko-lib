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
 * @(#)NavMapModule.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap.module
 * @creation   : 19-giu-2006
 */
package org.morozko.java.mod.navmap.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.mod.navmap.NavHandlerDefault;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.NavMapFactory;
import org.morozko.java.mod.navmap.NavModule;
import org.morozko.java.mod.navmap.NavNode;
import org.xml.sax.InputSource;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class NavMapModule extends NavHandlerDefault implements NavModule {

	public String toString() {
		return this.getClass().getName()+"["+this.getModuleName()+"]";
	}
	
	private NavMap parent;
	
	private NavMap current;
	
	/*
	 * (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavModule#configure(org.xml.sax.InputSource, org.morozko.java.mod.navmap.NavMap, java.lang.String, java.lang.String)
	 */
	public void configure(InputSource source, NavMap parent, String name, String home) throws Exception {
		this.parent = parent;
		this.current = NavMapFactory.configure( source, true );
		this.moduleName = name;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavModule#getParent()
	 */
	public NavMap getParent() {
		return this.parent;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavModule#getModuleVersion()
	 */
	public int getModuleVersion() {
		return CURRENT_MODULE_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavModule#getNavNode(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	public NavNode getNavNode(HttpServletRequest request, HttpServletResponse response, String module, String entry) throws Exception {
		NavNode navNode = this.getNavNode( module, entry );
		return this.handleNavNode( navNode, request, response );
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavModule#getNavNode(java.lang.String, java.lang.String)
	 */
	public NavNode getNavNode(String module, String entry) {
		this.getLog().debug( "getNavNode module : "+module+", entry : "+entry );
		NavNode navNode = this.current.getNavNode( module, entry );
		this.getLog().debug( "getNavNode navNode : "+navNode );
		return navNode;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavModule#getNavMap()
	 */
	public NavMap getNavMap() {
		return this.current;
	}

	private String moduleName;

	/**
	 * @return Restituisce il valore di moduleName.
	 */
	public String getModuleName() {
		return moduleName;
	}

}
