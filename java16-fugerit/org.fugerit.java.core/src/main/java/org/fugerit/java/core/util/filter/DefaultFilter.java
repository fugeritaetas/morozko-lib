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
 * @(#)DefaultFilter.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.util.filter
 * @creation	: 29-dic-2004 10.20.03
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.util.filter;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class DefaultFilter implements ObjectFilter {

    public static final ObjectFilter DENY_ALL = new DefaultFilter(false);
    
    public static final ObjectFilter ACCEPT_ALL = new DefaultFilter(true);
    
    private boolean accept;
    
    public DefaultFilter() {
        this(true);
    }
    
    /**
     * <p>Crea un nuovo DefaultFilter</p>
     * 
     * 
     */
    public DefaultFilter(boolean accept) {
        super();
        this.accept = accept;
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.util.filter.ObjectFilter#accept(java.lang.Object)
     */
    public boolean accept(Object item) {
        return this.accept;
    }

}
