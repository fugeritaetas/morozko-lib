/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.sync 

	Copyright (c) 2006 OpenInformatica

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
 * @(#)SyncException.java
 *
 * @project    : org.morozko.java.mod.sync
 * @package    : org.morozko.java.mod.sync.check
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.sync;

import org.morozko.java.core.lang.BasicException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class SyncException extends BasicException {

	/**
	 * <p>Creates a new instance of SyncException.</p>
	 *
	 */
	public SyncException() {
		super();
	}

	/**
	 * <p>Creates a new instance of SyncException.</p>
	 *
	 * @param arg0
	 * @param arg1
	 */
	public SyncException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * <p>Creates a new instance of SyncException.</p>
	 *
	 * @param arg0
	 */
	public SyncException(String arg0) {
		super(arg0);
	}

	/**
	 * <p>Creates a new instance of SyncException.</p>
	 *
	 * @param arg0
	 */
	public SyncException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 32581144701180388L;

}
