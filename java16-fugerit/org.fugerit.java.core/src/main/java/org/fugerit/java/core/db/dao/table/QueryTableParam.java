/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

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
 * @(#)QueryParam.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.dao.util.table
 * @creation   : 22/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.dao.table;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryTableParam {

	public static QueryTableParam newInstance( String columnName, Object columnValue ) {
		QueryTableParam queryTableParam = new QueryTableParam();
		queryTableParam.setColumnName( columnName );
		queryTableParam.setColumnValue( columnValue );
		return queryTableParam;
	}
	
	private String columnName;
	
	private Object columnValue;

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the columnValue
	 */
	public Object getColumnValue() {
		return columnValue;
	}

	/**
	 * @param columnValue the columnValue to set
	 */
	public void setColumnValue(Object columnValue) {
		this.columnValue = columnValue;
	}
	
}
