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
 * @(#)DocText.java
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
public class DocText extends DocElement {

	private int vAlign;
	
	private int hAlign;

	/**
	 * @return the hAlign
	 */
	public int getHAlign() {
		return hAlign;
	}

	/**
	 * @param align the hAlign to set
	 */
	public void setHAlign(int align) {
		hAlign = align;
	}

	/**
	 * @return the vAlign
	 */
	public int getVAlign() {
		return vAlign;
	}

	/**
	 * @param align the vAlign to set
	 */
	public void setVAlign(int align) {
		vAlign = align;
	} 
	
}
