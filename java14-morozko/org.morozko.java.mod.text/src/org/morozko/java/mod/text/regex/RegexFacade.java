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
 * @(#)BbFacade.java
 *
 * @project    : org.opinf.jlib.mod.bbcode
 * @package    : org.opinf.jlib.mod.bbcode
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.text.regex;

import java.io.InputStream;
import java.util.Collection;

import org.morozko.java.mod.text.regex.xml.RegexContentHandler;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class RegexFacade {

	private static final String DEFAULT_BB_CONFIG = "/org/opinf/jlib/mod/text/regex/res/bb_config.xml";
	
	public static String handleDoc( InputStream bbConfig, String docInput ) throws Exception {
		return RegexParser.newInstance( bbConfig ).handleDoc( docInput );
	}
	
	public static String handleDoc( String docInput ) throws Exception {
		return RegexParser.newInstance().handleDoc( docInput );
	}	
	
	public static Collection parseConfig( InputStream bbConfig ) throws Exception {
		RegexContentHandler bbCodeHandler = RegexContentHandler.parse( bbConfig );		
		return bbCodeHandler.getMatchEntryList();
	}
	
	public static Collection parseConfig(  ) throws Exception {
		return parseConfig( RegexParser.class.getResourceAsStream( DEFAULT_BB_CONFIG ) );
	}	

	public static void main( String[] args ) {
		try {
			 String text = "<html><body>[b]prova[/b]</body></html>";
			 RegexParser parser = RegexParser.newInstance();
			 System.out.println( "A : "+text );
			 System.out.println( "B : "+parser.handleDoc( text ) );
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	}
	}	
	
}
