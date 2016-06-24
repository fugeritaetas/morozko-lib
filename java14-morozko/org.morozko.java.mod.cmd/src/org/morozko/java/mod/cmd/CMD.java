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
 * @(#)CMD.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd
 * @creation	: 21-dic-2004 15.32.01
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd;

/**
 * <p>Interfaccia che rappresenta un interprete di comandi.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface CMD {

    /**
     * <p>Verifica se l'interprete può gestire un dato comando.</p>
     * 
     * @param command   il comando da verificare
     * @return          <code>true</code> se il comando può essere gestito
     *                   dall'interprete, <code>false</code> altrimenti
     */
    public boolean canHandle(String command);
    
    /**
     * <p>Esegue un comando.</p>
     * 
     * @param command       il comando da eseguire
     * @return              l'output del comando
     * @throws CMDException se si verifica un problema durante l'esecuzione del comando
     */
    public CMDOutput handleCommand(String command) throws CMDException;
    
}
