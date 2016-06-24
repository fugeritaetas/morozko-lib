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
 * @(#)Store.java
 *
 * @project	   : org.morozko.java.core
 * @package	   : org.morozko.java.core.util
 * @creation   : 18-giu-2005 15.54.37
 */
package org.morozko.java.core.util;

import java.util.Iterator;
import java.util.List;

/**
 * <p>.</p>
 *
 * @author Matteo Franci aka TUX2
 */
public class Store {

    public List list;
    
    /**
     * <p>Crea una nuova istanza di Store.</p>
     *
     * @param list
     */
    public Store(List list) {
        super();
        this.list = list;
    }
    
    public int size() {
        return this.list.size();
    }
    
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    
    public Iterator iterator() {
        return this.list.iterator();
    }
    
    protected List getList() {
        return list;
    }

}
