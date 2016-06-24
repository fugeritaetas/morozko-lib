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
 * @(#)CMDOutputWrapper.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 22-dic-2004 9.34.45
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CMDOutputWrapper implements CMDOutput {

    private CMDOutput wrappedOutput;
    
    public CMDOutputWrapper(CMDOutput wrapped) {
        super();
        this.wrappedOutput = wrapped;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#columns()
     */
    public int columns() throws CMDException {
        return this.getWrappedOutput().columns();
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#displaySize(int)
     */
    public int displaySize(int col) throws CMDException {
        return this.getWrappedOutput().displaySize(col);
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getHead()
     */
    public String[] getHead() throws CMDException {
        return this.getWrappedOutput().getHead();
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getRow()
     */
    public String[] getRow() throws CMDException {
        return this.getWrappedOutput().getRow();
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#hasHead()
     */
    public boolean hasHead() throws CMDException {
        return this.getWrappedOutput().hasHead();
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#nextRow()
     */
    public boolean nextRow() throws CMDException {
        return this.getWrappedOutput().nextRow();
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#release()
     */
    public void release() throws CMDException {
        this.getWrappedOutput().release();
    }
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getCommand()
     */
    public String getCommand() {
        return this.getWrappedOutput().getCommand();
    }
    
    /**
     * <p>Restituisce il valore di wrappedOutput.</p>
     * 
     * @return il valore di wrappedOutput.
     */
    public CMDOutput getWrappedOutput() {
        return wrappedOutput;
    }
    /**
     * <p>Imposta wrappedOutput.</p>
     * 
     * @param wrappedOutput il wrappedOutput da impostare.
     */
    public void setWrappedOutput(CMDOutput wrappedOutput) {
        this.wrappedOutput = wrappedOutput;
    }
}
