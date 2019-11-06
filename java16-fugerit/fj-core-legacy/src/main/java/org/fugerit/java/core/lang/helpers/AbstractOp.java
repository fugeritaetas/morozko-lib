/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core 

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
 * @(#)AbstractOp.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.lang.helpers
 * @creation	: 23-dic-2004 13.55.11
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.lang.helpers;

import org.fugerit.java.core.log.BasicLogObject;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public abstract class AbstractOp extends BasicLogObject implements Op {

    private ExHandler handler;
    
    public AbstractOp() {
        this(MuteExHandler.MUTE_HANDLER);
    }
    
    /**
     * <p>Crea un nuovo AbstractOp</p>
     * 
     * 
     */
    public AbstractOp(ExHandler handler) {
        super();
        this.handler = handler;
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.lang.Op#doOp()
     */
    public abstract boolean doOp() throws Exception;

    /* (non-Javadoc)
     * @see org.fugerit.java.core.lang.Op#doOpSecure()
     */
    public boolean doOpSecure() {
        boolean result = false;
        try {
            result = this.doOp();
        } catch (Exception e) {
            this.handler.error(e);
        }
        return result;
    }

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.lang.Op#undoOp()
	 */
	public boolean undoOp() throws Exception {
		if ( true ) {
			throw ( new Exception( "Unimplemented Method" ) );
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.lang.Op#undoOpSecure()
	 */
	public boolean undoOpSecure() throws Exception {
        boolean result = false;
        try {
            result = this.undoOp();
        } catch (Exception e) {
            this.handler.error(e);
        }
		return result;
	}

}
