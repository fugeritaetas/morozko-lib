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
 * @(#)TextFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.text.format
 * @creation	: 2-gen-2005 0.02.16
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.text.format;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface TextFormat {

    public String prepareField(String field);
    
    public String getSeparator();
    
}
