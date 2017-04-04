/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

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
 * @(#)ConnectionFactory.java
 *
 * @project  : org.fugerit.java.core.db
 * @package  : org.fugerit.java.core.db.connect
 * @creation : 19-mar-2006
 */
package org.fugerit.java.core.db.connect;

import java.sql.Connection;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.metadata.DataBaseInfo;

/**
 * <p>Una implementazione di ConnectionFactory fornisce un metodo semplice
 * per accedere a delle Connessioni verso una specifica sorgente dati jdbc.</p>
 *
 * @author mfranci
 *
 */
public interface ConnectionFactory {
	
	/**
	 * Restituisce una serie di informazioni convernenti il database.
	 * 
	 * @return le info sul db
	 */
	public DataBaseInfo getDataBaseInfo() throws DAOException;
	
	/**
	 * <p>Metodo che rilascia la ConnectionFactory ed ogni risorsa
	 * eventualmente impeganta da essa.</p>
	 * 
	 * @throws DAOException
	 */
	public void release() throws DAOException;
	
	/**
	 * <p>Metodo che restituisce una java.sql.Connection secondo
	 * le regole definite per questa ConnectionFactory.</p>
	 * 
	 * @return
	 * @throws DAOException
	 */
	public Connection getConnection() throws DAOException;
	
}
