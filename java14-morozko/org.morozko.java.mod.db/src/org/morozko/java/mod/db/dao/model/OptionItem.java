/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)OptionItem.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.model
 * @creation   : 12-giu-2006
 */
package org.morozko.java.mod.db.dao.model;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class OptionItem {

	private String label;
	
	private String value;

	/**
	 * @return Restituisce il valore di label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label il valore di label da impostare.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return Restituisce il valore di value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value il valore di value da impostare.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
