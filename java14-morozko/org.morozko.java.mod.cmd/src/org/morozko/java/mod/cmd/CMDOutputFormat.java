/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.cmd 

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
 * @(#)CMDOutputFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd
 * @creation	: 2-gen-2005 15.47.22
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd;

/**
 * <p>Formatta un CMDOutput.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface CMDOutputFormat {

    /**
     * <p>Formatta l'output di un CMD.</p>
     * 
     * @param outptut       l'output da formattare
     * @throws CMDException se si verificano problemi durante la formattazione
     */
    public void printCMDOutput(CMDOutput outptut) throws CMDException;
    
}
