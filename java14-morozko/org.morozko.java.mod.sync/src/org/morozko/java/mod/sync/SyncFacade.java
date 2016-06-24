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
 * @(#)SyncFacade.java
 *
 * @project    : org.morozko.java.mod.sync
 * @package    : org.morozko.java.mod.sync
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.sync;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.JVFSImpl;
import org.morozko.java.core.jvfs.file.RealJMount;
import org.morozko.java.mod.sync.check.ChangeChecker;
import org.morozko.java.mod.sync.check.DefaultChangeChecker;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class SyncFacade {
	
	public static final ChangeChecker DEFAULT_CHECKER = new DefaultChangeChecker();
	
	public static int sync( ChangeChecker checker, JVFS jvfs, Properties pros ) throws SyncException {
		int count = 0;
		Enumeration e = pros.keys();
		while ( e.hasMoreElements() ) {
			String from = (String)(e.nextElement());
			String to = pros.getProperty( from );
			JFile fileFrom = jvfs.getJFile( from );
			JFile fileTo = jvfs.getJFile( to );
			if ( syncFile( checker , fileFrom, fileTo ) ) {
				count++;
			}
		}
		return count;
	}
	
	public static int syncDir( ChangeChecker checher, JFile dirFrom, JFile dirTo ) throws SyncException {
		int count = 0;
		try {
			JFile[] list = dirFrom.listFiles();
			for ( int k=0; k<list.length; k++ ) {
				JFile fileFrom = list[k];
				JFile fileTo = dirTo.getChild( fileFrom.getName() );
				if ( syncFile(checher, fileFrom, fileTo) ) {
					count++;
				}
			}
		} catch (IOException e) {
			throw ( new SyncException( e ) );
		}
		return count;
	}
	
	public static boolean syncFile( ChangeChecker checher, JFile fileFrom, JFile fileTo ) throws SyncException {
		boolean changed = false;
		try {
			changed = checher.checkModification( fileFrom , fileTo );
			if ( changed ) {
				StreamIO.pipeStream( fileFrom.getInputStream() , fileTo.getOutputStream(), StreamIO.MODE_CLOSE_BOTH );
			}
		} catch (IOException e) {
			throw ( new SyncException( e ) );
		}		
		return changed;
	}
	
	public static void main( String[] args ) {
		try {
			RealJMount root = new RealJMount( new File( "test" ) );
			JVFS jvfs = JVFSImpl.getInstance( root );
			syncDir( DEFAULT_CHECKER, jvfs.getJFile( "/dir1" ), jvfs.getJFile( "/dir2" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
