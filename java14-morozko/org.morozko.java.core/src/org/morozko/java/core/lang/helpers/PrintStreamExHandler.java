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
 * @(#)PrintStreamExHandler.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.lang.helpers
 * @creation	: 16-feb-2005 12.29.24
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang.helpers;

import java.io.PrintStream;

import org.morozko.java.core.lang.ExHandler;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class PrintStreamExHandler implements ExHandler {

    public static final ExHandler out = new PrintStreamExHandler(System.out);
    
    public static final ExHandler err = new PrintStreamExHandler(System.err);
    
    private PrintStream stream;
    
    /**
     * <p>Crea un nuovo PrintStreamExHandler</p>
     * 
     * 
     */
    public PrintStreamExHandler(PrintStream stream) {
        super();    
        this.stream = stream;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.ExHandler#fatal(java.lang.Exception)
     */
    public void fatal(Exception e) {
        this.stream.println("Fatal error : "+e);
        e.printStackTrace(this.stream);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.ExHandler#error(java.lang.Exception)
     */
    public void error(Exception e) {
        this.stream.println("Error : "+e);
        e.printStackTrace(this.stream);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.ExHandler#warning(java.lang.Exception)
     */
    public void warning(Exception e) {
        this.stream.println("Warning : "+e);
        e.printStackTrace(this.stream);
    }

}
