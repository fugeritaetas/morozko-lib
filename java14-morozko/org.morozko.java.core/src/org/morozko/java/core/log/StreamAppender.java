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
 * @(#)DefaultAppender.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.log
 * @creation   : 27/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.log;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
public class StreamAppender implements Appender {

	public static final Appender out = new StreamAppender( System.out );
	
	public static final Appender err = new StreamAppender( System.out );
	
	public StreamAppender( OutputStream stream ) {
		this.writer = new PrintWriter( new OutputStreamWriter( stream ), true );
	}
	
	private PrintWriter writer;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.Appender#write(java.lang.String, java.lang.Object)
	 */
	public void write( Object obj, int level ) {
		if ( obj instanceof Throwable ) {
			((Throwable)obj).printStackTrace( this.writer ); 
		} else {
			this.writer.println( String.valueOf( obj ) );
		}
	}

}
