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
 * @(#)NoHeadCMDOutput.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.helpers
 * @creation	: 3-gen-2005 0.14.16
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
public class NoHeadCMDOutput extends CMDOutputWrapper {

    /**
     * <p>Crea un nuovo NoHeadCMDOutput</p>
     * 
     * @param wrapped
     */
    public NoHeadCMDOutput(CMDOutput wrapped) {
        super(wrapped);
    }
    
    

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#hasHead()
     */
    public boolean hasHead() throws CMDException {
        return false;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutput#getHead()
     */
    public String[] getHead() throws CMDException {
        return null;
    }
    
}
