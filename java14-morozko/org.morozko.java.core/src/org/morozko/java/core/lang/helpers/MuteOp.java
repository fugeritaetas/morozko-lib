/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core 

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
 * @(#)MuteOp.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.lang.helpers
 * @creation	: 23-dic-2004 14.39.49
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang.helpers;

import org.morozko.java.core.lang.Op;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class MuteOp extends AbstractOp {

    public static final Op MUTE_OP = new MuteOp();
    
    /**
     * <p>Crea un nuovo MuteOp</p>
     * 
     * 
     */
    public MuteOp() {
        super();
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.lang.Op#doOp()
     */
    public boolean doOp() throws Exception {
        return true;
    }

}
