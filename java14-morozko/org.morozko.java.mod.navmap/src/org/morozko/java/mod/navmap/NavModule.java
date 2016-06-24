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
 * @(#)NavModule.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap
 * @creation   : 19-giu-2006
 */
package org.morozko.java.mod.navmap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.InputSource;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public interface NavModule extends NavHandler {

	public static final int CURRENT_MODULE_VERSION = 1;
	
	public int getModuleVersion();

	public NavNode getNavNode( String module, String entry ); 
	
	public NavNode getNavNode( HttpServletRequest request, HttpServletResponse response, String module, String entry ) throws Exception;

	public NavMap getParent();
	
	public NavMap getNavMap();
	
	public void configure( InputSource source, NavMap parent, String name, String home ) throws Exception;
	
	public String getModuleName();
	
}
