/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools 

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
 * @(#)ImageToArray.java
 *
 * @project  : org.morozko.java.mod.tools
 * @package  : org.morozko.java.mod.tools.util.img
 * @creation : 13-feb-2006
 */
package org.morozko.java.mod.tools.util.img;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.morozko.java.core.io.StreamIO;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ImageToArray {

	public static void main( String[] args ) {
		try {
			String img = args[0];
			File file = new File( img );
			FileInputStream fis = new FileInputStream( file );
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			StreamIO.pipeStream( fis, baos, StreamIO.MODE_CLOSE_BOTH );
			byte[] data = baos.toByteArray();
			for ( int k=0; k<data.length; k++ ) {
				System.out.print( data[k]+", " );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
