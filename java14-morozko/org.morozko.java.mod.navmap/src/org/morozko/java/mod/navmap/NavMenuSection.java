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
 * @(#)NavMenuSection.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap
 * @creation	: 23/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavMenuSection implements NavMenuItemContainer {

	private String name;
	
	private NavEntry navEntry;
	
	private List navEntryList;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the navEntryList
	 */
	public List getNavEntryList() {
		return navEntryList;
	}

	/**
	 * @param name
	 * @param display
	 * @param navEntryList
	 */
	public NavMenuSection(String name, NavEntry navEntry) {
		super();
		this.name = name;
		this.navEntry = navEntry;
		this.navEntryList = new ArrayList();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavMenuItemContainer#add()
	 */
	public void add( NavEntry navEntry ) {
		this.getNavEntryList().add( navEntry );
		
	}

	/**
	 * @return the navEntry
	 */
	public NavEntry getNavEntry() {
		return navEntry;
	}
	
}
