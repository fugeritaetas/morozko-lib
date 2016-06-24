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
 * @(#)CMDWrapper.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 22-dic-2004 10.28.33
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CMDWrapper extends BasicLogObject implements CMD {

    private CMD wrappedCMD;
    
    public CMDWrapper(CMD cmd) {
        super();
        this.setWrappedCMD(cmd);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMD#canHandle(java.lang.String)
     */
    public boolean canHandle(String command) {
        return this.getWrappedCMD().canHandle(command);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMD#handleCommand(java.lang.String)
     */
    public CMDOutput handleCommand(String command) throws CMDException {
        return this.getWrappedCMD().handleCommand(command);
    }

    /**
     * <p>Restituisce il valore di wrappedCMD.</p>
     * 
     * @return il valore di wrappedCMD.
     */
    public CMD getWrappedCMD() {
        return wrappedCMD;
    }
    
    /**
     * <p>Imposta wrappedCMD.</p>
     * 
     * @param wrappedCMD il wrappedCMD da impostare.
     */
    public void setWrappedCMD(CMD wrappedCMD) {
        this.wrappedCMD = wrappedCMD;
    }
    
}
