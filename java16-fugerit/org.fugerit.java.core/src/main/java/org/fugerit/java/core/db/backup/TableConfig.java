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
 * @(#)TableConfig.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.backup
 * @creation   : 25/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.backup;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class TableConfig {
	
	private String table;
	
	private String select;
	
	private String meta;


	private BackupAdaptor adaptorFrom;
	
	private BackupAdaptor adaptorTo;
	
	/**
	 * @return the select
	 */
	public String getSelect() {
		return select;
	}

	/**
	 * @param select the select to set
	 */
	public void setSelect(String select) {
		this.select = select;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public BackupAdaptor getAdaptorFrom() {
		return adaptorFrom;
	}

	public void setAdaptorFrom(BackupAdaptor adaptorFrom) {
		this.adaptorFrom = adaptorFrom;
	}

	public BackupAdaptor getAdaptorTo() {
		return adaptorTo;
	}

	public void setAdaptorTo(BackupAdaptor adaptorTo) {
		this.adaptorTo = adaptorTo;
	}

	
	
	
	
}
