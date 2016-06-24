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
package org.morozko.java.mod.navmap.auth;

import javax.servlet.http.HttpServletRequest;

public class AllowAuthHandler extends BasicAuthHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4327320395075504260L;

	public int checkAuth(HttpServletRequest request, String resource) throws AuthException {
		return AuthHandler.STATE_AUTHORIZED;
	}

}
