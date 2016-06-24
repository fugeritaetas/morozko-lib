/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)PagedList.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.servlet.result
 * @creation	: 10/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.servlet.result;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class PagedList extends ArrayList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8342423845302781886L;

	private List pages;

	private int numeroPagine;

	public PagedList( List list, int current, int perPage ) {
		int start = (current-1)*perPage;
		int end = Math.min( start+perPage, list.size() );
		this.addAll( list.subList( start, end ) );
		boolean add = ( list.size()%perPage )>0;
		if ( add ) {
			this.numeroPagine = list.size()/perPage+1;	
		} else {
			this.numeroPagine = list.size()/perPage;
		}
		this.pages = new ArrayList();
		for ( int k=1; k<=this.numeroPagine; k++ ) {
			pages.add( String.valueOf( k ) );
		}
	}

	/**
	 * @return
	 */
	public List getPages() {
		return pages;
	}
	
	

	/**
	 * @return
	 */
	public int getNumeroPagine() {
		return numeroPagine;
	}
	
}
