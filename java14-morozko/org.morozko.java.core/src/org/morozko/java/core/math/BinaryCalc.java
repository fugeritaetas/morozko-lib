/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.std 

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
 * @(#)BinaryCalc.java
 *
 * @project    : org.opinf.jlib.std
 * @package    : org.morozko.java.core.math
 * @creation   : 03/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.math;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class BinaryCalc {
	
	public static long hexToLong( String hexString ) {
		long result = 0;
		hexString = hexString.toUpperCase();
		for ( int k=hexString.length()-1, esp=0; k>=0; k--, esp++ ) {
			result+= BinaryNumber.hexToInt( hexString.charAt( k ) )*Math.pow( 16 , esp );
		}
		return result;
	}
	
	public static void main( String[] args ) {
		try {
			System.out.println( "TEST : "+hexToLong( "FF" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
