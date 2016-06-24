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
 * @(#)PropertiesConfigurableObject.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.cfg.helpers
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.cfg.helpers;

import org.morozko.java.core.cfg.ConfigException;
import org.w3c.dom.Element;

/**
 * <p>Base class for implementations of ConfigurableObject supporting only
 * the <code>configure(Properties)</code> method.
 * The <code>configure(Element)</code> method will throw an exception.</p>
 *
 * @author Morozko
 *
 */
public abstract class PropertiesConfigurableObject  extends AbstractConfigurableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2001953242749817211L;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public void configure(Element tag) throws ConfigException {
		throw ( new ConfigException( "Properties configuration not supported" ) );
	}

}
