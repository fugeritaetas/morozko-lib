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
 * @(#)ForwardFilter.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.filter
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.ent.servlet.context.InitFacade;
import org.morozko.java.core.ent.servlet.request.Forwarder;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ForwardFilter extends HttpFilter {

	private Forwarder forwarder;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#destroy()
	 */
	public void destroy() {
		this.forwarder = null;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			this.forwarder.handle( request , response );
		} catch (Exception e) {
			throw new ServletException( e );
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.filter.HttpFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		try {
			this.forwarder = Forwarder.newInstance( InitFacade.toProperties( config ) );
		} catch (ConfigException e) {
			new ServletException( e );
		}
	}

}
