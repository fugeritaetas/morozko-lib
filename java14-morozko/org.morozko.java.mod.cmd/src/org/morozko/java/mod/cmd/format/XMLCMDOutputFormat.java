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
 * @(#)XMLCMDOutputFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.format
 * @creation	: 17-gen-2005 22.36.53
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.format;

import org.morozko.java.core.xml.simple.XMLGenerator;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.CMDOutputFormat;

/**
 * <p></p>
 * 
 * @author  Matteo Franci aka TUX2
 */
public class XMLCMDOutputFormat implements CMDOutputFormat {

    
    
    private XMLGenerator generator;
    
    /**
     * <p>Crea un nuovo XMLCMDOutputFormat</p>
     * 
     * 
     */
    public XMLCMDOutputFormat(XMLGenerator generator) {
        super();
        this.generator = generator;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutputFormat#printCMDOutput(org.morozko.java.mod.cmd.CMDOutput)
     */
    public void printCMDOutput(CMDOutput output) throws CMDException {
        print(output, this.generator, "table", "tr", "th", "td");
    }
    
    public static void print(CMDOutput output, XMLGenerator gen, String tableTag, String rowTag, String headCellTag, String cellTag) throws CMDException {
        gen.openTag(tableTag);
        if (output.hasHead()) {
            printRow(output.getHead(), rowTag, headCellTag, gen);
        }
        while (output.nextRow()) {
            printRow(output.getRow(), rowTag, cellTag, gen);
        }
        gen.closeTag(tableTag);        
    }
    
    private static void printRow(String[] row, String rowTag, String cellTag, XMLGenerator gen) throws CMDException {
        gen.openTag(rowTag);
        for (int k=0; k<row.length; k++) {
            gen.addTag(cellTag, row[k]);
        }
        gen.closeTag(rowTag);
    }

}
