/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.text 

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
 * @(#)DefaultCSVParser.java
 *
 * @project    : org.morozko.java.mod.text
 * @package    : org.morozko.java.mod.text.csv
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.text.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DefaultCSVParser implements CSVParser {

	private BufferedReader reader;
	
	public DefaultCSVParser( InputStream is ) {
		this.reader = new BufferedReader( new InputStreamReader( is ) );
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.text.csv.CSVParser#hasNext()
	 */
	public boolean hasNext() throws Exception {
		return this.reader.ready();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.text.csv.CSVParser#nextLine()
	 */
	public String[] nextLine() throws Exception {
		String line = this.reader.readLine();
		return line.split( ";" );
	}
	
	public static void main( String[] args ) {
		try {
			CSVParser p = new DefaultCSVParser( new FileInputStream( new File( "data/test.csv" ) ) );
			while ( p.hasNext() ) {
				String[] line = p.nextLine();
				for ( int k=0; k<line.length; k++ ){
					System.out.println( "LINE CURRENT " );
					System.out.println( "COL : "+line[k] );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
