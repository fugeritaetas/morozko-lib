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
 * @(#)TableCMDOutput.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 21-dic-2004 15.52.56
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import org.morozko.java.mod.cmd.CMDException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class TableCMDOutput extends AbstractCMDOutput {

    private int columns;
    
    private int rowPointer;
    
    private String[] head;
    
    private String[][] table;
    
    public TableCMDOutput(String command, String[][] table) {
        this(command, null, table);
    }
    
    private int[] displaySize;
    
    /**
     * <p>
     * Crea un nuovo TableCMDOutput
     * </p>
     * 
     * @param command
     */
    public TableCMDOutput(String command, String[] head, String[][] table) {
        super(command);
        this.head = head;
        this.table = table;
        if (head != null) {
            this.columns = head.length;
        } else if (table != null && table.length > 0) {
            this.columns = table[0].length;
        } else {
            this.columns = 0;
        }
        this.rowPointer = -1;
        //System.out.println("TEST " + table.length);
        
        if (table != null && table.length > 0) {
            this.displaySize = new int[table[0].length];
            for (int i = 0; i < displaySize.length; i++) {
                this.displaySize[i] = 0;
            }
            if (this.head != null) {
                for (int i = 0; i < displaySize.length; i++) {
                    this.displaySize[i] = head[i].length();
                }
            }
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    //System.out.println("TEST "+i+" "+j);
                    this.displaySize[j] = Math.max(this.displaySize[j],
                            table[i][j].length());
                }
            }
        } else if (head!=null) {
            this.displaySize = new int[head.length];
            if (this.head != null) {
                for (int i = 0; i < displaySize.length; i++) {
                    this.displaySize[i] = head[i].length();
                }
            }
        } else {
            this.displaySize = new int[0];
        }
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#columns()
     */
    public int columns() throws CMDException {
        return this.columns;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#nextRow()
     */
    public boolean nextRow() throws CMDException {
        this.rowPointer++;
        return (this.rowPointer<this.table.length);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getRow()
     */
    public String[] getRow() throws CMDException {
        return this.table[rowPointer];
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#hasHead()
     */
    public boolean hasHead() throws CMDException {
        return (this.head!=null);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getHead()
     */
    public String[] getHead() throws CMDException {
        return this.head;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#release()
     */
    public void release() throws CMDException {
        this.head = null;
        this.table = null;
    }
    
    
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#displaySize(int)
     */
    public int displaySize(int col) throws CMDException {
        return this.displaySize[col];
    }

	public int getCurrentRowCount() {
		return this.rowPointer+1;
	}

}
