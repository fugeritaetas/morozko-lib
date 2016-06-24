/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)SortException.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort
 * @creation	: 29-dic-2004 8.08.19
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort;

import org.morozko.java.core.lang.BasicException;

/**
 * <p>Eccezione per gestire problemi di ordinamento.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class SortException extends BasicException {

	private static final long serialVersionUID = -3660252304299415148L;

	/**
     * <p>Crea un nuovo SortException</p>
     * 
     * 
     */
    public SortException() {
        super();
    }

    /**
     * <p>Crea un nuovo SortException</p>
     * 
     * @param message	il messaggio di errore associato all' eccezione
     */
    public SortException(String message) {
        super(message);
    }

    /**
     * <p>Crea un nuovo SortException</p>
     * 
     * @param message	il messaggio di errore associato all' eccezione
     * @param cause		la causa dell' eccezione
     */
    public SortException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Crea un nuovo SortException</p>
     * 
     * @param cause		la causa dell' eccezione
     */
    public SortException(Throwable cause) {
        super(cause);
    }

}
