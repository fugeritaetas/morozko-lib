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
 * @(#)BasicLogObject.java
 *
 * @project  : simossWeb
 * @package  : org.morozko.java.core.log
 * @creation : 21-mag-2005 15.16.58
 */
package org.morozko.java.core.log;

/**
 * <p>.</p>
 *
 * @author Matteo Franci a.k.a. TUX2
 *
 */
public class BasicLogObject implements LogObject {
	
    /* (non-Javadoc)
     * @see org.morozko.java.core.log.LogObject#getLog()
     */
    public Logger getLog() {
        return this.log;
    }
    
    public BasicLogObject() {
        super();
        this.log = LogFacade.createLogger(this);
    }
    
    private Logger log;
    
}
