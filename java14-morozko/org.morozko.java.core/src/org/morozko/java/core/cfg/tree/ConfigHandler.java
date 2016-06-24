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
 * @(#)ConfigHandler.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.xml.cfg
 * @creation   : 6-ott-2005 12.08.58
 */
package org.morozko.java.core.cfg.tree;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.util.path.PathResolver;

/**
 * <p>Interfaccia per la gestione di parametri di configurazione.</p>
 *
 * @author Matteo Franci aka TUX2
 */
public interface ConfigHandler extends ConfigReader {

	public void setParameter( String name, String value ) throws ConfigException;	

	public void setParameter( String module, String name, String value ) throws ConfigException; 
	
	public PathResolver getModuleResolver();
	
}
