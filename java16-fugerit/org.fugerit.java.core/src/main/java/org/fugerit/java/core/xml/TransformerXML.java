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
 * @(#)TransformerXML.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.xml
 * @creation	: 13/lug/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class TransformerXML {

    public static Transformer newTransformer(Source source) throws XMLException {
        Transformer transformer = null;
        try {
            transformer = 
                TransformerFactory.newInstance().newTransformer(source);
        } catch (TransformerConfigurationException tce) {
            throw new XMLException(tce);
        }
        return transformer;
    }

    public static Transformer newTransformer() throws XMLException {
        Transformer transformer = null;
        try {
            transformer = 
                TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException tce) {
            throw new XMLException(tce);
        }
        return transformer;
    }	
	
}
