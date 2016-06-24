/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)WebDAOFactory.java
 *
 * @project     : org.morozko.java.mod.web
 * @package     : org.morozko.java.mod.web.dg
 * @creation	: 03/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.web.dg;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.FieldFactory;
import org.morozko.java.mod.web.dg.core.dao.WebCoreDAOFactory;
import org.morozko.java.mod.web.dg.user.dao.UserDAOFactory;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class WebDAOFactory extends BasicDAOFactory {

	public static void init( ConnectionFactory cf ) {
		instance = new WebDAOFactory( cf );
	}
	
	private static WebDAOFactory instance;
	
	private UserDAOFactory userDAOFactory;
	
	public UserDAOFactory getUserDAOFactory() {
		return userDAOFactory;
	}

	private WebCoreDAOFactory webCoreDAOFactory;
	
	private WebDAOFactory(ConnectionFactory cf) {
		super(cf, new FieldFactory());
		this.webCoreDAOFactory = new WebCoreDAOFactory( this );
		this.userDAOFactory = new UserDAOFactory( this );
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo webCoreDAOFactory.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of webCoreDAOFactory.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo webCoreDAOFactory.</jdl:text>
	 *         		<jdl:text lang='en'>the value of webCoreDAOFactory.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public WebCoreDAOFactory getWebCoreDAOFactory() {
		return webCoreDAOFactory;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo instance.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of instance.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo instance.</jdl:text>
	 *         		<jdl:text lang='en'>the value of instance.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public static WebDAOFactory getInstance() {
		return instance;
	}
	
	
}
