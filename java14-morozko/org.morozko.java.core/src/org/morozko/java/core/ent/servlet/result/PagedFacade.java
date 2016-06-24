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
 * @(#)PagedFacade.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.ent.servlet.result
 * @creation	: 10/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.ent.servlet.result;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class PagedFacade {

	private static final String PARAM_CURRENT_PAGE_NUMBER = "currentPageNumber";
	
	private static final String ATT_CURRENT_PAGE = "currentPage";
	
	public static void prepare( HttpServletRequest request, List list, int perPage ) {
		String currentPage = request.getParameter( PARAM_CURRENT_PAGE_NUMBER );
		int page = 1;
		if ( currentPage != null ) {
			page = Integer.parseInt( currentPage );
		}
		PagedList pagina = new PagedList( list, page, perPage );
		request.setAttribute( ATT_CURRENT_PAGE, pagina );
	}
	
}
