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
 * @(#)StringCompareRule.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util.sort.rule
 * @creation	: 29-dic-2004 8.06.20
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util.sort.rule;

import org.morozko.java.core.util.sort.SortException;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class StringCompareRule extends AbstractCompareRule {

    public StringCompareRule() {
        super();
    }

    /* (non-Javadoc)
     * @see org.morozko.java.core.util.sort.CompareRule#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object obj1, Object obj2) throws SortException {
        int result = 0;
        try {
            String s1 = (String)obj1;
            String s2 = (String)obj2;
            result = s1.compareTo(s2);
        } catch (ClassCastException cce) {
            throw (new SortException(cce));
        } catch (NullPointerException npe) {
            throw (new SortException(npe));   
        }
        return result;
    }
}
