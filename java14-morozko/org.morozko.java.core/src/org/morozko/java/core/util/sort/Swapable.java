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
 * @(#)Swapable.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort
 * @creation	: 29-dic-2004 8.14.29
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface Swapable {

    public int size();
    
    public void swap(int i1, int i2);
    
    public Object get(int index);
    
}
