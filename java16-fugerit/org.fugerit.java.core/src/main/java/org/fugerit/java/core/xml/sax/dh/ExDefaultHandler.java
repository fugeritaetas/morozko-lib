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
 * @(#)ExDefaultHandler.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.fugerit.java.core.xml.sax.dh
 * @creation	: 15-dic-2004 16.25.48
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.sax.dh;

import org.fugerit.java.core.lang.helpers.ExHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class ExDefaultHandler extends DefaultHandler {

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
    
    public ExDefaultHandler(ExHandler handler) {
        this.handler = handler;
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
    
}
