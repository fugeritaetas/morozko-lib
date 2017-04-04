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
 * @(#)ArgList.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.tools.util.args
 * @creation	: 28-dic-2004 10.11.52
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.tools.util.args;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class ArgList extends ArrayList<Arg> {

	public String[] toArgs() {
		List<String> list = new ArrayList<String>();
		for ( int k=0; k<this.size(); k++ ) {
			Arg arg = (Arg)this.get( k );
			list.add( "-"+arg.getName() );
			if ( arg.isSingleArg() ) {
				list.add( arg.getValue() );
			} else if ( arg.isMultiArg() ) {
				for ( int i=0; i<arg.getValueCount(); i++ ) {
					list.add( arg.getValueAt( i ) );
				}
			}
		}
		String[] result = new String[ list.size() ];
		for ( int k=0; k<result.length; k++ ) {
			result[k] = (String)list.get( k );
		}
		return result;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1485325580209630344L;

	/**
     * <p>Crea un nuovo ArgList</p>
     * 
     * 
     */
    public ArgList() {
        super(); 
    }
    

    public Arg findArg(Arg defValue) {
        return this.findArg(defValue.getName(), defValue);
    }
    
    public Arg findArg(String name) {
        return this.findArg(name, null);
    }
    
    public String findArgValue( String name ) {
    	String result = null;
    	Arg arg = this.findArg( name );
    	if ( arg != null ) {
    		result = arg.getValue();
    	}
    	return result;
    }
    public String findArgValue(String name, String defValue) {
        String result = findArgValue( name );
        if ( result == null ) {
        	result = defValue;
        }
        return result;
    }
    
    
    public Arg findArg(String name, Arg defValue) {
        Arg result = defValue;
        for (int k=0; k<this.size() && result==defValue; k++) {
            Arg current = (Arg)this.get(k);
            if (current.getName().equals( name )) {
                result = current;
            }
        }
        return result;
    }
    
    public boolean containsArg(String name) {
        return (this.findArg(name)!=null);
    }

	public void printArgs( PrintStream stream ) {
		for ( int k=0; k<this.size(); k++ ) {
			Arg arg = (Arg)this.get( k );
			stream.println( arg.getName()+" - "+arg.getValue() );
		}
	}
    
}
