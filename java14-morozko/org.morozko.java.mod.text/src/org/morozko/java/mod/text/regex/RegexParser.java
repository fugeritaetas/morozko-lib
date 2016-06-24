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
 * @(#)BbParser.java
 *
 * @project    : org.opinf.jlib.mod.bbcode
 * @package    : org.opinf.jlib.mod.bbcode
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.text.regex;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.morozko.java.mod.text.regex.xml.MatchEntry;


/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class RegexParser {

	private Collection matchList;
	
	public static RegexParser newInstance( InputStream bbConfig ) throws Exception {
		return new RegexParser( RegexFacade.parseConfig( bbConfig ) );
	}
	
	public static RegexParser newInstance( ) throws Exception {
		return new RegexParser( RegexFacade.parseConfig( ) );
	}	
	
	private RegexParser( Collection matchList ) {
		this.matchList = matchList;
	}
	
	public String handleDoc( String docInput ) {
		String docOutput = docInput;
		Iterator it = this.matchList.iterator();
		while ( it.hasNext() ) {
			MatchEntry matchEntry = (MatchEntry)it.next();
			docOutput = docOutput.replaceAll( matchEntry.getRegex(), matchEntry.getReplace() );
		}		
		return docOutput;
	}
		
}
