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
 * @(#)EntityResolverWrapper.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.fugerit.java.core.xml.sax.er
 * @creation	: 15-dic-2004 23.13.40
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.sax.er;

import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class EntityResolverWrapper implements EntityResolver {

    /**
     * <p>Crea un nuovo EntityResolverWrapper</p>
     * 
     * 
     */
    public EntityResolverWrapper(EntityResolver resolver) {
        super();
        this.wrappedEntityResolver = resolver;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
     */
    public InputSource resolveEntity(String arg0, String arg1) throws SAXException, IOException {
        return this.getWrappedEntityResolver().resolveEntity(arg0, arg1);
    }
    
    private EntityResolver wrappedEntityResolver;

    /**
     * <p>Restituisce il valore di wrappedEntityResolver.</p>
     * 
     * @return il valore di wrappedEntityResolver.
     */
    public EntityResolver getWrappedEntityResolver() {
        return wrappedEntityResolver;
    }
    
    /**
     * <p>Imposta wrappedEntityResolver.</p>
     * 
     * @param wrappedEntityResolver il wrappedEntityResolver da impostare.
     */
    public void setWrappedEntityResolver(EntityResolver wrappedEntityResolver) {
        this.wrappedEntityResolver = wrappedEntityResolver;
    }
    
}
