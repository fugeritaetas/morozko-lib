/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
 * @(#)DbFileIOException.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJFileIOException extends IOException {

	private Exception wrapped;
	
	public DbJFileIOException( Exception e ) {
		super( e.toString() );
		this.wrapped = e;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6353936323273273061L;

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace()
	 */
	public void printStackTrace() {
		super.printStackTrace();
		this.wrapped.printStackTrace();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
	 */
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		this.wrapped.printStackTrace(s);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
		this.wrapped.printStackTrace(s);
	}

	
	
}
