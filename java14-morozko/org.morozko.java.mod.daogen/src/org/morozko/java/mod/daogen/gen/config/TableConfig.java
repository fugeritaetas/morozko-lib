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
 * @(#)TableConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class TableConfig {

	private String xmlTable;
	
	public String getXmlTable() {
		return xmlTable;
	}

	public void setXmlTable(String xmlTable) {
		this.xmlTable = xmlTable;
	}

	public boolean isFake() {
		return fake;
	}

	public void setFake(boolean fake) {
		this.fake = fake;
	}

	public LoadConfig getLoad( String loadName ) {
		LoadConfig loadConfig = null;
		Iterator itLoad = this.getLoadList().iterator();
		while ( itLoad.hasNext() ) {
			LoadConfig current = (LoadConfig) itLoad.next();
			if ( current.getName().equals( loadName ) ) {
				loadConfig = current;
			}
		}
		return loadConfig;
	}
	
	public FieldConfig getFieldConfig( String fieldName ) {
		FieldConfig fieldConfig = null;
		Iterator it = this.getFields().iterator();
		while ( fieldConfig == null && it.hasNext() ) {
			FieldConfig current = (FieldConfig)it.next();
			// equalsIgnoreCase() instead of equals() is a bugfix for version 1.9.17
			if ( current.getFieldName().equalsIgnoreCase( fieldName ) ) {
				fieldConfig = current;
			}
		}
		return fieldConfig;
	}
	
	public TableConfig() {
		this.fields = new ArrayList();
		this.relations = new ArrayList();
		this.relationsOut = new ArrayList();
		this.fieldsUpdate = new ArrayList();
		this.loadList = new ArrayList();
		this.updateKey = null;
		this.aliasList = new ArrayList();
		this.operationsList = new ArrayList();
	}
	
	public List getOperationsList() {
		return operationsList;
	}

	private boolean fake;
	
	private List relationsOut;
	
	private List relations;
	
	private String tableName;
	
	private String tableUpdateSQL;
	
	private String tableViewSQL;
	
	private List fields;
	
	private List fieldsUpdate;
	
	private String[] updateKey;
	
	private String orderBy;
	
	private List loadList;
	
	private List aliasList;

	private String idGenerator;
	
	private List operationsList;

	/**
	 * @return the idGenerator
	 */
	public String getIdGenerator() {
		return idGenerator;
	}

	/**
	 * @param idGenerator the idGenerator to set
	 */
	public void setIdGenerator(String idGenerator) {
		this.idGenerator = idGenerator;
	}

	/**
	 * @return Restituisce il valore di loadList.
	 */
	public List getLoadList() {
		return loadList;
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
	 * @return Restituisce il valore di fields.
	 */
	public List getFields() {
		return fields;
	}

	/**
	 * @param fields il valore di fields da impostare.
	 */
	public void setFields(List fields) {
		this.fields = fields;
	}

	/**
	 * @return Restituisce il valore di tableName.
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName il valore di tableName da impostare.
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return Restituisce il valore di tableUpdateSQL.
	 */
	public String getTableUpdateSQL() {
		return tableUpdateSQL;
	}

	/**
	 * @param tableUpdateSQL il valore di tableUpdateSQL da impostare.
	 */
	public void setTableUpdateSQL(String tableUpdateSQL) {
		this.tableUpdateSQL = tableUpdateSQL;
	}

	/**
	 * @return Restituisce il valore di tableViewSQL.
	 */
	public String getTableViewSQL() {
		return tableViewSQL;
	}

	/**
	 * @param tableViewSQL il valore di tableViewSQL da impostare.
	 */
	public void setTableViewSQL(String tableViewSQL) {
		this.tableViewSQL = tableViewSQL;
	}

	/**
	 * @return Restituisce il valore di updateKey.
	 */
	public String[] getUpdateKey() {
		return updateKey;
	}

	/**
	 * @param updateKey il valore di updateKey da impostare.
	 */
	public void setUpdateKey(String[] updateKey) {
		this.updateKey = updateKey;
	}

	/**
	 * @return Restituisce il valore di fieldsUpdate.
	 */
	public List getFieldsUpdate() {
		return fieldsUpdate;
	}

	/**
	 * @return Restituisce il valore di relations.
	 */
	public List getRelations() {
		return relations;
	}

	/**
	 * @param relations il valore di relations da impostare.
	 */
	public void setRelations(List relations) {
		this.relations = relations;
	}

	/**
	 * @return Restituisce il valore di relationsOut.
	 */
	public List getRelationsOut() {
		return relationsOut;
	}

	/**
	 * @param relationsOut il valore di relationsOut da impostare.
	 */
	public void setRelationsOut(List relationsOut) {
		this.relationsOut = relationsOut;
	}

	/**
	 * @return Restituisce il valore di aliasList.
	 */
	public List getAliasList() {
		return aliasList;
	}

}
