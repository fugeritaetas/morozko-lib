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
 * @(#)RestrictFilter.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.filter.restrict
 * @creation   : 24/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.filter.restrict;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.filter.HttpFilter;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class RestrictBySessionAttributeFilter extends HttpFilter {

	private String attributeName;
	
	private String allowPages;
	
	private String noAccessPage;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#destroy()
	 */
	public void destroy() {
		
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		Object obj = request.getSession().getAttribute( this.attributeName );
		String uri = request.getRequestURI().substring( request.getContextPath().length() );
		this.getLog().info( "uri     : "+uri );
		boolean forward = true;
		if ( obj == null && this.allowPages.indexOf( uri ) == -1 ) {
			forward = false;
		}
		this.getLog().info( "forward : "+forward );
		if ( forward ) {
			chain.doFilter( request, response );
		} else {
			RequestDispatcher rd = request.getRequestDispatcher( this.noAccessPage );
			rd.forward( request, response );
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.attributeName = config.getInitParameter( "attribute-name" );
		this.allowPages = config.getInitParameter( "allow-pages" );
		this.noAccessPage = config.getInitParameter( "no-access-page" );
	}

}
