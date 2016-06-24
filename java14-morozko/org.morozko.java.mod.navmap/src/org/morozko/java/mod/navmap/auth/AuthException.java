/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
 * @(#)AuthException.java
 *
 * @project	   : org.morozko.java.mod.navmap
 * @package	   : org.morozko.java.mod.navmap.auth
 * @creation   : 29-ago-2005 11.31.32
 */
/**
 * 
 */
package org.morozko.java.mod.navmap.auth;

import org.morozko.java.core.lang.BasicException;

/**
 * <p>.</p>
 *
 * @author Matteo Franci aka TUX2
 */
public class AuthException extends BasicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7106104092518071576L;

	/**
	 * <p>Crea una nuova istanza di AuthException.</p>
	 *
	 */
	public AuthException() {
		super();
	}

	/**
	 * <p>Crea una nuova istanza di AuthException.</p>
	 *
	 * @param message
	 */
	public AuthException(String message) {
		super(message);
	}

	/**
	 * <p>Crea una nuova istanza di AuthException.</p>
	 *
	 * @param message
	 * @param cause
	 */
	public AuthException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <p>Crea una nuova istanza di AuthException.</p>
	 *
	 * @param cause
	 */
	public AuthException(Throwable cause) {
		super(cause);
	}

}
