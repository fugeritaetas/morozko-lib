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
 * @(#)TableCMDFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.format
 * @creation	: 2-gen-2005 15.56.57
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.format;

import org.morozko.java.core.io.line.LineWriter;
import org.morozko.java.core.text.format.DefaultTextFormat;
import org.morozko.java.core.util.StringUtils;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class TableCMDFormat extends LineFormat {

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutputFormat#printCMDOutput(org.morozko.java.mod.cmd.CMDOutput)
     */
    public void printCMDOutput(CMDOutput output) throws CMDException {
        super.printCMDOutput(output);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.format.LineFormat#printHead(java.lang.String[])
     */
    protected void printHead(String[] head) {
        this.printSep(head);
        super.printHead(head);
        this.printSep(head);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.format.LineFormat#printRow(java.lang.String[])
     */
    protected void printSep(String[] row) {
        String line = "+";
        for (int k=0; k<row.length; k++) {
           line+= StringUtils.pad("", row[k].length(), '-')+"+";
        }
        this.getOutputHandler().println(line);
    }
    
    /**
     * <p>Crea un nuovo TableCMDFormat</p>
     * 
     * @param outputHandler
     * @param format
     * @param wrap
     */
    public TableCMDFormat(LineWriter outputHandler) {
        super(outputHandler, new DefaultTextFormat("|", null), true);
    }

}
