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
 * @(#)ColumnContainer.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.metadata
 * @creation   : 22-mag-2006
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
public class ColumnContainer {

	
	private List columnList;
	
	private Map columnMap;
	
	private Map columnMapNoCase;
	

	/**
	 * @return Restituisce il valore di columnList.
	 */
	public List getColumnList() {
		return columnList;
	}

	/**
	 * @return Restituisce il valore di columnMap.
	 */
	public Map getColumnMap() {
		return columnMap;
	}	
	
	public ColumnContainer() {
		this.columnList = new ArrayList();
		this.columnMap = new HashMap();
		this.columnMapNoCase = new HashMap();
	}
	
	public void addColumn( ColumnModel columnModel ) {
		if ( columnModel != null ) {
			this.columnList.add( columnModel );
			this.columnMap.put( columnModel.getName(), columnModel );
			this.columnMapNoCase.put( columnModel.getName().toLowerCase(), columnModel );
		}
	}	
	
	public ColumnModel getColumn( String columnName ) {
		ColumnModel columnModel = (ColumnModel)this.getColumnMap().get( columnName );
		if ( columnModel == null ) {
			columnModel = (ColumnModel)this.columnMapNoCase.get( columnName.toLowerCase() );
		}
		return columnModel;
	}
	
}
