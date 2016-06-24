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
 * @(#)AuthHandler.java
 *
 * @project	   : org.morozko.java.mod.navmap
 * @package	   : org.morozko.java.mod.navmap.auth
 * @creation   : 29-ago-2005 11.30.23
 */
package org.morozko.java.mod.navmap.auth;

import javax.servlet.http.HttpServletRequest;

public interface AuthHandler {

	public static final String ATT_NAME = "org.morozko.java.mod.navmap.auth.AuthHandler";
	
	public static int STATE_AUTHORIZED = 0;
	
	public static int STATE_UNAUTHORIZED = 1;
	
	public static int STATE_HIDDEN = 2;
	
	public boolean checkAuthorized( HttpServletRequest request, String resource ) throws AuthException;
	
	public boolean checkUnauthorized( HttpServletRequest request, String resource )  throws AuthException;
	
	public boolean checkHidden( HttpServletRequest request, String resource )  throws AuthException;
	
	public int checkAuth( HttpServletRequest request, String resource )  throws AuthException;
	
	public void init() throws AuthException;
	
}
