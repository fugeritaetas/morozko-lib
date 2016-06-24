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
 * @(#)DefaultTextFormat.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.core.text.format
 * @creation	: 2-gen-2005 0.02.47
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.core.text.format;


/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class DefaultTextFormat implements TextFormat {

    public static final String DEFAULT_SEPARATOR = ",";
    
    public static final String DEFAULT_DELIMITER = String.valueOf('"');
    
    public DefaultTextFormat() {
        this(DEFAULT_SEPARATOR, DEFAULT_DELIMITER);
    }
    
    public DefaultTextFormat(String separator, String delimiter) {
        this.sep = separator;
        this.del = delimiter;
        this.retChar = false;
    }
    
    private boolean retChar;
    
    private String del;
    
    private String sep;
    
    /* (non-Javadoc)
     * @see print.TextFormat#prepareField(java.lang.String)
     */
    public String prepareField(String field) {
        StringBuffer result = new StringBuffer();
        if (field==null) {
            field = "";
        }
//        if ( !this.isRetChar() ) {
//        	for ( int k=0; k<field.length(); k++ ) {
//        		char c = field.charAt( k );
//        		if ( c == '\n' ) {
//        			System.err.println( "PRE :  "+field );
//        			field = field.substring( 0, k )+field.substring( k+1 );
//        			System.err.println( "POST : "+field );
//        		}
//        	}
//        }
        if (del != null) {
            int index = field.indexOf( del );
            while (index>=0) {
                result.append( field.substring(0,index+del.length()) );
                result.append( del );
                field = field.substring(index+del.length());
                index = field.indexOf( del );
            }
            result.append( field );
        } else {
            result.append(field);    
        }
        if ( del != null ) {
        	result = new StringBuffer( del+result.toString()+del );
        }
        return result.toString();
    }

    /* (non-Javadoc)
     * @see print.TextFormat#getSeparator()
     */
    public String getSeparator() {
        return sep;
    }

	/**
	 * @return the retChar
	 */
	public boolean isRetChar() {
		return retChar;
	}

	/**
	 * @param retChar the retChar to set
	 */
	public void setRetChar(boolean retChar) {
		this.retChar = retChar;
	}

}
