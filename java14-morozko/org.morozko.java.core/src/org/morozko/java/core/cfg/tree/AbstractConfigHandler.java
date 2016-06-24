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
 * @(#)AbstractConfigHandler.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.xml.cfg
 * @creation   : 6-ott-2005 12.48.33
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.cfg.tree;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.util.path.PathResolver;

public abstract class AbstractConfigHandler extends BasicLogObject implements ConfigHandler {

	private PathResolver moduleResolver;
	
	private String defaultModule;
	
	public AbstractConfigHandler( String defaultModule, PathResolver moduleResolver ) {
		super();
		this.defaultModule = defaultModule;
		this.moduleResolver = moduleResolver;
	}

	public String getParameter(String name) throws ConfigException {
		return this.getParameter( this.defaultModule, name );
	}

	public void setParameter(String name, String value) throws ConfigException {
		this.setParameter( this.defaultModule, name, value );
	}

	public abstract String getParameter(String module, String name) throws ConfigException;

	public abstract void setParameter(String module, String name, String value) throws ConfigException;

	public PathResolver getModuleResolver() {
		return this.moduleResolver;
	}

}
