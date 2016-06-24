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
 * @(#)MultiAppender.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.log
 * @creation   : 27/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class MultiAppender implements Appender {

	public String toString() {
		return this.getClass().getName()+"["+this.list+"]";
	}
	
	public MultiAppender( Appender appender ) {
		this();
		this.add( appender );
	}
	
	public MultiAppender() {
		
	}
	
	private List list = new ArrayList();
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.Appender#write(java.lang.Object, int)
	 */
	public void write(Object obj, int level) {
		for ( int k=0; k<list.size(); k++ ) {
			((Appender)list.get( k )).write( obj, level );
		}
	}
	
	public void add( Appender appender ) {
		this.list.add( appender );
	}
	
	public void clear() {
		this.list.clear();
	}
	
	public Iterator appenders() {
		return this.list.iterator();
	}

}
