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
 * @(#)ArrayUtils.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.util
 * @creation	: 28-dic-2004 9.42.54
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.util;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.fugerit.java.core.util.filter.ObjectFilter;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class ArrayUtils {

    public static Object[] filter(Object[] data, ObjectFilter filter) {
        Vector v = new Vector();
        for (int k=0; k<data.length; k++) {
            if (filter.accept(data[k])) {
                v.add(data[k]);
            }
        }
        return v.toArray();
    }
    
    public static Object[] asArray(Object data) {
        Object[] result = { data }; 
        return result;
    }
    
    public static String toString(Object[] array) {
        String result = "null";
        if (array!=null) {
            result = "";
            for (int k=0; k<array.length; k++) {
                if (array[k]==null) {
                    result+= "null;";
                } else {
                    result+= array[k].toString()+";";
                }
            }
        }
        return "'"+result+"'";
    }
    
    public static void fillArray(List l, Object[] array) {
        Iterator it = l.iterator();
        int count = 0;
        while (it.hasNext()) {
            array[count] = it.next();
            count++;
        }
    }    
    
    public static long[] fillLongArray(List l) {
        long[] result = new long[l.size()];
        fillLongArray(l, result);
        return result;
    }
    
    public static void fillLongArray(List l, long[] array) {
        Iterator it = l.iterator();
        int count = 0;
        while (it.hasNext()) {
            Number n = (Number)it.next();
            array[count] = n.longValue();
            count++;
        }
    }    
    
    /**
     * <p>Crea un nuovo ArrayUtils</p>
     * 
     * 
     */
    public ArrayUtils() {
        super();
    }

}
