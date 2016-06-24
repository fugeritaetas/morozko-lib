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
 * @(#)TableModel.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.metadata
 * @creation   : 22-mag-2006
 */
package org.morozko.java.mod.db.metadata;

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
public class TableModel extends ColumnContainer {

	public void addForeignKey( ForeignKeyModel foreignKeyModel ) {
		this.foreignKeyMap.put( foreignKeyModel.getName(), foreignKeyModel );
		this.foreignKeyList.add( foreignKeyModel );
	}	
	
	public void addIndex( IndexModel indexModel ) {
		this.indexMap.put( indexModel.getName(), indexModel );
		this.indexList.add( indexModel );
	}
	
	public TableModel() {
		this.indexMap = new HashMap();
		this.indexList = new ArrayList();
		this.foreignKeyMap = new HashMap();
		this.foreignKeyList = new ArrayList();
	}
	
	private Map foreignKeyMap;
	
	private List foreignKeyList;
	
	private Map indexMap;
	
	private List indexList;
	
	private TableId tableId;
	
	private IndexModel primaryKey;
	
	private String comment;

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the primaryKey
	 */
	public IndexModel getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(IndexModel primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return Restituisce il valore di tableId.
	 */
	public TableId getTableId() {
		return tableId;
	}

	/**
	 * @param tableId il valore di tableId da impostare.
	 */
	public void setTableId(TableId tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return Restituisce il valore di name.
	 */
	public String getName() {
		return this.getTableId().getTableName();
	}
	
	/**
	 * @return Restituisce il valore di name.
	 */
	public String getCatalog() {
		return this.getTableId().getTableCatalog();
	}	

	/**
	 * @return Restituisce il valore di name.
	 */
	public String getSchema() {
		return this.getTableId().getTableSchema();
	}	
	
	/**
	 * @return Restituisce il valore di indexMap.
	 */
	public Map getIndexMap() {
		return indexMap;
	}

	/**
	 * @return Restituisce il valore di indexList.
	 */
	public List getIndexList() {
		return indexList;
	}

	/**
	 * @return Restituisce il valore di foreignKeyList.
	 */
	public List getForeignKeyList() {
		return foreignKeyList;
	}

	/**
	 * @return Restituisce il valore di foreignKeyMap.
	 */
	public Map getForeignKeyMap() {
		return foreignKeyMap;
	}
	
}
