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
 * @(#)ErrorHandlerWrapper.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.fugerit.java.core.xml.sax.eh
 * @creation	: 15-dic-2004 23.10.51
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.sax.eh;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class ErrorHandlerWrapper implements ErrorHandler {
    
    /**
     * <p>Crea un nuovo ErrorHandlerWrapper</p>
     * 
     * 
     */
    public ErrorHandlerWrapper(ErrorHandler handler) {
        super();
        this.wrappedErrorHandler = handler;
    }
    
    private ErrorHandler wrappedErrorHandler;

    /* (non-Javadoc)
     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException arg0) throws SAXException {
        this.getWrappedErrorHandler().error(arg0);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
     */
    public void fatalError(SAXParseException arg0) throws SAXException {
        this.getWrappedErrorHandler().fatalError(arg0);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException arg0) throws SAXException {
        this.getWrappedErrorHandler().warning(arg0);
    }

    /**
     * <p>Restituisce il valore di wrappedErrorHandler.</p>
     * 
     * @return il valore di wrappedErrorHandler.
     */
    public ErrorHandler getWrappedErrorHandler() {
        return wrappedErrorHandler;
    }
    
    /**
     * <p>Imposta wrappedErrorHandler.</p>
     * 
     * @param wrappedErrorHandler il wrappedErrorHandler da impostare.
     */
    public void setWrappedErrorHandler(ErrorHandler wrappedHandler) {
        this.wrappedErrorHandler = wrappedHandler;
    }
    
}
