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
 * @(#)MultiCMD.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 22-dic-2004 15.44.26
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import java.util.Vector;

import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class MultiCMD implements CMD {

    private Vector cmdList = new Vector();
    
    /**
     * <p>Crea un nuovo MultiCMD</p>
     * 
     * 
     */
    public MultiCMD() {
        super();
        cmdList = new Vector();
    }
    
    public void add(CMD cmd) {
        this.cmdList.add(cmd);
    }
    
    public void clear() {
        this.cmdList.clear();
    }
    
    public int cmdCount() {
        return this.cmdList.size();
    }
    
    public CMD get(int index) {
        return (CMD)this.cmdList.get(index);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMD#canHandle(java.lang.String)
     */
    public boolean canHandle(String command) {
        return (this.find(command)!=null);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMD#handleCommand(java.lang.String)
     */
    public CMDOutput handleCommand(String command) throws CMDException {
        CMDOutput output = null;
        CMD cmd = this.find(command);
        if (cmd!=null) {
            output = cmd.handleCommand(command);
        }
        return output;
    }
    
    private CMD find(String command) {
        CMD cmd = null;
        for (int k=0; k<this.cmdCount() && cmd==null; k++) {
            CMD temp = this.get(k);
            if (temp.canHandle(command)) {
                cmd = temp;
            }
        }
        return cmd;
    }
    

}
