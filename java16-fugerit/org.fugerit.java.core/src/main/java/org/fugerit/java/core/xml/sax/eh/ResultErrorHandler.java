/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.std 

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
 * @(#)ResultErrorHandler.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.fugerit.java.core.xml.sax.eh
 * @creation	: 16-dic-2004 0.18.01
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.sax.eh;

import org.fugerit.java.core.lang.helpers.ExHandler;
import org.fugerit.java.core.lang.helpers.Result;
import org.fugerit.java.core.lang.helpers.ResultExHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class ResultErrorHandler implements ErrorHandler {
    
    /**
     * <p>Crea un nuovo ResultErrorHandler</p>
     * 
     * 
     */
    public ResultErrorHandler(Result res) {
        super();
        this.handler = new ResultExHandler(res);
    }

    /**
     * <p>Restituisce il valore di handler.</p>
     * 
     * @return il valore di handler.
     */
    public ExHandler getHandler() {
        return handler;
    }
    
    /**
     * <p>Imposta handler.</p>
     * 
     * @param handler il handler da impostare.
     */
    public void setHandler(ExHandler handler) {
        this.handler = handler;
    }
    

    /* (non-Javadoc)
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException arg0) throws SAXException {
        this.handler.error(arg0);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    public void fatalError(SAXParseException arg0) throws SAXException {
        this.handler.fatal(arg0);
    }
    
    /* (non-Javadoc)
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException arg0) throws SAXException {
        this.handler.warning(arg0);
    }
    
    private ExHandler handler;    
    
}
