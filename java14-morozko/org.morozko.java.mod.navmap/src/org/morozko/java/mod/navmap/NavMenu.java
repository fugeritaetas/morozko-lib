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
 * @(#)NavMenu.java
 *
 * @project	   : simoss
 * @package	   : org.morozko.java.mod.navmap
 * @creation   : 15-giu-2005 7.36.32
 */

package org.morozko.java.mod.navmap;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe che implemeta il menu dei nodi della NavMap</p>
 * 
 * @author asacca
 *
 */
public class NavMenu extends ArrayList implements NavMenuItemContainer {

	public String toString() {
		return this.getClass().getName()+"[name:"+this.getName()+"]"+super.toString();
	}
	
	
	private static final long serialVersionUID = -1622434503645384092L;
	
	/**
	 * 
	 * <p>Crea una nuova istanza di NavMenu.</p>
	 * 
	 * @param nome il nome del menu
	 */
	public NavMenu(String nome) {
		super();
        setName(nome);
        this.sectionList = new ArrayList();
	}
	
	private List sectionList;
	
	private String name;
	
	/**
	 * 
	 * <p>Imposta il valore di name.</p>
	 * 
	 * @param name il valore di name
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * 
	 * <p>Restituisce il valore di name.</p>
	 * 
	 * @return Restituisce il valore di name
	 */
	public String getName() {
		return name;
	}
	
	public NavMenu copy() {
		NavMenu navMenu = new NavMenu( this.name );
		navMenu.addAll( this );
		return navMenu;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavMenuItemContainer#add(org.morozko.java.mod.navmap.NavEntry)
	 */
	public void add(NavEntry navEntry) {
		this.add( (Object)navEntry );
	}

	/**
	 * @return the sectionList
	 */
	public List getSectionList() {
		return sectionList;
	}
	
	

}
