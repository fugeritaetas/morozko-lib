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
 * @(#)CMDOutput.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd
 * @creation	: 21-dic-2004 15.32.27
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd;

/**
 * <p>Interfaccia che rappresenta l'output di un comando.</p>
 * 
 * <p>L'output ha un formato tabellare.</p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public interface CMDOutput {
    
    /**
     * <p>Restituisce la dimensione presunta di un
     * una colonna dell'output.</p>
     * 
     * @param col   la colonna
     * @return      le dimensioni
     * @throws CMDException     se si verifica un problema durante l'esecuzione
     */
    public int displaySize(int col) throws CMDException;
    
    /**
     * <p>Restituisce il comando che ha generato l'output.</p>
     * 
     * @return  il comando che ha generato l'output
     */
    public String getCommand();
    
    /**
     * <p>Restituisce il numero di colonne di cui è composto l'output.</p>
     * 
     * @return          il numero di colonne di cui è composto l'output
     * @throws CMDException     se si verifica un problema durante l'esecuzione
     */
    public int columns() throws CMDException;
    
    /**
     * <p>Verifica se l'output ha una riga successiva.</p>
     * 
     * @return      <code>true</code> se l'output contiene una nuova riga
     *               <code>false</code> altrimenti
     * @throws CMDException     se si verifica un problema durante l'esecuzione
     */
    public boolean nextRow() throws CMDException;
    
    /**
     * <p>Restituisce la riga corrente.</p>
     * 
     * @return      la riga corrente
     * @throws CMDException     se si verifica un problema durante l'esecuzione
     */
    public String[] getRow() throws CMDException;

    /**
     * <p>Verifica se l'output ha l'intestazione.</p>
     * 
     * @return      <code>true</code> se l'output contiene una intestazione
     *               <code>false</code> altrimenti
     * @throws CMDException     se si verifica un problema durante l'esecuzione
     */
    public boolean hasHead() throws CMDException;
    
    /**
     * <p>Restituisce l'intestazione.</p>
     * 
     * @return      l'intestazione
     * @throws CMDException     se si verifica un problema durante l'esecuzione
     */
    public String[] getHead() throws CMDException;
    
    /**
     * <p>Rilascia tutte le risorse usate dall' output.</p>
     * 
     * @throws CMDException     se si verificano problemi durante il rilascio
     */
    public void release() throws CMDException;
    
}
