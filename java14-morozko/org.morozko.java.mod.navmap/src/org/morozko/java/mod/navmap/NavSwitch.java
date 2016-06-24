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
 * @(#)NavSwitch.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap
 * @creation	: 21/ago/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap;

import java.util.Map;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavSwitch extends NavId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6802201165506521185L;
	
	private Map navSwitchKeyMap;
	
	public NavSwitch( Map navSwitchKeyMap ) {
		this.navSwitchKeyMap = navSwitchKeyMap;
	}
	private NavEntry defaultNavEntry;

	/**
	 * @return the defaultNavEntry
	 */
	public NavEntry getDefaultNavEntry() {
		return defaultNavEntry;
	}
	/**
	 * @param defaultNavEntry the defaultNavEntry to set
	 */
	public void setDefaultNavEntry(NavEntry defaultNavEntry) {
		this.defaultNavEntry = defaultNavEntry;
	}
	
	public NavEntry findNavSwitch( String key ) {
		return (NavEntry)this.navSwitchKeyMap.get( key );
	}
	
}
