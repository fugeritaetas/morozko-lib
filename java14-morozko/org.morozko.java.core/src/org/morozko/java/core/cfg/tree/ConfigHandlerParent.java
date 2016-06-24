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
 * @(#)ConfigHandlerParent.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.xml.cfg
 * @creation   : 6-ott-2005 13.09.37
 */
package org.morozko.java.core.cfg.tree;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.util.path.PathResolver;

public class ConfigHandlerParent extends BasicLogObject implements ConfigHandler {

	private ConfigHandler parent;
	
	private ConfigHandler current;
	
	public boolean isRootHandler() {
		return this.parent==null;
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.xml.cfg.ConfigHandler#getModuleResolver()
	 */
	public PathResolver getModuleResolver() {
		return current.getModuleResolver();
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.xml.cfg.ConfigHandler#getParameter(java.lang.String, java.lang.String)
	 */
	public String getParameter(String module, String name) throws ConfigException {
		String value = this.current.getParameter( module, name );
		if (value==null && !isRootHandler()) {
			value = this.parent.getParameter( module, name );
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.xml.cfg.ConfigHandler#getParameter(java.lang.String)
	 */
	public String getParameter(String name) throws ConfigException {
		String value = this.current.getParameter( name );
		if (value==null && !isRootHandler()) {
			value = this.parent.getParameter( name );
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.xml.cfg.ConfigHandler#setParameter(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setParameter(String module, String name, String value) throws ConfigException {
		String exist = this.getParameter( module, name );
		if (exist==null && !isRootHandler()) {
			this.parent.setParameter( module, name, value );
		} else {
			this.current.setParameter( module, name, value );
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.core.xml.cfg.ConfigHandler#setParameter(java.lang.String, java.lang.String)
	 */
	public void setParameter(String name, String value) throws ConfigException {
		String exist = this.getParameter( name );
		if (exist==null && !isRootHandler()) {
			this.parent.setParameter( name, value );
		} else {
			this.current.setParameter( name, value );
		}
	}
	
	public void setParent (ConfigHandler padre) {
		this.parent = padre;
	}
	
	public ConfigHandler getParent() {
		return this.parent;
	}



}
