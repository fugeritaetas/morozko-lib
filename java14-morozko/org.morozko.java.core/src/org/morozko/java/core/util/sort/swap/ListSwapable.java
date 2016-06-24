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
 * @(#)ListSwapable.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort.swap
 * @creation	: 29-dic-2004 8.15.38
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort.swap;

import java.util.List;

import org.morozko.java.core.util.sort.Swapable;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class ListSwapable implements Swapable {

    private List list;
    
    public ListSwapable(List list) {
        super();
        this.list = list;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Swapable#size()
     */
    public int size() {
        return this.list.size();
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Swapable#swap(int, int)
     */
    public void swap(int i1, int i2) {
        Object obj = this.list.get(i1);
        this.list.set(i1, this.list.get(i2));
        this.list.set(i2, obj);
    }
    
    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Swapable#get(int)
     */
    public Object get(int index) {
        return this.list.get(index);
    }    
    
}
