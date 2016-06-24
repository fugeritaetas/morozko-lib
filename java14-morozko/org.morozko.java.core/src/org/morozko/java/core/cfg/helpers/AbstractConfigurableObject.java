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
 * @(#)AbstractConfigurableObject.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.cfg.helpers
 * @creation   : 11/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.cfg.helpers;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.ConfigurableObject;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.core.xml.dom.DOMIO;
import org.w3c.dom.Element;

/**
 * <p>Abstract implementation of ConfigurableObject interface.
 * 	Subclasses must only implement configure(Properties) and configure(Document).</p>
 *
 * @author Morozko
 *
 */
public abstract class AbstractConfigurableObject extends BasicLogObject implements ConfigurableObject, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5893401779153563982L;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configure(java.util.Properties)
	 */
	public abstract void configure(Properties props) throws ConfigException;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configure(org.w3c.dom.Element)
	 */
	public abstract void configure(Element tag) throws ConfigException;

	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configureProperties(java.io.InputStream)
	 */
	public void configureProperties(InputStream source) throws ConfigException {
		try {
			this.configure( PropsIO.loadFromStream( source ) );
		} catch (Exception e) {
			throw new ConfigException( e );
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.cfg.ConfigurableObject#configureXML(java.io.InputStream)
	 */
	public void configureXML(InputStream source) throws ConfigException {
		try {
			this.configure( DOMIO.loadDOMDoc( source ).getDocumentElement() );
		} catch (Exception e) {
			throw new ConfigException( e );
		}
	}

}
