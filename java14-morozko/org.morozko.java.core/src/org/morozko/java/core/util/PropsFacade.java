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
 * @(#)PropsFacade.java
 *
 * @project    : org.morozko.java.core
 * @package    : org.morozko.java.core.util
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.util;

import java.util.Properties;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class PropsFacade {

	public static Properties singleProp( String key, String value ) {
		Properties props = new Properties();
		props.setProperty(key, value);
		return props;
	}
	
	
}
