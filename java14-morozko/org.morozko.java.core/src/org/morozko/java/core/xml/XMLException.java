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
 * @(#)XMLException.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.xml
 * @creation	: 13/lug/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.xml;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class XMLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5111578010834483982L;

	/**
     * <p>Crea un nuovo XMLException</p>
     * 
     * 
     */
    public XMLException() {
        super();
    }

    /**
     * <p>Crea un nuovo XMLException</p>
     * 
     * @param message
     */
    public XMLException(String message) {
        super(message);
    }

    /**
     * <p>Crea un nuovo XMLException</p>
     * 
     * @param message
     * @param cause
     */
    public XMLException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Crea un nuovo XMLException</p>
     * 
     * @param cause
     */
    public XMLException(Throwable cause) {
        super(cause);
    }


}
