/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)Validator.java
 *
 * @project     : org.fugerit.java.core.tools
 * @package     : org.fugerit.java.core.tools.util.xml
 * @creation	: 15/ott/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.tools.util.xml;

import java.io.File;
import java.io.FileInputStream;

import org.fugerit.java.core.tools.util.args.ArgList;
import org.fugerit.java.core.tools.util.args.ArgUtils;
import org.fugerit.java.core.xml.XMLValidator;
import org.fugerit.java.core.xml.sax.SAXParseResult;
import org.fugerit.java.core.xml.sax.XMLValidatorSAX;
import org.xml.sax.InputSource;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class Validator {

	public static void main( String[] args ) {
		try {
			ArgList list = ArgUtils.parseArgsDefault( args );
			File f = new File( list.findArgValue( "f" ) );
			XMLValidator validator = XMLValidatorSAX.newInstance();
			SAXParseResult result = validator.validateXML( new InputSource( new FileInputStream( f ) ) );
			result.printErrorReport( System.out );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
