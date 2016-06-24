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
 * @(#)ObjectFormatFactory.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.text.format
 * @creation   : 22/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.text.format;

import java.sql.Date;
import java.sql.Time;
import java.text.MessageFormat;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class ObjectFormatFactory {

	public static final ObjectFormat DEFAULT_OBJECT_FORMAT = new DefaultObjectFormat();
	
	public static String format( Object obj ) {
		return DEFAULT_OBJECT_FORMAT.format( obj );
	}
	
	public static void main( String[] args ) {
		Integer i = new Integer( 1000 );
		Date d1 = new Date( System.currentTimeMillis() );
		Time d2 = new Time( System.currentTimeMillis() );
		System.out.println( format( i ) );
		System.out.println( format( d1 ) );
		System.out.println( format( d2 ) );
	}
	
}

class DefaultObjectFormat implements ObjectFormat {

	/* (non-Javadoc)
	 * @see org.morozko.java.core.text.format.ObjectFormat#format(java.lang.Object)
	 */
	public String format(Object obj) {
		MessageFormat mf = new MessageFormat( "{0}" );
		Object[] args = { obj };
		return mf.format( args  );
	}
	
}