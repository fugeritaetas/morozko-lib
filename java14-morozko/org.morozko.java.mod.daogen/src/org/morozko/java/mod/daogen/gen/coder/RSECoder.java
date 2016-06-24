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
 * @(#)RSECoder.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.coder
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.FieldConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class RSECoder extends Coder {

	public static void generate( PrintStream stream, DGConfig dgConfig, TableConfig tableConfig ) throws Exception {
		
		Properties generalProps = dgConfig.getGeneralProps();
		
		String name = tableConfig.getTableName();
        String pName = generalProps.getProperty( "package.dao" )+".helpers";
        String cName = name+"ModelRSEHelper";        
        addClassComment( stream, cName, pName, dgConfig );
        stream.println( "package "+pName+";" );
        stream.println();
        addTypeComment( stream, "Classe per l' estrazione di oggetti "+name+"Model da un ResultSet", dgConfig );
        stream.println( "import "+generalProps.getProperty( "package.model" )+"."+name+"Model;" );
        stream.println( "import java.sql.SQLException;" );
        stream.println( "import java.sql.ResultSet;" );
        stream.println();
        stream.println( "public class "+cName+" implements "+generalProps.getProperty( "interface.rse" )+" {"  );
        
        stream.println();
        stream.println("	private final static long serialVersionUID = "+System.currentTimeMillis()+""+(int)(Math.random()*100)+"L;" );
        stream.println();
        
        stream.println( "    public Object extractNext(ResultSet rs) throws SQLException {" );
        stream.println( "        "+name+"Model model = new "+name+"Model();" );
        
        Iterator itField = tableConfig.getFields().iterator();
        while ( itField.hasNext() ) {
        	FieldConfig fieldConfig = (FieldConfig)itField.next();
        	if ( !fieldConfig.isFake() && !fieldConfig.isExcludeRse() ) {
        		if ( fieldConfig.isUnsafe() ) {
        			String varName = fieldConfig.getFieldName()+"Temp";
        			stream.println( "        if ( rs.getObject( \""+fieldConfig.getFieldName()+"\" ) != null ) {" );
        			stream.println( "            model.set"+methodName( fieldConfig.getFieldName() )+"( "+fieldConfig.getTypeHandler().getResultSetExtract( fieldConfig.getFieldName() )+" );" );
        			stream.println( "        }" );
        		} else {
        			stream.println( "        model.set"+methodName( fieldConfig.getFieldName() )+"( "+fieldConfig.getTypeHandler().getResultSetExtract( fieldConfig.getFieldName() )+" );" );	
        		}
        	} else if ( fieldConfig.isExcludeRse() ) {
        		stream.println( "        // field "+fieldConfig.getFieldName()+" skipped because unsafe" );        		
        	} else if ( fieldConfig.isFake() ) {
        		stream.println( "        // field "+fieldConfig.getFieldName()+" skipped because virtual" );
        	}
        }        

        stream.println( "        return model;" );
        stream.println( "    }" );
        stream.println();        
        stream.println( "}" );
        
    }      	
	
}
