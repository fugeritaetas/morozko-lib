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
 * @(#)RelationConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 14-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config;

import java.util.List;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class RelationConfig {

	public RelationConfig() {
		this.cascade = false;
		this.sql = null;
	}
	
	private final static String TYPE_ONETOONE = "oneToOne";
	
	private final static String TYPE_ONETOMANY = "oneTomany";
	
	public boolean isTypeOneToOne() {
		return TYPE_ONETOONE.equalsIgnoreCase( this.getType() );
	}
	
	public boolean isTypeOneToMany() {
		return TYPE_ONETOMANY.equalsIgnoreCase( this.getType() );
	}	
	
	private String name;
	
	private String type;
	
	private List fieldList;
	
	private List fieldOutList;
	
	private String table;

	private String tableOut;
	
	private String orderBy;
	
	private boolean cascade;

	/**
	 * @return Restituisce il valore di cascade.
	 */
	public boolean isCascade() {
		return cascade;
	}

	/**
	 * @param cascade il valore di cascade da impostare.
	 */
	public void setCascade(boolean cascade) {
		this.cascade = cascade;
	}

	/**
	 * @return Restituisce il valore di orderBy.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy il valore di orderBy da impostare.
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return Restituisce il valore di tableOut.
	 */
	public String getTableOut() {
		return tableOut;
	}

	/**
	 * @param tableOut il valore di tableOut da impostare.
	 */
	public void setTableOut(String tableOut) {
		this.tableOut = tableOut;
	}

	/**
	 * @return Restituisce il valore di table.
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table il valore di table da impostare.
	 */
	public void setTable(String table) {
		this.table = table;
	}

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

	/**
	 * @param fieldOutList il valore di fieldOutList da impostare.
	 */
	public void setFieldOutList(List fieldOutList) {
		this.fieldOutList = fieldOutList;
	}

	/**
	 * @return Restituisce il valore di fieldOutList.
	 */
	public List getFieldOutList() {
		return fieldOutList;
	}

	private String sql;

	/**
	 * @return Restituisce il valore di sql.
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql il valore di sql da impostare.
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
