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
 * @(#)DoNothingDTDHandler.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.fugerit.java.core.xml.sax.dtd
 * @creation	: 15-dic-2004 23.22.48
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.sax.dtd;

import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class DoNothingDTDHandler implements DTDHandler {

    /**
     * <p>Crea un nuovo DoNothingDTDHandler</p>
     * 
     * 
     */
    public DoNothingDTDHandler() {
        super();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.DTDHandler#notationDecl(java.lang.String, java.lang.String, java.lang.String)
     */
    public void notationDecl(String arg0, String arg1, String arg2)
            throws SAXException {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.DTDHandler#unparsedEntityDecl(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void unparsedEntityDecl(String arg0, String arg1, String arg2,
            String arg3) throws SAXException {
    }

}
