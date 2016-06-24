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
 * @(#)FactoryCoder.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.coder
 * @creation   : 14-apr-2006
 */
package org.morozko.java.mod.daogen.gen.coder;

import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.TableConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class FactoryCoder extends Coder {

	public static void generate( PrintStream stream, DGConfig dgConfig ) throws Exception {
		
		Properties generalProps = dgConfig.getGeneralProps();

		List tableList = dgConfig.getTableConfigList();
		String name = generalProps.getProperty( "factory.dao.module" );
        String cName = name+"Helper";
        String pName = generalProps.getProperty( "package.dao" )+".helpers";
        addClassComment( stream, cName, pName, dgConfig );
        stream.println( "package "+pName+";" );
        stream.println();
        
		for ( int k=0; k<tableList.size(); k++ ) {
			TableConfig tableConfig = (TableConfig)tableList.get( k );
			stream.println( "import "+generalProps.getProperty( "package.dao" )+"."+tableConfig.getTableName()+"DAO;" );	
		}        
        
        stream.println();
        addTypeComment( stream, "Dao factory", dgConfig );
        stream.println( "public class "+cName+" extends "+dgConfig.getGeneralProps().getProperty( "main.dao.factory" )+" {"  );
        
        stream.println();
        stream.println("	private final static long serialVersionUID = "+System.currentTimeMillis()+""+(int)(Math.random()*100)+"L;" );
        stream.println();
        
		for ( int k=0; k<tableList.size(); k++ ) {
			TableConfig tableConfig = (TableConfig)tableList.get( k );
			String tn = tableConfig.getTableName()+"DAO";
			stream.println( "	private "+tn+" "+lowFirst( tn )+";" );
		}            
		stream.println();
        
		stream.println( "	public "+cName+"( "+generalProps.getProperty( "factory.dao" )+" daoFactory ) {" );
		stream.println( "		super(daoFactory.getConnectionFactory(), daoFactory.getFieldFactory());" );		
		for ( int k=0; k<tableList.size(); k++ ) {
			TableConfig tableConfig = (TableConfig)tableList.get( k );
			String tn = tableConfig.getTableName()+"DAO";
			stream.println( "		this."+lowFirst( tn )+" = new "+tn+"(daoFactory);" );
			stream.println( "		this."+lowFirst( tn )+".setModuleDaoFactory(this);" );
		}
		stream.println( "	}" );
		stream.println();
		
		for ( int k=0; k<tableList.size(); k++ ) {
			TableConfig tableConfig = (TableConfig)tableList.get( k );
			String tn = tableConfig.getTableName()+"DAO";
			stream.println( makeGetterWorker( lowFirst( tn ), tn ) );
		}            
		stream.println();		
		
        stream.println( "}" );		
		
    }   	
	
}
