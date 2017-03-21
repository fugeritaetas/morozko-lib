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
 * @(#)DOMUtils.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.xml.dom
 * @creation	: 13/lug/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.xml.dom;

import java.util.Properties;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class DOMUtils {

	   public static Properties attributesToProperties(Element tag) {
	        Properties props = new Properties();
	        NamedNodeMap atts = tag.getAttributes();
	        for (int k=0; k<atts.getLength(); k++) {
	            Attr a = (Attr)atts.item(k);
	            props.setProperty(a.getName(), a.getValue());
	        }
	        return props;
	    }
	    

	    private DOMUtils() {
	        super();
	    }	
	
}
