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
 * @(#)DataBaseModel.java
 *
 * @project  : org.fugerit.java.core.db
 * @package  : org.fugerit.java.core.db.model
 * @creation : 16-mar-2006
 */
package org.fugerit.java.core.db.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DataBaseModel {
	
	private String databaseProductName;
	
	private String databaseProductVersion;
	
	private String driverName;
	
	private String driverVersion;
	
	public void addTable( TableModel tableModel ) {
		this.getTableList().add( tableModel );
		this.getTableMap().put( tableModel.getTableId(), tableModel );
		this.getTableNameMap().put( tableModel.getName(), tableModel );
	}
	
	public DataBaseModel() {
		this.tableList = new ArrayList();
		this.tableMap = new HashMap();
		this.tableNameMap = new HashMap();
	}
	
	private List tableList;

	private Map tableMap;
	
	private Map tableNameMap;
	
	/**
	 * @return Restituisce il valore di tableList.
	 */
	public List getTableList() {
		return tableList;
	}

	/**
	 * @return Restituisce il valore di tableMap.
	 */
	public Map getTableMap() {
		return tableMap;
	}

	/**
	 * @return the tableNameMap
	 */
	public Map getTableNameMap() {
		return tableNameMap;
	}

	/**
	 * @param tableNameMap the tableNameMap to set
	 */
	public void setTableNameMap(Map tableNameMap) {
		this.tableNameMap = tableNameMap;
	}

	/**
	 * @return the databaseProductName
	 */
	public String getDatabaseProductName() {
		return databaseProductName;
	}

	/**
	 * @param databaseProductName the databaseProductName to set
	 */
	public void setDatabaseProductName(String databaseProductName) {
		this.databaseProductName = databaseProductName;
	}

	/**
	 * @return the databaseProductVersion
	 */
	public String getDatabaseProductVersion() {
		return databaseProductVersion;
	}

	/**
	 * @param databaseProductVersion the databaseProductVersion to set
	 */
	public void setDatabaseProductVersion(String databaseProductVersion) {
		this.databaseProductVersion = databaseProductVersion;
	}

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @return the driverVersion
	 */
	public String getDriverVersion() {
		return driverVersion;
	}

	/**
	 * @param driverVersion the driverVersion to set
	 */
	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}
	
	
	
}
