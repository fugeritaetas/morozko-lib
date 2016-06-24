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
 * @(#)ModelCoder.java
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
import org.morozko.java.mod.daogen.gen.config.RelationConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ModelCoder extends Coder {

	public static void generate( PrintStream stream, DGConfig dgConfig, TableConfig tableConfig ) throws Exception {
		
		Properties generalProps = dgConfig.getGeneralProps();
		
		String name = tableConfig.getTableName();
        String cName = name+"ModelHelper";
        String pName = generalProps.getProperty( "package.model" )+".helpers";
        addClassComment( stream, cName, pName, dgConfig );
        stream.println( "package "+pName+";" );
        stream.println();
        stream.println( "import "+generalProps.getProperty( "package.bean" )+"."+name+"Bean;" );
        stream.println();
        addTypeComment( stream, "Oggetto di modello per "+name, dgConfig );
        stream.println( "public class "+cName+" extends "+generalProps.getProperty( "superclass.model" )+" {"  );
        
        stream.println();
        stream.println("	private final static long serialVersionUID = "+System.currentTimeMillis()+""+(int)(Math.random()*100)+"L;" );
        stream.println();

        stream.println();
        stream.println("	public static final String ATT_NAME = "+stringValue( lowFirst( name )+"Model"  )+";" );
        stream.println();        
        
        stream.println( "    // campi relativi alla tabella - START " );
        Iterator itField = tableConfig.getFields().iterator();
        while ( itField.hasNext() ) {
        	FieldConfig fieldConfig = (FieldConfig)itField.next();
        	String javaField = fieldConfig.getJavaFieldName();
        	stream.println();
        	stream.println( "	private "+fieldConfig.getTypeHandler().getJavaType()+" "+javaField+";" );
        	stream.println();
        	stream.print( makeGetter( fieldConfig.getFieldName(), fieldConfig.getTypeHandler().getJavaType() ) );
        	stream.println();
        	stream.print( makeSetter( fieldConfig.getFieldName(), fieldConfig.getTypeHandler().getJavaType() ) );
        	stream.println();
        }      
        stream.println( "    // campi relativi alla tabella - END " );
        stream.println();    
        
        // inizio alias
        stream.println( "    // alias della tabellea - START " );
        Iterator itAlias = tableConfig.getAliasList().iterator();
        while ( itAlias.hasNext() ) {
        	AliasConfig aliasConfig = (AliasConfig)itAlias.next();
        	FieldConfig fieldConfig = tableConfig.getFieldConfig( aliasConfig.getField() );
        	stream.println( );
        	stream.println( "    // setter alias per il campo : "+fieldConfig.getFieldName() );
        	stream.println( "    public void set"+methodName( aliasConfig.getName() )+"( "+fieldConfig.getJavaFieldType()+" "+fieldConfig.getJavaFieldName()+" ) {" );
        	stream.println( "        this."+fieldConfig.getJavaFieldName()+" = "+fieldConfig.getJavaFieldName()+";" );
        	stream.println( "    }" );
        	stream.println( );
        	stream.println( "    // getter alias per il campo : "+fieldConfig.getFieldName() );
        	stream.println( "    public "+fieldConfig.getJavaFieldType()+" get"+methodName( aliasConfig.getName() )+"() {" );
        	stream.println( "        return this."+fieldConfig.getJavaFieldName()+";" );
        	stream.println( "    }" );
        	stream.println( );
        }
        stream.println( "    // alias della tabellea - END " );
        // fine alias
        
        // inizio relazioni
        if ( !tableConfig.getRelations().isEmpty() ) {
            stream.println( "    // campi relativi a relazioni - START " );
            for ( int k=0; k < tableConfig.getRelations().size(); k++ ) {
            	RelationConfig relationConfig = (RelationConfig)tableConfig.getRelations().get( k );
            	if ( relationConfig.isTypeOneToOne() ) {
                	String relModelType = generalProps.getProperty( "package.model" )+"."+relationConfig.getTable()+"Model";
                	String relModelVar = lowFirst( relationConfig.getName() );
                	stream.println( "    private "+relModelType+" "+relModelVar+";" );
                	stream.println();
                	stream.println( makeGetterWorker( relModelVar, relModelType ) );
                	stream.println();
                	stream.println( makeSetterWorker( relModelVar, relModelType ) );
                	stream.println();             		
            	} else if ( relationConfig.isTypeOneToMany() ) {
                	String relModelType = "java.util.List";
                	String relModelVar = lowFirst( relationConfig.getName() );
                	stream.println( "    private "+relModelType+" "+relModelVar+";" );
                	stream.println();
                	stream.println( makeGetterWorker( relModelVar, relModelType ) );
                	stream.println();
                	stream.println( makeSetterWorker( relModelVar, relModelType ) );
                	stream.println();             		
            	}
            }   
            stream.println( "    // campi relativi a relazioni - END " );
            stream.println();  
        }
        // fine relazioni           
        
        // metodo che restituisce un bean orientato alla visualizzazione
        Iterator itField1 = tableConfig.getFields().iterator();
        stream.println( "    public "+name+"Bean getBean() {" );
        stream.println( "        "+name+"Bean bean = new "+name+"Bean();" );
        while ( itField1.hasNext() ) {
        	FieldConfig fieldConfig = (FieldConfig)itField1.next();
            stream.println( "        bean.set"+methodName( fieldConfig.getFieldName() )+"( "+fieldConfig.getTypeHandler().getModelToBean( fieldConfig.getFieldName() )+" );" );
        }   
        stream.println( "        return bean;" );
        stream.println( "    }" );
//        
//        // metodo che restituisce un bean orientate alla visualizzazione
//        // in tabelle html (al posto dei blank scrive &nbsp;
//        stream.println( "    public "+name+"Bean getBeanTable() {" );
//        stream.println( "        "+name+"Bean bean = new "+name+"Bean();" );
//        stream.println( "        String currentValue = null;" );
//        for (int k=1; k<=rsmd.getColumnCount(); k++) {
//            String col = rsmd.getColumnName( k );
//            stream.println( "        currentValue = this.formatObject( "+fieldName( col )+" );" );
//            stream.println( "        if ( currentValue==null || currentValue.equals( \"\" ) ) {" );
//            stream.println( "        	currentValue = \" \";" );
//            stream.println( "        }" );
//            stream.println( "        bean.set"+methodName( col )+"( currentValue );" );
//        }   
//        stream.println( "        return bean;" );
//        stream.println( "    }" );        
    
        Iterator itField2 = tableConfig.getFields().iterator();
        stream.println();
        stream.println( "    public String toString() {" );
        stream.println( "        StringBuffer buffer = new StringBuffer();" );
        stream.println( "        buffer.append( this.getClass().getName() );" );
        stream.println( "        buffer.append( \"[ \" );" );
        while ( itField2.hasNext() ) {
        	FieldConfig fieldConfig = (FieldConfig)itField2.next();
            stream.println( "        buffer.append( \""+fieldName( fieldConfig.getFieldName() )+"=\" );" );
            stream.println( "        buffer.append( this."+fieldName( fieldConfig.getFieldName() )+" );" );
            stream.println( "        buffer.append( \"; \" );" );
        }
        stream.println( "        buffer.append( \"]\" );" );
        stream.println( "        return buffer.toString();" );
        stream.println( "    }" );
        stream.println();        
                
        stream.println( "}" );
    }    
	
	
}
