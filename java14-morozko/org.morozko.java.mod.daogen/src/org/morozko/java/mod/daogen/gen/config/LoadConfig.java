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
public class LoadConfig {

	public final static String TYPE_ONE = "one";
	public final static String TYPE_ALL = "all";
	
	public boolean isTypeAll() {
		return TYPE_ALL.equalsIgnoreCase( this.getType() );
	}
	
	public boolean isTypeOne() {
		return TYPE_ONE.equalsIgnoreCase( this.getType() );
	}
	
	public LoadConfig() {
		this.fieldList = new ArrayList();
	}
	
	private String name;
	
	private boolean relations;
	
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
	 * @return Restituisce il valore di relations.
	 */
	public boolean isRelations() {
		return relations;
	}

	/**
	 * @param relations il valore di relations da impostare.
	 */
	public void setRelations(boolean relations) {
		this.relations = relations;
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
