/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core 

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
 * @(#)ObjectFilter.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.util
 * @creation	: 13-dic-2004 11.24.03
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.util.filter;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public interface ObjectFilter {

    public boolean accept(Object item);
    
}
