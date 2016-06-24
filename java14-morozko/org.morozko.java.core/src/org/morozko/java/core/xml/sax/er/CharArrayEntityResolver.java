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
 * @(#)CharArrayEntityResolver.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.morozko.java.core.xml.sax.er
 * @creation	: 15-dic-2004 23.52.00
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.xml.sax.er;

import java.io.CharArrayReader;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CharArrayEntityResolver implements EntityResolver {

    private String publicID;
    private String systemID;
    private char[] entity;
    
    /**
     * <p>Crea un nuovo CharArrayEntityResolver</p>
     * 
     * 
     */
    public CharArrayEntityResolver(char[] entity, String publicID, String systemID ) {
        super();
        this.publicID = publicID;
        this.systemID = systemID;
        this.entity = entity;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
     */
    public InputSource resolveEntity(String arg0, String arg1)
            throws SAXException, IOException {
        InputSource source = null;
        if ((arg0!=null && arg0.equals(this.publicID)) || (arg1!=null && arg1.equals(this.systemID))) {
            source = new InputSource(new CharArrayReader(this.entity));
        }
        return source;
    }

}
