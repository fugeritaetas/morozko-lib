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
 * @(#)Primitive.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.lang
 * @creation	: 29-dic-2004 9.12.45
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.lang;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class Primitive {

    public static Float wrap(float value) {
        return (new Float(value));
    }        
    
    public static Double wrap(double value) {
        return (new Double(value));
    }    
    
    public static Byte wrap(byte value) {
        return (new Byte(value));
    }
    
    public static Short wrap(short value) {
        return (new Short(value));
    }
    
    public static Integer wrap(int value) {
        return (new Integer(value));
    }
    
    public static Long wrap(long value) {
        return (new Long(value));
    }

    private Primitive() {
        super();
    }

}
