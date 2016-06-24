/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
 * @(#)DirectoryMapFilter.java
 *
 * @project  : org.morozko.java.core.ent
 * @package  : org.morozko.java.core.ent.servlet.filter
 * @creation : 8-feb-2006
 */
package org.morozko.java.core.ent.servlet.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.context.ContextHelper;
import org.morozko.java.core.ent.servlet.util.dirmap.DirMapConfig;
import org.morozko.java.core.ent.servlet.util.dirmap.MapOutputPage;
import org.morozko.java.core.xml.dom.DOMIO;
import org.w3c.dom.Document;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class DirectoryMapFilter extends HttpFilter {
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		try {
			File configFile = ContextHelper.resolvePath( config.getServletContext(), config.getInitParameter( "dir-map-config" ) );
			Document doc = DOMIO.loadDOMDoc( configFile );
			this.dirMapConfig = DirMapConfig.configure( doc );
		} catch (Exception e) {
			throw ( new ServletException( e ) );
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		MapOutputPage.outputPage( request, response, this.dirMapConfig );
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#destroy()
	 */
	public void destroy() {
		this.dirMapConfig = null;
	}
	
	private DirMapConfig dirMapConfig;

}
