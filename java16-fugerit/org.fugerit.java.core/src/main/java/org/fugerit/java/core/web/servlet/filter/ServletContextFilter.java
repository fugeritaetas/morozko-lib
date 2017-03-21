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
 * @(#)ServletContextFilter.java
 *
 * @project	   : org.fugerit.java.core.ent
 * @package	   : org.fugerit.java.core.web.servlet.filter
 * @creation   : 29-ago-2005 11.35.54
 */
package org.fugerit.java.core.web.servlet.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public abstract class ServletContextFilter extends HttpFilter {

	private ServletContext context;
	
	/**
	 * <p>Restituisce il valore di context.</p>
	 *
	 * @return il valore di context.
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * <p>Imposta il valore di context.</p>
	 *
	 * @param context il valore da impostare.
	 */
	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void init(FilterConfig config) throws ServletException {
		this.setContext( config.getServletContext() );
	}

	public void destroy() {
		this.setContext( null );
	}

}
