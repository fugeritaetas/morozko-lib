/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

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
 * @(#)IdGeneratorConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.daogen.gen.config;

import java.util.Properties;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class IdGeneratorConfig {

	public String toString() {
		return this.getClass().getName()+"[name:"+this.getName()+",type:"+this.getType()+",config:"+this.getConfig()+"]";
	}
	
	private String name;
	
	private String type;
	
	private Properties config;

	/**
	 * @return the config
	 */
	public Properties getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(Properties config) {
		this.config = config;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
