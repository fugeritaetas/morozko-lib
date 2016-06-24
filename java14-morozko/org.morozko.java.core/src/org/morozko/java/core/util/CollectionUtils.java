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
 * @(#)CollectionUtils.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util
 * @creation	: 29-dic-2004 10.05.33
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class CollectionUtils {
    
    public static Enumeration iteratorToEnumeration(Iterator it) {
        return (new IteratorToEnumeration(it));
    }

    private CollectionUtils() {
        super();
    }

}

class IteratorToEnumeration implements Enumeration {
 
    private Iterator iterator;
    
    public IteratorToEnumeration(Iterator iterator) {
        this.iterator = iterator;
    }
    
    /* (non-Javadoc)
     * @see java.util.Enumeration#hasMoreElements()
     */
    public boolean hasMoreElements() {
        return this.iterator.hasNext();
    }
    /* (non-Javadoc)
     * @see java.util.Enumeration#nextElement()
     */
    public Object nextElement() {
        return this.iterator.next();
    }
}