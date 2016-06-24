/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)CMDOutputBean.java
 *
 * @project     : org.morozko.java.mod.cmd
 * @package     : org.morozko.java.mod.cmd
 * @creation	: 11/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd;

import java.util.Iterator;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class CMDOutputBean implements Iterator {

	private CMDOutput cmdOutput;

	public String getHasHead() {
		boolean hasHead = false;
		try {
			hasHead = this.cmdOutput.hasHead();
		} catch (CMDException e) {
			e.printStackTrace();
		}
		return String.valueOf( hasHead );
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public String[] getHead() {
		String[] row = null;
		try {
			row = this.cmdOutput.getHead();
		} catch (CMDException e) {
			e.printStackTrace();
		}
		return row;
	}	
	
	public CMDOutputBean( CMDOutput cmdOutput ) {
		this.cmdOutput = cmdOutput;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		boolean next = false;
		try {
			next = this.cmdOutput.nextRow();
		} catch (CMDException e) {
			e.printStackTrace();
		}
		if ( !next ) {
			try {
				this.cmdOutput.release();
			} catch (CMDException e) {
				e.printStackTrace();
			}
		}
		return next;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
		String[] row = null;
		try {
			row = this.cmdOutput.getRow();
		} catch (CMDException e) {
			e.printStackTrace();
		}
		return row;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new RuntimeException( "Unimplemented method" );
	}
	
	
	
}
