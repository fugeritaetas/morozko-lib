/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.std 

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
 * @(#)ResultExHandler.java
 *
 * @project     : org.opinf.jlib.std
 * @package     : org.opinf.jlib.std.lang
 * @creation	: 15-dic-2004 15.49.31
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class ResultExHandler implements ExHandler {

    private Result result;
    
    /**
     * <p>Restituisce il valore di result.</p>
     * 
     * @return il valore di result.
     */
    public Result getResult() {
        return result;
    }
    
    /**
     * <p>Imposta result.</p>
     * 
     * @param result il result da impostare.
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * <p>Crea un nuovo ResultExHandler</p>
     * 
     * 
     */
    public ResultExHandler() {
        this(new Result());
    }    
    
    /**
     * <p>Crea un nuovo ResultExHandler</p>
     * 
     * 
     */
    public ResultExHandler(Result result) {
        super();
        this.result = result;
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.lang.ExHandler#fatal(java.lang.Exception)
     */
    public void fatal(Exception e) {
        result.putFatal(e);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.lang.ExHandler#error(java.lang.Exception)
     */
    public void error(Exception e) {
        result.putError(e);
    }

    /* (non-Javadoc)
     * @see org.opinf.jlib.std.lang.ExHandler#warning(java.lang.Exception)
     */
    public void warning(Exception e) {
        result.putWarning(e);
    }

}
