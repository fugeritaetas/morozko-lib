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
 * @(#)LogObjectFilter.java
 *
 * @project	   : org.morozko.java.core.ent
 * @package	   : org.morozko.java.core.ent.log.helpers
 * @creation   : 12-lug-2005 8.27.32
 */
package org.morozko.java.core.ent.log.helpers;

import javax.servlet.Filter;

import org.morozko.java.core.log.BasicLogObject;

public abstract class LogObjectFilter extends BasicLogObject implements Filter {

	public void logInit( String param, String value ) {
		this.logInit( param+" : '"+value+"'" );
	}
	
	public void logInit( String message ) {
		this.getLog().info( "[INIT]"+message );
	}
	
}
