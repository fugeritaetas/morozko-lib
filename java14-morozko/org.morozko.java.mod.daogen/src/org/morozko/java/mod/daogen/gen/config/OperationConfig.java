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
 * @(#)LoadConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 28-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class OperationConfig {

	public final static String TYPE_UPDATE = "update";
	public final static String TYPE_DELETE = "delete";
	
	public boolean isTypeUpdate() {
		return TYPE_UPDATE.equalsIgnoreCase( this.getType() );
	}
	
	public boolean isTypeDelete() {
		return TYPE_DELETE.equalsIgnoreCase( this.getType() );
	}
	
	public OperationConfig() {
		this.fieldList = new ArrayList();
	}
	
	private String name;
	
	private List fieldList;
	
	private String type;

	/**
	 * @return Restituisce il valore di fieldList.
	 */
	public List getFieldList() {
		return fieldList;
	}

	/**
	 * @param fieldList il valore di fieldList da impostare.
	 */
	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
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

	/**
	 * @return Restituisce il valore di type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type il valore di type da impostare.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
