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
 * @(#)LineFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.format
 * @creation	: 2-gen-2005 15.48.45
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.format;

import org.morozko.java.core.io.line.LineWriter;
import org.morozko.java.core.text.format.DefaultTextFormat;
import org.morozko.java.core.text.format.TextFormat;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.CMDOutputFormat;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class LineFormat implements CMDOutputFormat {

    protected void printHead(String[] head) {
        this.printRow(head);
    }
    
    protected void printRow(String[] row) {
        String line = "";
        if (wrap) {
            line+= format.getSeparator();
        }
        for (int k=0; k<row.length-1; k++) {
           line+= this.getFormat().prepareField(row[k])+format.getSeparator();
        }
        if (row.length>0) {
            line+= this.getFormat().prepareField(row[row.length-1]);
        }
        if (wrap) {
            line+= format.getSeparator();
        }        
        outputHandler.println(line);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutputFormat#printCMDOutput(org.morozko.java.mod.cmd.CMDOutput)
     */
    public void printCMDOutput(CMDOutput output) throws CMDException {
        if (output.hasHead()) {
            String[] head = output.getHead();
            printHead(head);
        }
        while (output.nextRow()) {
            printRow(output.getRow());
        }
        output.release();
    }
    
    /**
     * <p>Crea un nuovo LineFormat</p>
     * 
     * 
     */
    public LineFormat(LineWriter outputHandler, TextFormat format, boolean wrap) {
        super();
        this.outputHandler = outputHandler;
        this.format = format;
        this.wrap = wrap;
    }
    
    public static CMDOutputFormat getFormatCSV(LineWriter outputHandler, String del, String sep) {
        CMDOutputFormat formatter = 
            new LineFormat(outputHandler, new DefaultTextFormat(del, sep), false);
        return formatter;
    }
    
    public static CMDOutputFormat getFormatCSV(LineWriter outputHandler) {
        CMDOutputFormat formatter = 
            new LineFormat(outputHandler, new DefaultTextFormat(";", "\""), false);
        return formatter;
    }
    
    private boolean wrap;
    
    private LineWriter outputHandler;
    
    private TextFormat format;

    /**
     * <p>Restituisce il valore di format.</p>
     * 
     * @return il valore di format.
     */
    protected TextFormat getFormat() {
        return format;
    }
    
    /**
     * <p>Restituisce il valore di outputHandler.</p>
     * 
     * @return il valore di outputHandler.
     */
    protected LineWriter getOutputHandler() {
        return outputHandler;
    }
    
    /**
     * <p>Restituisce il valore di wrap.</p>
     * 
     * @return il valore di wrap.
     */
    protected boolean isWrap() {
        return wrap;
    }
    
}
