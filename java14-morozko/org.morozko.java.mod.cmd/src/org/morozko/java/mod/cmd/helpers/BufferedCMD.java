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
 * @(#)BufferedCMD.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 28-dic-2004 8.29.30
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class BufferedCMD extends CMDWrapper {

    public final static String CMD_LIST = "\\blist";
    
    public final static String CMD_CLEAR = "\\bclear";
    
    public final static String CMD_REDO = "\\bredo";
    
    public final static String CMD_LOAD = "\\bload";
    
    public final static String CMD_SAVE = "\\bsave";
    
    public final static String[] COMMANDS = {   
                                                CMD_LIST, 
                                                CMD_REDO,
                                                CMD_CLEAR,
                                                CMD_LOAD,
                                                CMD_SAVE
                                            };    
    
    public static final int DEFAULT_CAPACITY = 10;
    
    private List buffer;
    
    private int bufferCapacity;
    
    public BufferedCMD(CMD cmd) {
        this(cmd, DEFAULT_CAPACITY);
    }
    
    public BufferedCMD(CMD cmd, int capacity) {
        super(cmd);
        this.buffer = new LinkedList();
        this.bufferCapacity = capacity;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMD#canHandle(java.lang.String)
     */
    public boolean canHandle(String command) {
        return super.canHandle(command);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMD#handleCommand(java.lang.String)
     */
    public CMDOutput handleCommand(String command) throws CMDException {
        CMDOutput output = null;
        this.getLog().debug( "command>> '"+command+"'" );
        if (CMDUtils.isCommandIgnoreCase(command, COMMANDS)) {
            if (CMDUtils.isCommandIgnoreCase(command, CMD_LIST)) {
                String[] head = { "N", "COMMAND" };
                String[][] cmdList = new String[this.buffer.size()][2];
                Iterator it = this.buffer.iterator();
                int k = 0;
                while (it.hasNext()) {
                    cmdList[k][0] = String.valueOf(k+1);
                    cmdList[k][1] = (String)it.next();
                    k++;
                }
                output = new TableCMDOutput(command, head, cmdList);
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_SAVE)) { 
            	String f = CMDUtils.parseArgs(CMD_SAVE, command);
                try {
                    File file = new File( f );
                    PrintWriter fw = new PrintWriter( new FileWriter( file ) );
                    Iterator it = this.buffer.iterator();
                    while ( it.hasNext() ) {
                    	fw.println( it.next().toString() );
                    }
                    fw.close();
                    output = new WordCMDOutput(command, "Buffer saved" );
                } catch (Exception e) {
                    output = new WordCMDOutput(command, "Error:"+e);
                }                
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_LOAD)) {
            	String f = CMDUtils.parseArgs(CMD_LOAD, command);
            	this.getLog().debug( "entra load '"+f+"'" );
                try {
                    File file = new File( f );
                    if ( file.exists() ) {
                    	BufferedReader br = new BufferedReader( new FileReader( file ) );
                    	while ( br.ready() ) {
                    		String line = br.readLine();
                    		this.buffer.add( line );
                    	}
                    	br.close();
                    	output = new WordCMDOutput(command, "Buffer loaded  ( size: "+this.buffer.size()+ " )" );
                    } else {
                    	output = new WordCMDOutput(command, "File doesn't exist:"+file.getCanonicalPath());
                    }
                } catch (Exception e) {
                    output = new WordCMDOutput(command, "Error:"+e);
                }
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_REDO)) { 
            	String number = CMDUtils.parseArgs(CMD_REDO, command);
                try {
                    int value = Integer.parseInt(number);
                    if (value>0 && value <=this.buffer.size()) {
                        output = super.handleCommand((String)this.buffer.get(value-1));
                    } else {
                        output = new WordCMDOutput(command, "Value out of range [1,"+this.buffer.size()+"] : "+value);    
                    }
                } catch (NumberFormatException nfe) {
                    output = new WordCMDOutput(command, "Error:"+nfe);
                }
            } else if (CMDUtils.isCommandIgnoreCase(command, CMD_CLEAR)) { 
                this.buffer.clear();
            }
        } else {
            this.buffer.add(command);
            if (this.buffer.size()>this.bufferCapacity) {
                buffer.remove(0);
            }
            output = super.handleCommand(command);
        }
        return output;
    }
    
}
