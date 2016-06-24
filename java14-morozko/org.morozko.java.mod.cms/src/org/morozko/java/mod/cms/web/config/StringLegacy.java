/*****************************************************************
<copyright>
	OpenInformatica Java Library org.opinf.jlib.lgc 

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
 * @(#)StringUtils.java
 *
 * @project    : org.opinf.jlib.lgc
 * @package    : org.opinf.jlib.lgc.lang
 * @creation   : 28/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.config;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class StringLegacy {

	public static String replace( String text, String oldToken, String newToken ) {
		StringBuffer result = new StringBuffer();
		int index = text.indexOf( oldToken );
		while ( index != -1 ) {
			result.append( text.substring( 0, index )+newToken );
			text = text.substring( index+oldToken.length() );
			index = text.indexOf( oldToken );
		}
		result.append( text );
		return result.toString();
	}
	
	public static void main( String[] args ) {
		String a = "prova ciao test ciao a";
		System.out.println( "A : "+a );
		System.out.println( "B : "+replace( a, "ciao", "hello") );
	}
	
}
