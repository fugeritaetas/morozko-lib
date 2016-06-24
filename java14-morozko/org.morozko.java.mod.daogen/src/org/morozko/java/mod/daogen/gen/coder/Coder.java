/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

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
 * @(#)Coder.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.coder
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import org.morozko.java.mod.daogen.gen.config.DGConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class Coder {

	protected static String stringValue( String s ) {
		return "\""+s+"\"";
	}
	
	private static final DateFormat DF = new SimpleDateFormat( "dd/MM/yyyy HH/mm/ss" );
	
    protected static String lowFirst( String s ) {
        return s.substring(0, 1).toLowerCase()+s.substring(1);
    }	
	
    protected static String upFirst( String s ) {
        return s.substring(0, 1).toUpperCase()+s.substring(1);
    }
    
    protected static String convertName( String name ) {
        String result = "";
        if ( name.equalsIgnoreCase( "class" ) ) {
        	result = "classField";
        } else {
            StringTokenizer st = new StringTokenizer( name.toLowerCase(), "_" );
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (result.equals("")) {
                    result+= token;
                } else {
                    result+= upFirst( token );
                }
            }	
        }
        return result;
    }

    public static String fieldName( String name  ) {
        return convertName( name );
    }    
    
    public static String methodName( String name  ) {
        return upFirst( convertName( name ) );
    }	

    
    public static String makeSetter( String field, String type) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "    /** \n" );
        buffer.append( "     * <p>Imposta il valore di "+fieldName(field)+"</p> \n" );
        buffer.append( "     * \n" );
        buffer.append( "     * @param      "+fieldName(field)+" il valore di "+fieldName(field)+" da impostare\n" );
        buffer.append( "     */ \n" );        
        buffer.append( "    public void set"+methodName(field)+"( "+type+" "+fieldName(field)+" ) {\n" );
        buffer.append( "        this."+fieldName(field)+" = "+fieldName(field)+";\n" );
        buffer.append( "    }" );
        return buffer.toString();
    }    
    
    public static String makeSetterWorker( String field, String type) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "    /** \n" );
        buffer.append( "     * <p>Imposta il valore di "+field+"</p> \n" );
        buffer.append( "     * \n" );
        buffer.append( "     * @param      "+field+" il valore di "+field+" da impostare\n" );
        buffer.append( "     */ \n" );        
        buffer.append( "    public void set"+upFirst(field)+"( "+type+" "+field+" ) {\n" );
        buffer.append( "        this."+field+" = "+field+";\n" );
        buffer.append( "    }" );
        return buffer.toString();
    }        

    public static String makeGetter( String field, String type) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "    /** \n" );
        buffer.append( "     * <p>Restituisce il valore di "+fieldName(field)+"</p> \n" );
        buffer.append( "     * \n" );
        buffer.append( "     * @return      restituisce il valore di "+fieldName(field)+"\n" );
        buffer.append( "     */ \n" );
        buffer.append( "    public "+type+" get"+methodName(field)+"() {\n" );
        buffer.append( "        return this."+fieldName(field)+";\n" );
        buffer.append( "    }" );
        return buffer.toString();        
    }	
    
    public static String makeGetterWorker( String field, String type) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "    /** \n" );
        buffer.append( "     * <p>Restituisce il valore di "+field+"</p> \n" );
        buffer.append( "     * \n" );
        buffer.append( "     * @return      restituisce il valore di "+field+"\n" );
        buffer.append( "     */ \n" );
        buffer.append( "    public "+type+" get"+upFirst( field )+"() {\n" );
        buffer.append( "        return this."+field+";\n" );
        buffer.append( "    }" );
        return buffer.toString();        
    }	    
	
    public static void addTypeComment( PrintStream stream, String comment, DGConfig dgConfig ) {
        stream.println( "/**" );
        stream.println( " * <p>"+comment+".</p>" );
        stream.println( " *" );
        stream.println( " * @author "+dgConfig.getGeneralProps().getProperty( "author.list" ) );
        stream.println( " */" );
    }    
    
    public static void addClassComment( PrintStream stream, String cName, String pName, DGConfig dgConfig ) {
        stream.println( "/*" );
        stream.println( " * @(#)"+cName+".java" );
        stream.println( " *" );
        stream.println( " * @project    : "+dgConfig.getGeneralProps().getProperty("project.name") );
        stream.println( " * @package    : "+pName );
        stream.println( " * @creation   : "+DF.format( new java.util.Date() ) );
        stream.println( " */" );        
    }	
	
}
