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
 * @(#)DocContainer.java
 *
 * @project    : org.fugerit.java.core.doc
 * @package    : org.fugerit.java.core.doc
 * @creation   : 06/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.doc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DocContainer extends DocElement {

	private List<DocElement> elementList;
	
	public DocContainer() {
		this.elementList = new ArrayList<DocElement>();
	}
	
	public Iterator<DocElement> docElements() {
		return this.elementList.iterator();
	}
	
	public int containerSize() {
		return this.elementList.size();
	}
	
	public void addElement( DocElement docElement ) {
		this.elementList.add( docElement );
	}
	
}
