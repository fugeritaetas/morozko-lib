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
 * @(#)NavHandler.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap
 * @creation   : 7-giu-2006
 */
package org.morozko.java.mod.navmap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public interface NavHandler {

	public static final int CURRENT_HANDLER_VERSION = 1;
	
	public int getHandlerVersion();
	
	public boolean isCurrent( NavEntry navEntry, HttpServletRequest request ) throws Exception;
	
	/**
	 * <p>Gestisce</p>
	 * 
	 * @param navNode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public NavNode handleNavNode( NavNode navNode, HttpServletRequest request, HttpServletResponse response ) throws Exception;
	
}
