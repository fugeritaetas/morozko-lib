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
 * @(#)BasicAuthHandler.java
 *
 * @project	   : org.morozko.java.mod.navmap
 * @package	   : org.morozko.java.mod.navmap.auth
 * @creation   : 29-ago-2005 11.33.51
 */
package org.morozko.java.mod.navmap.auth;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.core.log.BasicLogObject;

public abstract class BasicAuthHandler extends BasicLogObject implements AuthHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1608345265683776633L;

	public BasicAuthHandler() {
		super();
	}

	public boolean checkAuthorized(HttpServletRequest request, String resource) throws AuthException {
		return this.checkAuth( request, resource )==STATE_AUTHORIZED;
	}

	public boolean checkUnauthorized(HttpServletRequest request, String resource) throws AuthException {
		return this.checkAuth( request, resource )==STATE_UNAUTHORIZED;
	}

	public boolean checkHidden(HttpServletRequest request, String resource) throws AuthException {
		return this.checkAuth( request, resource )==STATE_HIDDEN;
	}

	public abstract int checkAuth(HttpServletRequest request, String resource) throws AuthException;

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.auth.AuthHandler#init()
	 */
	public void init() throws AuthException {
		
	}

}
