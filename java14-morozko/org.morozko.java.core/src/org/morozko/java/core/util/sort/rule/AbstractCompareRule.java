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
 * @(#)AbstractCompareRule.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort.rule
 * @creation	: 29-dic-2004 8.04.10
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort.rule;

import org.morozko.java.core.util.sort.CompareRule;
import org.morozko.java.core.util.sort.SortException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public abstract class AbstractCompareRule implements CompareRule {

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.CompareRule#compare(java.lang.Object, java.lang.Object)
     */
    public abstract int compare(Object obj1, Object obj2) throws SortException;

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.CompareRule#isMajor(java.lang.Object, java.lang.Object)
     */
    public boolean isMajor(Object obj1, Object obj2) throws SortException {
        return (this.compare(obj1, obj2)>0);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.CompareRule#isMinor(java.lang.Object, java.lang.Object)
     */
    public boolean isMinor(Object obj1, Object obj2) throws SortException {
        return (this.compare(obj1, obj2)<0);
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.CompareRule#isEqual(java.lang.Object, java.lang.Object)
     */
    public boolean isEqual(Object obj1, Object obj2) throws SortException {
        return (this.compare(obj1, obj2)==0);
    }

}
