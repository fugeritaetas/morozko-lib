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
 * @(#)ForeignKeyModel.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.metadata
 * @creation   : 22-mag-2006
 */
package org.fugerit.java.core.db.metadata;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ForeignKeyModel  {

	public ForeignKeyModel() {
		this.columnMap = new Properties();
	}
	
	private Properties columnMap;
	
	private String name;

	private TableId foreignTableId;
	

	/**
	 * @return Restituisce il valore di foreignTableId.
	 */
	public TableId getForeignTableId() {
		return foreignTableId;
	}

	/**
	 * @param foreignTableId il valore di foreignTableId da impostare.
	 */
	public void setForeignTableId(TableId foreignTableId) {
		this.foreignTableId = foreignTableId;
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
	 * Le chiavi sono le colonne della tabella cui viene fatto riferimento.
	 * I valori sono le colonne corrispondenti nella tabella dove la foreign key è definita.
	 * 
	 * @return the columnMap
	 */
	public Properties getColumnMap() {
		return columnMap;
	}

	public List foreignColumnList( TableModel tableModel ) {
		List columnList = new ArrayList();
		Enumeration e = columnMap.keys();
		while ( e.hasMoreElements() ) {
			String columnName = (String)(e.nextElement());
			columnList.add( tableModel.getColumn( columnName ) );
		}
		return columnList;
	}
	
	/**
	 * <p>Restituisce l'elenco delle colonne interne per questo foreign key alla tabella in cui la chiave è definita.</p>
	 * 
	 * @param tableModel
	 * @return
	 */
	public List internalColumnList( TableModel tableModel ) {
		List columnList = new ArrayList();
		Enumeration e = columnMap.elements();
		while ( e.hasMoreElements() ) {
			String columnName = (String)(e.nextElement());
			columnList.add( tableModel.getColumn( columnName ) );
		}
		return columnList;
	}	
	
}
