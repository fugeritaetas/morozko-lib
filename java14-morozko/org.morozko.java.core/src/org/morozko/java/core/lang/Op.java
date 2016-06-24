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
 * @(#)Op.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.lang
 * @creation	: 23-dic-2004 13.54.26
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface Op {

    public boolean doOp() throws Exception;
    
    public boolean doOpSecure();
    
    public boolean undoOp() throws Exception;
    
    public boolean undoOpSecure() throws Exception;
    
}
