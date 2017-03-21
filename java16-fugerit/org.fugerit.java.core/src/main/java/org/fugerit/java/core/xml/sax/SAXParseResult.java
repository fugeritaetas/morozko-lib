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
 * @(#)SAXParseResult.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.fugerit.java.core.xml.sax
 * @creation	: 15-dic-2004 16.06.59
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.sax;

import org.fugerit.java.core.lang.helpers.Result;
import org.xml.sax.SAXParseException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class SAXParseResult extends Result {

    /**
     * <p>Crea un nuovo SAXParseResult</p>
     * 
     * 
     */
    public SAXParseResult() {
        super();
    }

    public SAXParseException getSAXError(int index) {
        return (SAXParseException)this.getError(index);
    }
    
    public SAXParseException getSAXFatal(int index) {
        return (SAXParseException)this.getFatal(index);
    }    
    
    public SAXParseException getSAXWarning(int index) {
        return (SAXParseException)this.getWarning(index);
    }

}
