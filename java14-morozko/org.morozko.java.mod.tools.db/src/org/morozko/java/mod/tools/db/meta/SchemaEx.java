/*******************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools.db 

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
*******************************************************/
/*
 * @(#)SchemaEx.java
 *
 * @project    : org.morozko.java.mod.tools.db
 * @package    : org.morozko.java.mod.tools.db.meta
 * @creation   : 15/nov/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.tools.db.meta;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.core.util.PropsIO;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.metadata.ColumnModel;
import org.morozko.java.mod.db.metadata.DataBaseModel;
import org.morozko.java.mod.db.metadata.ForeignKeyModel;
import org.morozko.java.mod.db.metadata.IndexModel;
import org.morozko.java.mod.db.metadata.MetaDataUtils;
import org.morozko.java.mod.db.metadata.TableModel;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 *
 */
public class SchemaEx {

	private static boolean isIndex( IndexModel current, IndexModel index ) {
		boolean equal = true; 
		if ( current.getColumnList().size() == index.getColumnList().size() ) {
			for ( int k=0; k< current.getColumnList().size() && equal; k++ ) {
				ColumnModel c1 = (ColumnModel)current.getColumnList().get( k );
				ColumnModel c2 = (ColumnModel)index.getColumnList().get( k );
				equal = ( c1.getName().toLowerCase().equals( c2.getName().toLowerCase() ) );
			}
		} else {
			equal = false;
		}
		return equal;
	}
	
	private static void addFK( ForeignKeyModel fkModel, TableModel tableModel, PrintStream pw, String type, String name ) {
		pw.print( "ALTER TABLE "+tableModel.getName()+" ADD CONSTRAINT "+name );
		pw.print( " "+type+" ( " );
		Properties columnMap = fkModel.getColumnMap();
		Iterator itCol1 = columnMap.keySet().iterator();
		String fromBuffer = "";
		String toBuffer = "";
		while ( itCol1.hasNext() ) {
			String fromCol = (String)itCol1.next();
			String toCol = columnMap.getProperty( fromCol );
			fromBuffer+= fromCol;
			toBuffer+= toCol;
			if ( itCol1.hasNext() ) {
				fromBuffer+= ", ";
				toBuffer+= ", ";
			}
		}
		pw.println( toBuffer+" ) REFERENCES "+fkModel.getForeignTableId().getTableName()+" ( "+fromBuffer+" );\n" );
	}
	
	private static void addIndex( IndexModel indexModel, TableModel tableModel, PrintStream pw, String type, String name ) {
		Iterator itCol = indexModel.getColumnList().iterator();
		if (  !isIndex(tableModel.getPrimaryKey(), indexModel ) &&  itCol.hasNext() ) {
			pw.print( "ALTER TABLE "+tableModel.getName()+" ADD CONSTRAINT "+name );
			pw.print( " "+type+" ( " );
			while ( itCol.hasNext() ) {
				ColumnModel cm = (ColumnModel)itCol.next();
				pw.print( cm.getName() );
				if ( itCol.hasNext() ) {
					pw.print( " , " );
				} else {
					pw.println( " );" );
				}
			}
		}
	}
	
	private static void extract( ConnectionFactory cf , PrintStream pw, Properties typeMappingProps, String schema, Properties map, int[] params ) throws Exception {
		DataBaseModel dbModel = MetaDataUtils.createModel( cf, null, schema );
		if ( 1 == params[0] && 1 == params[1] ) {
			Iterator itTable = dbModel.getTableList().iterator();
			while ( itTable.hasNext() ) {
				TableModel tableModel = (TableModel)itTable.next();
				int count = 1;
				Iterator itFk = tableModel.getForeignKeyList().iterator();
				while ( itFk.hasNext() ) {
					itFk.next();
					pw.println( "ALTER TABLE "+tableModel.getName()+" DROP CONSTRAINT "+tableModel.getName()+"_fk"+count+";" );	
					count++;
				}
			}
		}
		// tables
		Iterator itTable = dbModel.getTableList().iterator();
		while ( itTable.hasNext() ) {
			TableModel tableModel = (TableModel)itTable.next();
			if ( 1 == params[0] ) {
				pw.println("DROP TABLE "+tableModel.getName()+";" );	
			}
			pw.println( "CREATE TABLE "+tableModel.getName()+" (" );
			Iterator itColumns = tableModel.getColumnList().iterator();
			while ( itColumns.hasNext() ) {
				ColumnModel columnModel = (ColumnModel) itColumns.next();
				String type = columnModel.getTypeName();
				type = typeMappingProps.getProperty( type, type );
				String typeMap = map.getProperty( String.valueOf( columnModel.getTypeSql() ) );
				if ( typeMap == null ) {
					pw.print( "\t"+columnModel.getName()+" "+type );
				} else {
					pw.print( "\t"+columnModel.getName()+" "+typeMap );
				}
				if ( columnModel.getSize() != 0 && typeMap == null ) {
					pw.print( "("+columnModel.getSize()+")"  );	
				}
				if ( itColumns.hasNext() ) {
					pw.print( ", " );
					pw.print( " -- sql type : "+columnModel.getTypeSql()+" - "+columnModel.getTypeName() );
				} else {
					pw.print( " -- sql type : "+columnModel.getTypeSql()+" - "+columnModel.getTypeName() );
					pw.print( "\n);\n" );
				}
				pw.println();
				// add keys
			}
			addIndex( tableModel.getPrimaryKey() , tableModel, pw, "PRIMARY KEY", tableModel.getName()+"_pk" );
			int count = 1;
			Iterator itIndex = tableModel.getIndexList().iterator();
			while ( itIndex.hasNext() ) {
				IndexModel indexModel = (IndexModel) itIndex.next();
				addIndex( indexModel , tableModel, pw, "UNIQUE", tableModel.getName()+"_sk"+count );
				count++;	
			}
			pw.println( );
			pw.flush();
		}
		// constraints
		if ( params[1] == 1 ) {
			itTable = dbModel.getTableList().iterator();
			while ( itTable.hasNext() ) {
				TableModel tableModel = (TableModel)itTable.next();
				Iterator itFk = tableModel.getForeignKeyList().iterator();
				int count = 1;
				while ( itFk.hasNext() ) {
					ForeignKeyModel fkModel = (ForeignKeyModel)itFk.next();	
					addFK( fkModel , tableModel, pw, "FOREIGN KEY", tableModel.getName()+"_fk"+count );
					count++;
				}
			}
		}
	}
	
	public static void main( String[] args ) {
		try {
            ArgList list = ArgUtils.parseArgs( args );
            ConnectionFactory cf = ConnArgs.createConnectionFactory( list );
            Arg typeMapping = list.findArg( "m" );
            Properties typeMappingProps = new Properties();
            if ( typeMapping != null ) {
            	typeMappingProps = PropsIO.loadFromFile( new File( typeMapping.getValue() ) );
            }
            String schema = null;
            if ( list.findArg( "ts" ) != null ) {
            	schema = list.findArgValue( "ts" );
            }
            StringBuffer buffer = new StringBuffer();
            // arg map
            String mapArg = list.findArgValue( "map" );
            Properties map = new Properties();
            if ( mapArg != null ) {
            	InputStream is = SchemaEx.class.getResourceAsStream( "/org/morozko/java/mod/tools/db/meta/types-"+mapArg+".properties" );
            	map.load( is );
            }
            // other props
            // a1 -  drop
            int[] params = { 
            			Integer.parseInt( list.findArgValue( "drop" , "0" ) ),
            			Integer.parseInt( list.findArgValue( "fk" , "0" ) ),
            		};
            extract( cf , System.out, typeMappingProps, schema, map, params );
            System.out.println( buffer );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}	
	
}
