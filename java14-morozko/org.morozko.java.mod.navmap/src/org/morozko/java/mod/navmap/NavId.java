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
 * @(#)NavId.java
 *
 * @project     : org.morozko.java.mod.navmap
 * @package     : org.morozko.java.mod.navmap
 * @creation	: 21/ago/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.navmap;

import java.io.Serializable;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class NavId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7330064151867921844L;

	public String toString() {
		return this.getClass().getName()+"["+
					"module="+this.getModule()+";"+
					"name="+this.getName()+";"+
					"]";
	}	
	
	public static String renderUrl( String module, String name ) {
		String url = name;
		if ( module!=null ) {
			url = module+"/"+name;
		}
		return url;
	}	
	
	private String name;
	
	private String module;

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * <p>Restituisce l'url di questa NavEntry.</p>
	 * 
	 * <p>Se l'entry fa parte di un modulo l'url è
	 * uguale a : {module}/{name}. Altrimenti
	 * l'url è solamente {name}.</p>
	 * 
	 * @return	l'url dell' entry
	 */
	public String getUrl() {
		return renderUrl( this.getModule(), this.getName() );
	}	
	
}
