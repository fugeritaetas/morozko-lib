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
 * @(#)MergeSorter.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort.sorter
 * @creation	: 29-dic-2004 8.46.52
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort.sorter;

import org.morozko.java.core.util.sort.CompareRule;
import org.morozko.java.core.util.sort.SortException;
import org.morozko.java.core.util.sort.Swapable;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class MergeSorter extends AbstractSorter {

    /**
     * <p>Crea un nuovo MergeSorter</p>
     * 
     * 
     */
    public MergeSorter() {
        super();
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.Sorter#sort(org.morozko.java.core.util.sort.Swapable, org.morozko.java.core.util.sort.CompareRule)
     */
    public void sort(Swapable data, CompareRule rule) throws SortException {
        recorsiveSort(data, 0, data.size()-1, rule);
    }
    
    private static void recorsiveSort(Swapable data,int lf,int rg,CompareRule r) throws SortException
    {
        int n;
        int ct;
        n = rg-lf+1;
        if (n>1) {
            ct = partition(data,lf,rg,r);
            recorsiveSort(data,lf,ct-1,r);
            recorsiveSort(data,ct+1,rg,r);
        }
    }

    private static int partition(Swapable data, int lf, int rg, CompareRule r) throws SortException
    {
        boolean end = false;
        int result = 0;
        while (!end) {
            while (lf<rg && r.isMinor(data.get(lf), data.get(rg)))
                rg--;
            if (lf<rg)
                data.swap(lf++, rg);
            else {
                result = lf;
                end = true;
            }
            while (lf<rg && r.isMinor(data.get(lf), data.get(rg)))
                lf++;
            if (lf<rg)
                data.swap(lf, rg--);
            else {
                result = rg;
                end = true;
            }
        }
        return result;
    } 

}
