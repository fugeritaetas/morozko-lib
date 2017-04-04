/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.tools 

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
 * @(#)Arg.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.tools.util.args
 * @creation	: 28-dic-2004 9.31.27
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.tools.util.args;

import org.fugerit.java.core.util.ArrayUtils;
import org.fugerit.java.core.util.StringUtils;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class Arg {

    public Arg(String name, String[] values) {
        super();
        this.name = name;
        this.values = values;
    }
    
    public String toString() {
        String result = this.getClass().getName()+"[name:"+this.name+";";
        if (this.isEmptyArg()) {
            result+= "EMPTY";
        } else if (this.isSingleArg()) {
            result+= "SINGLE;value:"+this.getValue();
        } else {
            result+= "MULTI;values:"+ArrayUtils.toString(this.values);
        }
        return result+"]";
    }
    
    public Arg(String name, String value) {
        this(name, StringUtils.toArray(value));
    }
    
    public Arg(String name) {
        this(name, new String[0]);
    }

    public boolean isEmptyArg() {
        return this.values.length == 0;
    }
    
    public boolean isSingleArg() {
        return this.values.length == 1;
    }
    
    public boolean isMultiArg() {
        return this.values.length > 1;
    }

    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return this.getValueAt(0);
    }
    
    public int getValueCount() {
        return this.values.length;
    }
    
    public String getValueAt(int index) {
        return this.values[index];
    }
    
    private String name;
    
    private String[] values;
    
}
