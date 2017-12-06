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
 * @(#)Forwarder.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.request
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.request;

import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.PropertiesConfigurableObject;
import org.morozko.java.core.util.PropsFacade;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class Forwarder extends PropertiesConfigurableObject implements RequestHandler {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9097099290523039314L;

	public static Forwarder newInstance( String forwardTo ) throws ConfigException {
		return newInstance( PropsFacade.singleProp( PROP_FORWARDTO, forwardTo ));
	}	
	
	public static Forwarder newInstance( Properties props ) throws ConfigException {
		Forwarder forwarder = new Forwarder();
		forwarder.configure( props );
		return forwarder;
	}
	
	public static final String PROP_FORWARDTO = "forward-to";
	
	private String forwardTo;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		this.forwardTo = props.getProperty( PROP_FORWARDTO );
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.ent.servlet.request.RequestHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher( this.forwardTo );
		rd.forward(request, response);
	}
	
}
