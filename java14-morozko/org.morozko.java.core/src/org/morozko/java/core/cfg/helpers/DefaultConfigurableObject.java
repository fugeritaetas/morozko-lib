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
 * @(#)DefaultConfigurableObject.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.cfg.helpers
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.cfg.helpers;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.ConfigurableObject;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.w3c.dom.Element;

/**
 * <p>Default implementation of <code>ConfigurableObject</code>,
 * <code>configure(Properties)</code> method is a donothing, while
 * <code>configure(Element)</code> calls <code>configure(Properties)</code>
 * with the Element attributes transformed in a Properties object.</p>
 *
 * @author Morozko
 *
 */
public class DefaultConfigurableObject extends AbstractConfigurableObject implements ConfigurableObject {

	/**
	 * <p>Convers an XML Tag in a Properties object.</p> 
	 * 
	 * @param tag
	 * @return
	 */
	public static Properties defaultConversion( Element tag ) {
		return DOMUtils.attributesToProperties( tag );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {

	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public void configure(Element tag) throws ConfigException {
		configure( defaultConversion( tag ) );
	}

}
