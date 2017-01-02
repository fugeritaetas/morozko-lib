/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.doc 

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
 * @(#)DocBase.java
 *
 * @project    : org.fugerit.java.core.doc
 * @package    : org.fugerit.java.core.doc
 * @creation   : 06/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.doc;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DocBase extends DocElement {

	private static void print( DocContainer docContainer, PrintStream s, int pad ) {
		String p = "";
		for ( int k=0; k<pad; k++ ) {
			p+= "  ";
		}
		Iterator it = docContainer.docElements();
		while ( it.hasNext() ) {
			DocElement docElement = (DocElement)it.next();
			s.println( p+docElement );
			if ( docElement instanceof DocContainer ) {
				print( (DocContainer)docElement, s, pad+1 );
			}
		}
	}
	
	public static void print( DocBase docBase, PrintStream s ) {
		s.println( docBase );
		print( docBase.getDocBody(), s, 1 );
	}
	
	public DocBase() {
		this.docBody = new DocContainer();
		this.docMeta = new DocContainer();
		this.docHeader = new DocHeader();
		this.docFooter = new DocFooter();
		this.idMap = new HashMap();
		this.additionalData = new HashMap();
	}
	
	private Map additionalData;
	
	public Map getAdditionalData() {
		return additionalData;
	}

	private DocHeader docHeader;
	
	private DocFooter docFooter;
	
	private DocContainer docBody;

	private DocContainer docMeta;
	
	private HashMap idMap;
	
	public void setId( String id, DocElement element ) {
		this.idMap.put( id , element );
	}
	
	public DocElement getElementById( String id ) {
		return (DocElement)this.idMap.get( id );
	}
	
	/**
	 * @return the docMeta
	 */
	public DocContainer getDocMeta() {
		return docMeta;
	}

	/**
	 * @param docMeta the docMeta to set
	 */
	public void setDocMeta(DocContainer docMeta) {
		this.docMeta = docMeta;
	}

	/**
	 * @return the docBody
	 */
	public DocContainer getDocBody() {
		return docBody;
	}

	/**
	 * @param docBody the docBody to set
	 */
	public void setDocBody(DocContainer docBody) {
		this.docBody = docBody;
	}

	/**
	 * @return the docFooter
	 */
	public DocFooter getDocFooter() {
		return docFooter;
	}

	/**
	 * @param docFooter the docFooter to set
	 */
	public void setDocFooter(DocFooter docFooter) {
		this.docFooter = docFooter;
	}

	/**
	 * @return the docHeader
	 */
	public DocHeader getDocHeader() {
		return docHeader;
	}

	/**
	 * @param docHeader the docHeader to set
	 */
	public void setDocHeader(DocHeader docHeader) {
		this.docHeader = docHeader;
	}
	
	public Properties getInfo() {
		Properties info = new Properties();
		Iterator itInfo = this.getDocMeta().docElements();
		while ( itInfo.hasNext() ) {
			DocElement docElement = (DocElement)itInfo.next();
			if ( docElement instanceof DocInfo ) {
				DocInfo docInfo = (DocInfo) docElement;
				info.setProperty( docInfo.getName() , docInfo.getContent().toString() );
			}
		}	
		return info;
	}
	
}
