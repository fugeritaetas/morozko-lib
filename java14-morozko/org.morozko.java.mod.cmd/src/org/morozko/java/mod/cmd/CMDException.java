/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.cmd 

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
 * @(#)CMDException.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd
 * @creation	: 21-dic-2004 15.33.16
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd;

import org.morozko.java.core.lang.BasicException;

/**
 * <p>Eccezione per la gestione di errori in un CMD.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CMDException extends BasicException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7813206556938025871L;

	/**
     * <p>Crea un nuovo CMDException</p>
     * 
     * 
     */
    public CMDException() {
        super();
    }

    /**
     * <p>Crea un nuovo CMDException</p>
     * 
     * @param message
     */
    public CMDException(String message) {
        super(message);
    }

    /**
     * <p>Crea un nuovo CMDException</p>
     * 
     * @param message
     * @param cause
     */
    public CMDException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Crea un nuovo CMDException</p>
     * 
     * @param cause
     */
    public CMDException(Throwable cause) {
        super(cause);
    }

}
