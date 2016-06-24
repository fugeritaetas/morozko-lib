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
 * @(#)NavNode.java
 *
 * @project	   : simoss
 * @package	   : org.morozko.java.mod.navmap
 * @creation   : 15-giu-2005 7.36.32
 */

package org.morozko.java.mod.navmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.mod.navmap.servlet.NavController;

/**
 * <p>Classe che implementa il singolo NavNode</p>
 * 
 * @author asacca
 */
public class NavNode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7593197273138150387L;

	public static NavNode getFromSession( HttpServletRequest request ) {
		return (NavNode) request.getSession().getAttribute( NavController.ATT_NAME_NAVNODE );
	}
	
	public String toString() {
		String entry = null;
		if (this.getNavEntry()!=null) {
			entry = this.getNavEntry().getUrl();
		}
		return this.getClass().getName()+"["+entry+"]";
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param navEntry
	 * @param parent
	 * @param menuMap
	 */
	public NavNode( NavEntry navEntry, NavNode parent, Map menuMap ) {
		this.navEntry = navEntry;
		this.parent = parent;
		this.menuMap = menuMap;
	}
	
	private boolean backPath;
	
	public boolean isBackPath() {
		return backPath;
	}

	public void setBackPath(boolean backPath) {
		this.backPath = backPath;
	}

	private NavNode parent;
	
	private Map menuMap;
	
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	private NavEntry navEntry;
	
	private Integer depth;
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public NavMenu getMenu1() {
		return this.getMenu( "menu-1" );
	}
	
	public NavMenu getMenu2() {
		return this.getMenu( "menu-2" );
	}	
	
	public NavMenu getMenu3() {
		return this.getMenu( "menu-3" );
	}	
	
	public NavMenu getMenu4() {
		return this.getMenu( "menu-4" );
	}	
	
	public NavMenu getMenu5() {
		return this.getMenu( "menu-5" );
	}	
	
	/**
	 * 
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public NavMenu getMenu( String name ) {
		return (NavMenu)this.menuMap.get( name );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return ( this.getParent() == null );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public NavEntry getNavEntry() {
		return navEntry;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param navEntry
	 */
	public void setNavEntry(NavEntry navEntry) {
		this.navEntry = navEntry;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public List getPath() {
		List path = this.getPathReverse();
		int size = path.size();
		for (int k=0; k<size/2; k++) {
			Object tmp = path.get(k);
			path.set( k, path.get( size-1-k ) );
			path.set( size-1-k, tmp );
		}
		return path;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public List getPathReverse() {
		List path = new ArrayList();
		NavNode currentNode = this;
		while ( !currentNode.isRoot() ) {
			path.add( currentNode );
			currentNode = currentNode.getParent(); 
		}
		return path;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public NavNode getParent() {
		return parent;
	}
	
	public NavNode copy() {
		Map menuMap = new HashMap();
		menuMap.putAll( this.menuMap );
		NavNode navNode = new NavNode( this.navEntry.copy(), this.parent, menuMap );
		return navNode;
	}
	
	public void setMenu( String name, NavMenu navMenu ) {
		this.menuMap.put( name, navMenu );
	}
	
	
	
}
