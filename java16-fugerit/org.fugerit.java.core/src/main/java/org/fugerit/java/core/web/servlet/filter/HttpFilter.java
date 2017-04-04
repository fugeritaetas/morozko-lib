/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.ent 

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
 * @(#)HttpFilter.java
 *
 * @project	   : org.fugerit.java.core.ent
 * @package	   : org.fugerit.java.core.web.servlet.filter
 * @creation   : 12-lug-2005 8.28.19
 */
package org.fugerit.java.core.web.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fugerit.java.core.web.log.helpers.LogObjectFilter;

public abstract class HttpFilter extends LogObjectFilter {

	public abstract void init(FilterConfig config) throws ServletException;

	public abstract void doFilter( HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if ( request instanceof HttpServletRequest && response instanceof HttpServletResponse ) {
			this.getLogger().debug( "Http request and response : doFilter http" );
			this.doFilter( (HttpServletRequest)request, (HttpServletResponse)response, chain );
		} else {
			this.getLogger().debug( "Not http request and response : proceeding with chain" );
			chain.doFilter( request, response );
		}
	}

	public abstract void destroy();

}
