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
 * @(#)DbJFileTest.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.io.File;
import java.io.OutputStream;

import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.JVFSImpl;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMIO;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJFileTest {

	public static void main( String[] args ) {
		try {
			
			LogFacade.updateCurrentConfig("log.level", "6");
			
			JVFS jvfs = JVFSImpl.getInstance( DOMIO.loadDOMDoc( new File( "C:/jvfs/config.xml" ) ).getDocumentElement() );
			
			JFile file = jvfs.getJFile( "/test/ciao.html" );
			
			OutputStream os = file.getOutputStream();
			
			os.write( "test".getBytes() );
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
