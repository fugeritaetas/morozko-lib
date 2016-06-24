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
 * @(#)MapOutputPage.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.util.dirmap
 * @creation   : 3-mag-2006
 */
package org.morozko.java.core.ent.servlet.util.dirmap;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class MapOutputPage {

	private static final DirMapHandler DEF_HANDLER = new DefaultDirMapHandler();
	
	public static void outputPage( HttpServletRequest request, HttpServletResponse response, DirMapConfig dirMapConfig ) throws IOException {
		DEF_HANDLER.outputPage(request, response, dirMapConfig);
	}
	
}
