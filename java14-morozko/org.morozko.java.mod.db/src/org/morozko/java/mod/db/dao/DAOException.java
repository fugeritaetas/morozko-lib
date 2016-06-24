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
 * @(#)DAOException.java
 *
 * @project	   : simoss
 * @package	   : it.finanze.secin.shared.dao
 * @creation   : 27-mag-2005 7.54.10
 */
package org.morozko.java.mod.db.dao;

import org.morozko.java.core.lang.BasicException;

/**
 * <p>.</p>
 *
 * @author tux2
 */
public class DAOException extends BasicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8459978395011496700L;

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * 
	 */
	public DAOException() {
		super();
	}

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * @param message
	 * @param t
	 */
	public DAOException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * <p>Crea una nuova istanza di DAOException.</p>
	 *
	 * @param t
	 */
	public DAOException(Throwable t) {
		super(t);
	}

}
