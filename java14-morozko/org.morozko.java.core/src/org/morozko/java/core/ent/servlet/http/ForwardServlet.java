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
 * @(#)ForwardServlet.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.http
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.http;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.ent.log.helpers.LogObjectServlet;
import org.morozko.java.core.ent.servlet.context.InitFacade;
import org.morozko.java.core.ent.servlet.request.Forwarder;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ForwardServlet extends LogObjectServlet {

	private Forwarder forwarder;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		try {
			this.forwarder.handle( request , response );
		} catch (Exception e) {
			throw new ServletException( e );
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6576018908501573268L;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		this.forwarder = null ;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			this.forwarder = Forwarder.newInstance( InitFacade.toProperties( config ) );
		} catch (ConfigException e) {
			new ServletException( e );
		}		
	}

}
