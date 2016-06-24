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
 * @(#)ConfigException.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.xml.cfg
 * @creation   : 6-ott-2005 12.46.53
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.cfg;

/**
 * <p>Exception for handling unexpected situations during configuration.</p>
 *
 * @author Morozko
 *
 */
public class ConfigException extends Exception {

	private int code;	// error code
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4205246051306630691L;

	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 */
	public ConfigException() {
		super();
	}


	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param message	the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 * @param cause		the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param message	the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 */
	public ConfigException(String message) {
		super(message);
	}

	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param cause		the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ConfigException(Throwable cause) {
		super(cause);
	}

	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param code		the error code
	 */
	public ConfigException(int code) {
		super();
		this.code = code;
	}

	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param message	the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 * @param code		the error code
	 */
	public ConfigException(String message, int code) {
		super(message);
		this.code = code;
	}

	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param cause		the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 * @param code		the error code
	 */
	public ConfigException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	/**
	 * <p>Creates a new <code>ConfigException</code></p>
	 * 
	 * @param message	the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 * @param cause		the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 * @param code		the error code
	 */
	public ConfigException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return super.toString()+"[code:"+this.code+"]";
	}

}
