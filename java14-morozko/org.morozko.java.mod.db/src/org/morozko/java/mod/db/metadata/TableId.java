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
 * @(#)TableId.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.metadata
 * @creation   : 22-mag-2006
 */
package org.morozko.java.mod.db.metadata;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class TableId {
	
	public String toString() {
		return this.getClass().getName()+"[tableCatalog:"+this.getTableCatalog()+",tableSchema:"+this.getTableSchema()+",tableName:"+this.getTableName()+"]";
	}
	
	private String tableName;
	
	private String tableSchema;
	
	private String tableCatalog;

	/**
	 * @return Restituisce il valore di tableCatalog.
	 */
	public String getTableCatalog() {
		return tableCatalog;
	}

	/**
	 * @param tableCatalog il valore di tableCatalog da impostare.
	 */
	public void setTableCatalog(String tableCatalog) {
		this.tableCatalog = tableCatalog;
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
	 * @return Restituisce il valore di tableSchema.
	 */
	public String getTableSchema() {
		return tableSchema;
	}

	/**
	 * @param tableSchema il valore di tableSchema da impostare.
	 */
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	private static boolean equals( Object o1, Object o2 ) {
		return ( o1 == null && o2 == null ) || ( o1.equals( o2 ) );
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean eq = false;
		if ( obj instanceof TableId ) {
			TableId tableId = (TableId)obj;
			eq = equals( this.getTableCatalog(), tableId.getTableCatalog() )
				&& equals( this.getTableSchema(), tableId.getTableSchema() )
				&& equals( this.getTableName(), tableId.getTableName() );
		}
		return eq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int h = Integer.MIN_VALUE;
		if ( this.getTableCatalog()!=null ) {
			h+= tableCatalog.hashCode();
		}
		if ( this.getTableName()!=null ) {
			h+= getTableName().hashCode();
		}
		if ( this.getTableSchema()!=null ) {
			h+= this.getTableSchema().hashCode();
		}
		return h;
	}
	
}
