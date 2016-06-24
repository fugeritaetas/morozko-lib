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
 * @(#)AbstractCMDOutput.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 21-dic-2004 15.37.35
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.helpers;

import org.morozko.java.mod.cmd.CMDOutput;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public abstract class AbstractCMDOutput implements CMDOutput {

    private String command;
    
    /**
     * <p>Crea un nuovo AbstractCMDOutput</p>
     * 
     * 
     */
    public AbstractCMDOutput(String command) {
        super();
        this.command = command;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getCommand()
     */
    public String getCommand() {
        // TODO Auto-generated method stub
        return this.command;
    }

}
