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
 * @(#)PaddedCMDOutput.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 22-dic-2004 9.37.44
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import org.morozko.java.core.util.StringUtils;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class PaddedCMDOutput extends CMDOutputWrapper {

    public static final int DEFAULT_MAXSIZE = 256;
    
    public static final int NO_MAXSIZE = 0;
    
    private int maxSize;
    
    private void padRow(String[] row) throws CMDException {
        for (int k=0; k<row.length; k++) {
            int size = this.displaySize(k);
            if (this.maxSize!=NO_MAXSIZE) {
                size = Math.min(size, this.maxSize);
            }
            if (row[k] == null) {
                row[k] = new String(); 
            }
            row[k] = StringUtils.resize(row[k], size);
        }
    }
    
    /**
     * <p>Crea un nuovo PaddedCMDOutput</p>
     * 
     * @param wrapped
     */
    public PaddedCMDOutput(CMDOutput wrapped) {
        this(wrapped, DEFAULT_MAXSIZE);
    }
    
    /**
     * <p>Crea un nuovo PaddedCMDOutput</p>
     * 
     * @param wrapped
     * @param maxSize
     */
    public PaddedCMDOutput(CMDOutput wrapped, int maxSize) {
        super(wrapped);
        this.maxSize = maxSize;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getRow()
     */
    public String[] getRow() throws CMDException {
        String[] row = super.getRow();
        this.padRow(row);
        return row;
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getHead()
     */
    public String[] getHead() throws CMDException {
        String[] row = super.getHead();
        this.padRow(row);
        return row;
    }
    
}
