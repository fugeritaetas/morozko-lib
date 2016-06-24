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
 * @(#)DateString.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.util
 * @creation	: 9-dic-2004 10.16.00
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.util;

import java.util.Date;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class DateString {

    public static String getTime(Date time) {
        return (new java.sql.Time(time.getTime())).toString();
    }
    
    public static String getDate(Date time) {
        return (new java.sql.Date(time.getTime())).toString();
    }
    
    public static String getDatetime(Date time) {
        return getDate(time.getTime())+" "+getTime(time.getTime());
    }

    public static String getTime(long time) {
        return (new java.sql.Time(time)).toString();
    }
    
    public static String getDate(long time) {
        return (new java.sql.Date(time)).toString();
    }
    
    public static String getDatetime(long time) {
        return getDate(time)+" "+getTime(time);
    }

    public static String getTime() {
        return (new java.sql.Time(System.currentTimeMillis())).toString();
    }
    
    public static String getDate() {
        return (new java.sql.Date(System.currentTimeMillis())).toString();
    }
    
    public static String getDatetime() {
        long time = System.currentTimeMillis();
        return getDate(time)+" "+getTime(time);
    }
    
    
}
