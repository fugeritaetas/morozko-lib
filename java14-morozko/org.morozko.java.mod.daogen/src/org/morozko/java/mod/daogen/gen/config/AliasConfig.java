/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

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
 * @(#)AliasConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 2-mag-2006
 */
package org.morozko.java.mod.daogen.gen.config;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class AliasConfig {

	private String name;
	
	private String field;

	/**
	 * @return Restituisce il valore di field.
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field il valore di field da impostare.
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return Restituisce il valore di name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name il valore di name da impostare.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
