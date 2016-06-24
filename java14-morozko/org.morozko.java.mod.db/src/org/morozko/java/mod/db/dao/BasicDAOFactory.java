/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)BasicDAOFactory.java
 *
 * @project	   : simoss
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 27-mag-2005 19.26.09
 */
package org.morozko.java.mod.db.dao;


import java.sql.Connection;

import org.morozko.java.mod.db.connect.ConnectionFactory;

/**
 * <p>.</p>
 *
 * @author tux2
 */
public class BasicDAOFactory {

	private DAOUtils daoUtils;
	
	public Object[] sqlArgs = new Object[0];
	
    public Connection getConnection() throws DAOException {
        return this.connectionFactory.getConnection();
    }
    
    private ConnectionFactory connectionFactory;
    
    private FieldFactory fieldFactory;
    
    /**
     * <p>Crea una nuova istanza di BasicDAOFactory.</p>
     *
     * 
     */
    public BasicDAOFactory(ConnectionFactory cFactory) {
    	this( cFactory, new FieldFactory() );
    }    
    
    /**
     * <p>Crea una nuova istanza di BasicDAOFactory.</p>
     *
     * 
     */
    public BasicDAOFactory(ConnectionFactory cFactory, FieldFactory fFactory) {
        super();
        this.connectionFactory = cFactory;
        this.fieldFactory = fFactory;
        this.daoUtils = new DAOUtils( new GenericDAO( this ) );
    }

    /**
     * <p>Restituisce il valore di connectionFactory.</p>
     *
     * @return il valore di connectionFactory.
     */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
    
    /**
     * <p>Restituisce il valore di fieldFactory.</p>
     *
     * @return il valore di fieldFactory.
     */
    public FieldFactory getFieldFactory() {
        return fieldFactory;
    }

	/**
	 * @return the sqlArgs
	 */
	public Object[] getSqlArgs() {
		return sqlArgs;
	}

	/**
	 * @param sqlArgs the sqlArgs to set
	 */
	public void setSqlArgs(Object[] sqlArgs) {
		this.sqlArgs = sqlArgs;
	}

	public DAOUtils getDaoUtils() {
		return daoUtils;
	}

	
}
