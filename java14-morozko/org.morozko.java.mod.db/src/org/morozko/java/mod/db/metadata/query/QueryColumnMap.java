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
 * @(#)QueryColumnMap.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.metadata.query
 * @creation   : 25/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.metadata.query;

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
public class QueryColumnMap {

	private List list = new ArrayList();
	
	private Map map = new HashMap();
	
	public int size() {
		return this.list.size();
	}
	
	public void add( QueryColumnModel model ) {
		list.add( model );
		map.put( model.getName().toLowerCase(), model );
	}
	
	public Iterator iterator() {
		return this.list.iterator();
	}
	
	public QueryColumnModel get( String name ) {
		return (QueryColumnModel)this.map.get( name.toLowerCase() );
	}
	
}
