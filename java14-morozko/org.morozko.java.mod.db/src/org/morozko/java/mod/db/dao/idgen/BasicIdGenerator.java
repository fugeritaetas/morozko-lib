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
 * @(#)BasicIdGenerator.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.idgen
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.dao.idgen;

import java.io.InputStream;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.db.dao.IdGenerator;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public abstract class BasicIdGenerator extends BasicLogObject implements IdGenerator {
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.cfg.ConfigurableObject#configureProperties(java.io.InputStream)
	 */
	public void configureProperties(InputStream source) throws ConfigException {
		
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.cfg.ConfigurableObject#configureXML(java.io.InputStream)
	 */
	public void configureXML(InputStream source) throws ConfigException {
		
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public void configure(Element tag) throws ConfigException {
		
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.cfg.ConfigurableObject#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.dao.IdGenerator#generateID()
	 */
	public abstract DAOID generateId() throws DAOException;
	
	private ConnectionFactory connectionFactory;

	/**
	 * @return the connectionFactory
	 */
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * @param connectionFactory the connectionFactory to set
	 */
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

}
