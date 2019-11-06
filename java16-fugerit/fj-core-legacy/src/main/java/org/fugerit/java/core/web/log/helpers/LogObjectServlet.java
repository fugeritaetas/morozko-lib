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
 * @(#)LogObjectServlet.java
 *
 * @project    : org.fugerit.java.core.ent
 * @package    : org.fugerit.java.core.web.log.helpers
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.web.log.helpers;

import javax.servlet.http.HttpServlet;

import org.fugerit.java.core.log.LogFacade;
import org.fugerit.java.core.log.LogObject;
import org.slf4j.Logger;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class LogObjectServlet extends HttpServlet implements LogObject {

	public void logInit( String message ) {
		this.getLogger().info( "[INIT]"+message );
	}	
	
	public LogObjectServlet() {
		this.log = LogFacade.newLogger( this );
	}
	
	private Logger log;

	public Logger getLogger() {
		return this.log;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8122658652733496521L;

}
