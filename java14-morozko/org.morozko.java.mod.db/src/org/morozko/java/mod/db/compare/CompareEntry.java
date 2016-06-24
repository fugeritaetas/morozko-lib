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
 * @(#)CompareEntry.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.compare
 * @creation   : 06/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.compare;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CompareEntry {

	private String queryFrom;
	
	private String queryTo;

	/**
	 * @return the queryFrom
	 */
	public String getQueryFrom() {
		return queryFrom;
	}

	/**
	 * @param queryFrom the queryFrom to set
	 */
	public void setQueryFrom(String queryFrom) {
		this.queryFrom = queryFrom;
	}

	/**
	 * @return the queryTo
	 */
	public String getQueryTo() {
		return queryTo;
	}

	/**
	 * @param queryTo the queryTo to set
	 */
	public void setQueryTo(String queryTo) {
		this.queryTo = queryTo;
	}
	
}
