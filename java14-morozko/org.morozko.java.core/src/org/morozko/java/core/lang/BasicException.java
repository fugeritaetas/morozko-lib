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
 * @(#)BasicException.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.lang
 * @creation	: 3-dic-2004 15.29.55
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang;


/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 *      Eccezione base per tutte le eccezioni di queste librerie. 
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		Superclass for every exception in this library.
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class BasicException extends Exception {

	private static final long serialVersionUID = 1000452312341414241L;
	
	/**
	 * <jdl:section>
	 * 		<jdl:text lang='it'><p>Crea una nuova istanza di BasicException.</p></jdl:text>
	 * 		<jdl:text lang='en'><p>Creates a new instance of BasicException.</p></jdl:text>
	 * </jdl:section>
	 *
	 */
    public BasicException() {
        super();
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di BasicException.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of BasicException.</p></jdl:text>
     * </jdl:section>
     *
     * @param message
     */
    public BasicException(String message) {
        super(message);
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di BasicException.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of BasicException.</p></jdl:text>
     * </jdl:section>
     *
     * @param message
     * @param cause
     */
    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * <jdl:section>
     * 		<jdl:text lang='it'><p>Crea una nuova istanza di BasicException.</p></jdl:text>
     * 		<jdl:text lang='en'><p>Creates a new instance of BasicException.</p></jdl:text>
     * </jdl:section>
     *
     * @param cause
     */
    public BasicException(Throwable cause) {
        super(cause);
    }
    
}
