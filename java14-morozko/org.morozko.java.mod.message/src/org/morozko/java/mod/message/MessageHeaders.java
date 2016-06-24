/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.message 

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
 * @(#)MessageHeaders.java
 *
 * @project    : org.morozko.java.mod.message
 * @package    : org.morozko.java.mod.message
 * @creation   : 5-giu-2006
 */
package org.morozko.java.mod.message;

import java.util.Enumeration;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class MessageHeaders {

	public static MessageHeaders fromProperties( Properties headers ) {
		MessageHeaders messageHeaders = new MessageHeaders();
		messageHeaders.headers = headers; 
		return messageHeaders;
	}
	
	public MessageHeaders() {
		this.headers = new Properties();
	}
	
	private Properties headers;
	
	public Enumeration getHeaderNames() {
		return this.headers.keys();
	}
	
	public void addHeader( String name, String value ) {
		this.headers.setProperty( name, value );
	}
	
	public String getHeader( String name ) {
		return this.headers.getProperty( name );
	}
	
}
