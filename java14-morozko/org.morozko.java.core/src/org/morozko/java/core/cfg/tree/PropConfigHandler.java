/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)PropConfigHandler.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.xml.cfg
 * @creation   : 6-ott-2005 12.52.06
 */
package org.morozko.java.core.cfg.tree;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.util.path.DefaultPathResolver;

public class PropConfigHandler extends AbstractConfigHandler {

	private Properties prop;
	
	public PropConfigHandler(Properties props) {
		super( "", DefaultPathResolver.DEFAULT );
		this.prop = props;
	}

	public String getParameter(String module, String name) throws ConfigException {
		System.out.println( "ESTRAGGO : '"+(module+name)+"'" );
		return this.prop.getProperty( module+name );
	}

	public void setParameter(String module, String name, String value) throws ConfigException {
		this.prop.setProperty( module+name, value );
	}
	
	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load( new FileInputStream( new File( "test/cfg/test.properties" ) ) );
			ConfigHandler ch = new PropConfigHandler( props );
			System.out.println( "test 1 : "+ch.getParameter( "prova1" ) );
			System.out.println( "test 2 : "+ch.getParameter( "/test/", "prova1" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
