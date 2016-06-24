/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
 * @(#)ActionContext.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap.context
 * @creation   : 24-nov-2005 14.50.41
 */
package org.morozko.java.mod.navmap.context;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Sacca'
 *
 */
public class ActionContext {

	public static ActionContext getInstance( HttpServletRequest request ) {
		return new ActionContext( request );
	}
	
	public void setAttribute( String name, Object value ) {
		this.getRequest().getSession().setAttribute( name, value );
	}
	
	public Object getAttribute( String name ) {
		return getRequest().getSession().getAttribute( name );
	}
	
	private ActionContext( HttpServletRequest request ) {
		this.request = request;
	}
	
	private HttpServletRequest request;

	/**
	 * @return Restituisce il valore di request.
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	
}
