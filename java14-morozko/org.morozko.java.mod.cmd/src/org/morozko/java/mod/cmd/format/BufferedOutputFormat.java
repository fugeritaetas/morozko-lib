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
 * @(#)BufferedOutputFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.cmd.format
 * @creation	: 2-gen-2005 16.21.59
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cmd.format;

import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.CMDOutputFormat;
import org.morozko.java.mod.cmd.helpers.CMDUtils;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class BufferedOutputFormat implements CMDOutputFormat {

    private CMDOutputFormat format;
    
    /**
     * <p>Crea un nuovo BufferedOutputFormat</p>
     * 
     * 
     */
    public BufferedOutputFormat(CMDOutputFormat format) {
        super();
        this.format = format;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.mod.cmd.CMDOutputFormat#printCMDOutput(org.morozko.java.mod.cmd.CMDOutput)
     */
    public void printCMDOutput(CMDOutput output) throws CMDException {
        this.format.printCMDOutput(CMDUtils.bufferedOutput(output));
    }

}
