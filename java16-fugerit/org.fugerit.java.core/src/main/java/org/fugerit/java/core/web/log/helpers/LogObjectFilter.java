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
 * @(#)LogObjectFilter.java
 *
 * @project	   : org.fugerit.java.core.ent
 * @package	   : org.fugerit.java.core.web.log.helpers
 * @creation   : 12-lug-2005 8.27.32
 */
package org.fugerit.java.core.web.log.helpers;

import javax.servlet.Filter;

import org.fugerit.java.core.log.BasicLogObject;

public abstract class LogObjectFilter extends BasicLogObject implements Filter {

	public void logInit( String param, String value ) {
		this.logInit( param+" : '"+value+"'" );
	}
	
	public void logInit( String message ) {
		this.getLogger().info( "[INIT]"+message );
	}
	
}
