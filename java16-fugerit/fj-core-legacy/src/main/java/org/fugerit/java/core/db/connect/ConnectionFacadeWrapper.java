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
 * @(#)ConnectionFacadeWrapper.java
 *
 * @project     : org.fugerit.java.core.db
 * @package     : org.fugerit.java.core.db.connect
 * @creation	: 21/nov/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.db.connect;

import java.sql.Connection;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.metadata.DataBaseInfo;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class ConnectionFacadeWrapper implements ConnectionFactory {

	private ConnectionFactory wrapperConnectionFactory;
	
	public ConnectionFacadeWrapper( ConnectionFactory wrapperConnectionFactory ) {
		this.wrapperConnectionFactory = wrapperConnectionFactory;
	}
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.connect.ConnectionFactoryImpl#getDataBaseInfo()
	 */
	public DataBaseInfo getDataBaseInfo() throws DAOException {
		return this.getWrapperConnectionFactory().getDataBaseInfo();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.connect.ConnectionFactoryImpl#release()
	 */
	public void release() throws DAOException {
		this.getWrapperConnectionFactory().release();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.connect.ConnectionFactoryImpl#getConnection()
	 */
	public Connection getConnection() throws DAOException {
		return this.getWrapperConnectionFactory().getConnection();
	}

	/**
	 * @return the wrapperConnectionFactory
	 */
	public ConnectionFactory getWrapperConnectionFactory() {
		return wrapperConnectionFactory;
	}

}
