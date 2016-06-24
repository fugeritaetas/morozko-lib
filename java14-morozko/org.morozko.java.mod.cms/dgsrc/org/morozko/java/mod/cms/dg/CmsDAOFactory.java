/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.app.cms 

	Copyright (c) 2006 OpenInformatica

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
 * @(#)CmsDAOFactory.java
 *
 * @project    : jlibWeb
 * @package    : org.morozko.java.mod.cms.dg
 * @creation   : 19-mag-2006
 */
package org.morozko.java.mod.cms.dg;

import org.morozko.java.mod.cms.dg.core.dao.CmsCoreDAOFactory;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.db.dao.FieldFactory;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsDAOFactory extends BasicDAOFactory {

	public static void init( ConnectionFactory cf ) {
		instance = new CmsDAOFactory( cf, new FieldFactory() );
	}
	
	private static CmsDAOFactory instance = null;
	
	public static CmsDAOFactory getInstance() {
		return instance;
	}
	
	private CmsCoreDAOFactory cmsCoreDAOFactory;
	
	/**
	 * @return Restituisce il valore di cmsCoreDAOFactory.
	 */
	public CmsCoreDAOFactory getCmsCoreDAOFactory() {
		return cmsCoreDAOFactory;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	private CmsDAOFactory(ConnectionFactory connectionFactory, FieldFactory fieldFactory) {
		super(connectionFactory, fieldFactory);
		this.cmsCoreDAOFactory = new CmsCoreDAOFactory( this );
	}
	
	public static DAOID generateId() throws DAOException {
		return getInstance().getCmsCoreDAOFactory().getCmsPageDAO().generateId();
	}

}
