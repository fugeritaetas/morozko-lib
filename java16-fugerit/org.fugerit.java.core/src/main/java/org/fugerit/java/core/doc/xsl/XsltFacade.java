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
 * @(#)XsltFacade.java
 *
 * @project    : org.fugerit.java.core.doc
 * @package    : org.fugerit.java.core.doc.xsl
 * @creation   : 13/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.doc.xsl;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class XsltFacade {

	public static void main( String[] args ) {
		try {
			StreamResult result = new StreamResult( System.out );
			TransformerFactory factory = TransformerFactory.newInstance();
			StreamSource xsl = new StreamSource( new FileInputStream( new File( "test/doc1.xsl" ) ) );
			StreamSource source = new StreamSource( new FileInputStream( new File( "test/doc1.xml" ) ) );
			Transformer transformer = factory.newTransformer( xsl );
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
