/*******************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.tools 

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
*******************************************************/
/*
 * @(#)PropertyReader.java
 *
 * @project    : org.fugerit.java.core.tools
 * @package    : org.fugerit.java.core.tools.util
 * @creation   : 26/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.tools.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.fugerit.java.core.tools.util.args.Arg;
import org.fugerit.java.core.tools.util.args.ArgList;
import org.fugerit.java.core.tools.util.args.ArgUtils;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 *
 */
public class PropertyReader {

	public static void main( String [] args ) {
		try {
			ArgList argList = ArgUtils.parseArgsDefault( args );
			Arg fileArg = argList.findArg( "f" );
			Arg propArg = argList.findArg( "p" );
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream( new File( fileArg.getValue() ) );
			props.load( fis );
			fis.close();
			String value = props.getProperty( propArg.getValue() );
			System.out.println( value );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
