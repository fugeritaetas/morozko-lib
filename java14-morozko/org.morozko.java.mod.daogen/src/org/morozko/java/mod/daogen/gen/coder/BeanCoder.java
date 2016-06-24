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
 * @(#)BeanCoder.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.coder
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.mod.daogen.gen.config.AliasConfig;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.FieldConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class BeanCoder extends Coder {

	public static void generate( PrintStream stream, DGConfig dgConfig, TableConfig tableConfig ) throws Exception {
    	
    	Properties generalProps = dgConfig.getGeneralProps();
    	
    	String name = tableConfig.getTableName();
        String pName = generalProps.getProperty( "package.bean" )+".helpers";
        String cName = name+"BeanHelper";           
        addClassComment( stream, cName, pName, dgConfig );
        stream.println( "package "+pName+";" );
        stream.println();
        stream.println( "import "+generalProps.getProperty( "package.model" )+"."+name+"Model;" );
        stream.println();
        addTypeComment( stream, "Bean per oggetti di tipo "+name+"Model", dgConfig );
        stream.println( "public class "+cName+" extends "+generalProps.getProperty( "superclass.bean" )+" {"  );
        
        stream.println();
        stream.println("	private final static long serialVersionUID = "+System.currentTimeMillis()+""+(int)(Math.random()*100)+"L;" );
        stream.println();        
        
        Iterator itField = tableConfig.getFields().iterator();
        while ( itField.hasNext() ) {
        	FieldConfig fieldConfig = (FieldConfig)itField.next();
        	String javaField = fieldConfig.getJavaFieldName();
        	stream.println( "	private String "+javaField+";" );
        	stream.println();
        	stream.print( makeGetter( fieldConfig.getFieldName(), "String" ) );
        	stream.println();
        	stream.print( makeSetter( fieldConfig.getFieldName(), "String" ) );
        	stream.println();
        }        
        
        // inizio alias
        stream.println( "    // alias della tabellea - START " );
        Iterator itAlias = tableConfig.getAliasList().iterator();
        while ( itAlias.hasNext() ) {
        	AliasConfig aliasConfig = (AliasConfig)itAlias.next();
        	FieldConfig fieldConfig = tableConfig.getFieldConfig( aliasConfig.getField() );
        	stream.println( );
        	stream.println( "    // setter alias per il campo : "+fieldConfig.getFieldName() );
        	stream.println( "    public void set"+methodName( aliasConfig.getName() )+"( String "+fieldConfig.getJavaFieldName()+" ) {" );
        	stream.println( "        this."+fieldConfig.getJavaFieldName()+" = "+fieldConfig.getJavaFieldName()+";" );
        	stream.println( "    }" );
        	stream.println( );
        	stream.println( "    // getter alias per il campo : "+fieldConfig.getFieldName() );
        	stream.println( "    public String get"+methodName( aliasConfig.getName() )+"() {" );
        	stream.println( "        return this."+fieldConfig.getJavaFieldName()+";" );
        	stream.println( "    }" );
        	stream.println( );
        }
        stream.println( "    // alias della tabellea - END " );
        // fine alias        
         
        Iterator itField1 = tableConfig.getFields().iterator();
        stream.println( "    public "+name+"Model getModel() {" );
        stream.println( "        "+name+"Model model = new "+name+"Model();" );
        while ( itField1.hasNext() ) {
        	FieldConfig fieldConfig = (FieldConfig)itField1.next();
            stream.println( "        model.set"+methodName( fieldConfig.getFieldName() )+"( "+fieldConfig.getTypeHandler().getBeanToModel( fieldConfig.getFieldName() )+" );" );
        }   
        stream.println( "        return model;" );
        stream.println( "    }" );
        stream.println();        
        
        stream.println( "}" );
    }
	
	
}
