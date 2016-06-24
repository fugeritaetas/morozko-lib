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
 * @(#)LoggerWrapper.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.log
 * @creation   : 27/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.log;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author Morozko
 *
 */
public class LoggerWrapper implements Logger {

	public LoggerWrapper( Logger logger ) {
		this.wrapped = logger;
	}
	
	private Logger wrapped;
	
	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo wrapped.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of wrapped.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo wrapped.</jdl:text>
	 *         		<jdl:text lang='en'>the value of wrapped.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public Logger getWrapped() {
		return wrapped;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo wrapped.</jdl:text>
	 * 		<jdl:text lang='en'>Sets wrapped.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>wrapped il valore di wrapped da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>wrapped the wrapped to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setWrapped(Logger wrapped) {
		this.wrapped = wrapped;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object obj, Throwable t) {
		
		this.getWrapped().debug(obj, t);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#debug(java.lang.Object)
	 */
	public void debug(Object obj) {
		
		this.getWrapped().debug(obj);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object obj, Throwable t) {
		
		this.getWrapped().error(obj, t);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#error(java.lang.Object)
	 */
	public void error(Object obj) {
		
		this.getWrapped().error(obj);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object obj, Throwable t) {
		
		this.getWrapped().fatal(obj, t);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#fatal(java.lang.Object)
	 */
	public void fatal(Object obj) {
		
		this.getWrapped().fatal(obj);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object obj, Throwable t) {
		
		this.getWrapped().info(obj, t);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#info(java.lang.Object)
	 */
	public void info(Object obj) {
		
		this.getWrapped().info(obj);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object obj, Throwable t) {
		
		this.getWrapped().warn(obj, t);
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.DefaultLogger#warn(java.lang.Object)
	 */
	public void warn(Object obj) {
		
		this.getWrapped().warn(obj);
	} 
	
	
	
}
