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
 * @(#)DefaultChangeChecker.java
 *
 * @project    : org.morozko.java.mod.sync
 * @package    : org.morozko.java.mod.sync.check
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.sync.check;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.sync.SyncException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DefaultChangeChecker extends BasicLogObject implements ChangeChecker {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.sync.check.ChangeChecker#checkModification(java.io.InputStream, java.io.InputStream)
	 */
	public boolean checkModification( JFile fileFrom, JFile fileTo ) throws SyncException {
		boolean equal = false;
		try {
			equal = fileTo.exists();
			if ( equal ) {
				ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
				ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
				StreamIO.pipeStream( fileFrom.getInputStream() , baos1, StreamIO.MODE_CLOSE_IN_ONLY );
				StreamIO.pipeStream( fileTo.getInputStream() , baos2, StreamIO.MODE_CLOSE_IN_ONLY );
				byte[] b1 = baos1.toByteArray();
				byte[] b2 = baos2.toByteArray();
				if ( b1.length == b2.length ) {
					for ( int k=0; k<b1.length && equal; k++ ) {
						equal = b1[k]==b2[k];
					}
				} else {
					equal = false;
				}					
			}
		} catch (IOException e) {
			throw ( new SyncException( e ) );
		}			
		return !equal;
	}

}
