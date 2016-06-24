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
 * @(#)TypedProperties.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.util
 * @creation   : 24/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.util;

import java.sql.Date;
import java.util.Properties;

/**
 * <p>Estensione di <code>java.util.Properties</code> che permette di ottenere le proprietà
 * presenti nell' oggetto properties come tipi diversi dalle sole <code>java.lang.String</code>.</p>
 * 
 * 	<p>In particolare vengono supportati i seguenti tipi : 
 * 	<ul>
 * 		<li><code>java.lang.String</code> (il default)</li>
 * 		<li><code>java.lang.Integer</code></li>
 * 		<li><code>java.lang.Long</code></li>
 * 		<li><code>java.lang.Float</code></li>
 * 		<li><code>java.lang.Double</code></li>
 * 		<li><code>java.sql.Date</code></li>
 * 		<li><code>java.sql.Time</code></li>
 * 		<li><code>java.sql.Timestamp</code></li>
 *	</ul>
 * 	</p>
 * 
 * <p>E' responsabilità di chi usa questa libreria di assicurarsi che le proprità richieste in formato diverse
 * siano state correttamente immagazzinate come stringhe convertibili dal metodo <code>valueOf(java.lang.String)
 * dei tipi sopra elencati.</p>
 *
 * @author Morozko
 *
 */
public class TypedProperties extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1826093860204083565L;

	public Date getDateProperty( String key ) {
		Date d = null;
		String p = this.getProperty(key);
		if ( p!= null ) {
			d = Date.valueOf( p );
		}
		return d;
	}	
	
	public Integer getIntProperty( String key ) {
		Integer i = null;
		String p = this.getProperty(key);
		if ( p!= null ) {
			i = Integer.valueOf( p );
		}
		return i;
	}
	
}
