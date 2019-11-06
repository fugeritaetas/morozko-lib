/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.doc 

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
 * @(#)DocElement.java
 *
 * @project    : org.fugerit.java.core.doc
 * @package    : org.fugerit.java.core.doc
 * @creation   : 06/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.doc;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DocElement {

	public String toString() {
		return this.getClass().getName();
	}
	
	public static final int ELEMENT_TYPE_TABLE = 1;
	
	public static final int ELEMENT_TYPE_TABLEROW = 2;
	
	public static final int ELEMENT_TYPE_TABLECELL = 3;
	
	public static final int ELEMENT_TYPE_PARAGRAPH = 4;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
