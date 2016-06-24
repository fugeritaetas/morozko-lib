/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.text 

	Copyright (c) 2006 OpenInformatica

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
 * @(#)FormatUtils.java
 *
 * @project    : org.opinf.jlib.mod.regex
 * @package    : org.morozko.java.mod.text.format
 * @creation   : 29/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.text.format;

import java.text.MessageFormat;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class FormatFacade {

	public static String format( String pattern, String arg ) {
		Object[] args = { arg };
		return new MessageFormat( pattern ).format( args );
	}
	
}
