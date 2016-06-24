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
 * @(#)ConfigurableObject.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.cfg
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.cfg;

import java.io.InputStream;
import java.util.Properties;

import org.w3c.dom.Element;

/**
 * <p>A class implementing ConfigurableObject interface is an object which
 *  should be configured through an XML Document or a Properties object.</p>
 *
 * @author Morozko
 *
 */
public interface ConfigurableObject {

	/**
	 * <p>Configure the object.</p>
	 * 
	 * @param source				The input source to use for configuration.
	 * @throws ConfigException		If troubles arise during object configuration.
	 */
	public void configureProperties( InputStream source ) throws ConfigException;	
	
	/**
	 * <p>Configure the object.</p>
	 * 
	 * @param source				The input source to use for configuration.
	 * @throws ConfigException		If troubles arise during object configuration.
	 */
	public void configureXML( InputStream source ) throws ConfigException;
	
	/**
	 * <p>Configure the object.</p>
	 * 
	 * @param source				The property object to use for configuration.
	 * @throws ConfigException		If troubles arise during object configuration.
	 */
	public void configure( Properties props ) throws ConfigException;
	
	/**
	 * <p>Configure the object.</p>
	 * 
	 * @param source				The tag object to use for configuration.
	 * @throws ConfigException		If troubles arise during object configuration.
	 */
	public void configure( Element tag ) throws ConfigException;
	
}
