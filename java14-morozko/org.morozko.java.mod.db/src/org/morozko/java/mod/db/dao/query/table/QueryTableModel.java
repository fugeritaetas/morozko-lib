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
 * @(#)QueryResult.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.util.table
 * @creation   : 22/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.dao.query.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryTableModel {

	public void addColumn( String name, QueryTableColumn column ) {
		this.valueMap.put( name.toLowerCase(), column );
		this.valueList.add( column );
	}
	
	public QueryTableModel() {
		this.valueList = new ArrayList();
		this.valueMap = new HashMap();
	}
	
	private Map valueMap;
	
	private List valueList;
	
	public Iterator iterateQueryColumn() {
		return this.valueList.iterator();
	}
	
	public QueryTableColumn getQueryColumn( String name ) {
		return (QueryTableColumn)this.valueMap.get( name.toLowerCase() );
	}
	
	public QueryTableColumn getQueryColumn( int index ) {
		return (QueryTableColumn)this.valueList.get( index );
	}	
	
}
