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
 * @(#)LongArraySwapable.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort.swap
 * @creation	: 29-dic-2004 9.10.44
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort.swap;

import org.morozko.java.core.lang.Primitive;
import org.morozko.java.core.util.sort.Swapable;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class LongArraySwapable implements Swapable {

    public long[] data;
    
    /**
     * <p>Crea un nuovo LongArraySwapable</p>
     * 
     * 
     */
    public LongArraySwapable(long[] data) {
        super();
        this.data = data;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Swapable#size()
     */
    public int size() {
        return this.data.length;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Swapable#swap(int, int)
     */
    public void swap(int i1, int i2) {
        long temp = this.data[i1];
        this.data[i1] = this.data[i2];
        this.data[i2] = temp;
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Swapable#get(int)
     */
    public Object get(int index) {
        return Primitive.wrap(this.data[index]);
    }

}
