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
 * @(#)AbstractSorter.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort.sorter
 * @creation	: 29-dic-2004 8.23.01
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort.sorter;

import java.util.List;

import org.morozko.java.core.util.sort.CompareRule;
import org.morozko.java.core.util.sort.SortException;
import org.morozko.java.core.util.sort.SortUtils;
import org.morozko.java.core.util.sort.Sorter;
import org.morozko.java.core.util.sort.Swapable;
import org.morozko.java.core.util.sort.swap.ArraySwapable;
import org.morozko.java.core.util.sort.swap.ListSwapable;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public abstract class AbstractSorter implements Sorter {

    /**
     * <p>Crea un nuovo AbstractSorter</p>
     * 
     * 
     */
    public AbstractSorter() {
        super();
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sort(java.lang.Object[], org.morozko.java.core.util.sort.CompareRule)
     */
    public void sort(Object[] data, CompareRule rule) throws SortException {
        this.sort(new ArraySwapable(data), rule);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sort(org.morozko.java.core.util.sort.Swapable, org.morozko.java.core.util.sort.CompareRule)
     */
    public abstract void sort(Swapable data, CompareRule rule) throws SortException;

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sort(java.util.List, org.morozko.java.core.util.sort.CompareRule)
     */
    public void sort(List data, CompareRule rule) throws SortException {
        this.sort(new ListSwapable(data), rule);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sortReverse(java.lang.Object[], org.morozko.java.core.util.sort.CompareRule)
     */
    public void sortReverse(Object[] data, CompareRule rule)
            throws SortException {
        this.sortReverse(new ArraySwapable(data), rule);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sortReverse(org.morozko.java.core.util.sort.Swapable, org.morozko.java.core.util.sort.CompareRule)
     */
    public void sortReverse(Swapable data, CompareRule rule)
            throws SortException {
        this.sort(data, rule);
        SortUtils.reverse(data);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sortReverse(java.util.List, org.morozko.java.core.util.sort.CompareRule)
     */
    public void sortReverse(List data, CompareRule rule) throws SortException {
        this.sortReverse(new ListSwapable(data), rule);
    }

}
