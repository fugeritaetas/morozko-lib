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
 * @(#)UpdateOp.java
 *
 * @project    : org.morozko.java.mod.sync
 * @package    : org.morozko.java.mod.sync.update
 * @creation   : 24/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.sync.update;

import java.util.Enumeration;
import java.util.Properties;

import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.JVFSFacade;
import org.morozko.java.core.lang.Op;
import org.morozko.java.core.lang.helpers.AbstractOp;
import org.morozko.java.mod.sync.SyncFacade;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class UpdateOp extends AbstractOp {

	private Properties updateList;
	
	private JVFS jvfs;
	
	public static Op newInstance( String jvfsName, Properties updateList ) {
		UpdateOp updateOp = new UpdateOp();
		updateOp.jvfs = JVFSFacade.getJVFS( jvfsName );
		updateOp.updateList = updateList;
		return updateOp;
	}
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.lang.helpers.AbstractOp#doOp()
	 */
	public boolean doOp() throws Exception {
		boolean ok = true;
		Enumeration e = this.updateList.keys();
		while ( e.hasMoreElements() ) {
			String key =  (String)e.nextElement();
			String value = this.updateList.getProperty( key );
			JFile from = jvfs.getJFile( key );
			JFile to = jvfs.getJFile( value );
			SyncFacade.syncFile( SyncFacade.DEFAULT_CHECKER, from, to );
		}
		return ok;
	}

}
