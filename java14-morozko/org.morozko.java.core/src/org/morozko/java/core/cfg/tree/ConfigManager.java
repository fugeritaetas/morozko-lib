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
 * @(#)ConfigManager.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.xml.cfg
 * @creation   : 6-ott-2005 13.06.51
 */
package org.morozko.java.core.cfg.tree;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfigManager {

	private static Map manager = new HashMap();
	
	public static ConfigHandler getConfigHandler( String configName ) {
		ConfigHandler configHandler = null;
		configHandler = (ConfigHandler) manager.get(configName);
		return configHandler;
	}
	
	public static void setConfigHandler( String configName, ConfigHandler value ){
		manager.put(configName, value);
	}
	
	
	
	public void stampaManager() {
		Set keys = manager.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String chiave = (String) it.next();
			System.out.println("Chiave:" + chiave + "valore:" + manager.get(chiave));
		}
	}
	
	private ConfigManager() {
		super();
		
		try {
			Properties props = new Properties();
			props.load( new FileInputStream( new File( "test/cfg/struttura.properties" ) ) );
			ConfigHandler ch = new PropConfigHandler( props );
			System.out.println( "test 1 : "+ch.getParameter( "prova1" ) );
			System.out.println( "test 2 : "+ch.getParameter( "/test/", "prova1" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main (String[] args) {
//		ConfigManager cm = new ConfigManager();
//		cm.setConfigHandler("primo", new ConfigHandler());
		
		
	}
}
