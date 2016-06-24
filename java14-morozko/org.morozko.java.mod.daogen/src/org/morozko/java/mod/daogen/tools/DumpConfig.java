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
 * @(#)DumpConfig.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.tools
 * @creation   : 21-mag-2006
 */
package org.morozko.java.mod.daogen.tools;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.simple.XMLGenerator;
import org.morozko.java.core.xml.simple.gen.StreamXMLGenerator;
import org.morozko.java.mod.daogen.gen.coder.Coder;
import org.morozko.java.mod.daogen.gen.config.ConfigParser;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.daogen.gen.config.FieldConfig;
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
import org.w3c.dom.Document;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DumpConfig {
	
	private static String prepareFields( List columnList ) {
		String field = ((ColumnModel)columnList.get( 0 )).getName();
		for ( int j=1; j<columnList.size(); j++ ) {
			field+= ";"+((ColumnModel)columnList.get( j )).getName();;
		}
		return field;
	}
	
	/*
	 * Restituisce i campo interni ed esterni della chiave esterna
	 */
	private static String[] prepareFields( Properties props ) {
		String fieldIn = "";
		String fieldOut = "";
		boolean first = true;
		Enumeration e = props.keys();
		while ( e.hasMoreElements() ) {
			String k = (String)e.nextElement();
			String v = props.getProperty( k );
			if ( first ) {
				first = false;
			} else {
				fieldIn+= ";";
				fieldOut+= ";";
			}
			fieldIn+= k;
			fieldOut+= v;
		}
		String[] result = { fieldIn, fieldOut };
		return result;
	}		
	
	private static boolean checkId( String id, String field ) {
		boolean isID = false;
		if ( id != null ) {
			Pattern p = Pattern.compile( id );
			Matcher m = p.matcher( field );
			isID = m.matches() || field.indexOf( id ) == 0;
		}
		return isID;
	}
	
	public static void dump( ConnectionFactory cf, XMLGenerator xmlGen, String schema, String matcher, String id, String useFistAsKey )  throws Exception {

		Connection conn = cf.getConnection();
		
		LogFacade.getLog().debug( "DumpConfig.dumb() : Loading database metadata : "+schema );
		
		DataBaseModel dataBaseModel = MetaDataUtils.createModel( cf, null, schema );
		
		LogFacade.getLog().debug( "DumpConfig.dumb() : metadata loaded" );
		
		xmlGen.openTag( "dao-gen" );
		
		xmlGen.openTag( "table-list" );

		List tables = dataBaseModel.getTableList();
		
		DGConfig dgConfig = new DGConfig();
		dgConfig.setConnectionFactory( cf );
		
		for ( int k=0; k<tables.size(); k++ ) {
	
			TableModel tableModel = (TableModel)tables.get( k );
			
			String currentTable = tableModel.getName();
			
			LogFacade.getLog().info( "DumpConfig.dumb() : currentTable : "+currentTable );
			
			if ( currentTable.indexOf( matcher ) != -1 ) {

				LogFacade.getLog().debug( "DumpConfig.dumb() : processing : "+currentTable );
				
				if ( currentTable.indexOf( "." )!=-1 ) {
					currentTable = currentTable.substring( currentTable.lastIndexOf( "." )+1 );
				}
				
				String tableName = Coder.methodName( currentTable );
				
				Properties tableAtts = new Properties();
			
				IndexModel primaryKey = tableModel.getPrimaryKey();
				if ( primaryKey != null && primaryKey.getColumnList() != null && primaryKey.getColumnList().size() > 0 ) {
					StringBuffer key = new StringBuffer();
					key.append( ((ColumnModel)primaryKey.getColumnList().get( 0 )).getName() );
					for ( int kl=1; kl<primaryKey.getColumnList().size(); kl++ ) {
						key.append( ";"+((ColumnModel)primaryKey.getColumnList().get( kl )).getName() );
					}
					tableAtts.setProperty( "key" , key.toString() );
				}
				
				String sql = " SELECT v.* FROM "+currentTable+" v ";
				
				List fields = new ArrayList();
				
				ConfigParser.populateFields( dgConfig, tableName, sql, fields );
				
				
				
				tableAtts.put( "update", currentTable );
				tableAtts.put( "name", tableName );
				if ( useFistAsKey != null ) {
					FieldConfig fieldConfig = (FieldConfig)fields.get( 0 );
					tableAtts.put( "key", fieldConfig.getFieldName().toLowerCase() );
				}
				 
				xmlGen.openTag( "table", tableAtts );
				
				xmlGen.addTag( "view", sql );
				
				xmlGen.openTag( "fields" );
				
				Iterator fieldsIt = fields.iterator();
				while ( fieldsIt.hasNext() ) {
					FieldConfig fieldConfig = (FieldConfig)fieldsIt.next();
					LogFacade.getLog().info( "fieldConfig.getFieldName() : "+fieldConfig.getFieldName() );
					ColumnModel columnModel = (ColumnModel) tableModel.getColumn( fieldConfig.getFieldName() );
					LogFacade.getLog().info( "columnModel : "+columnModel );
					Properties props = new Properties();
					props.put( "name", fieldConfig.getFieldNameOriginal() );
					if ( checkId(id, fieldConfig.getFieldName().toLowerCase() ) ) {
						if ( "auto_increment".equalsIgnoreCase( columnModel.getExtra() ) ) {
							props.put( "type", "idauto" );
						} else {
							props.put( "type", "id" );
						}
							
					} else {
						props.put( "type", fieldConfig.getFieldType().getTypeName() );
					}
					props.put( "extra", columnModel.getExtra() );
					xmlGen.emptyTag( "field", props );
				}
				
				xmlGen.closeTag( "fields" );
				
				if ( !tableModel.getForeignKeyList().isEmpty() ) {
					xmlGen.openTag( "relations" );
					Iterator relationIt = tableModel.getForeignKeyList().iterator();
					while ( relationIt.hasNext() ) {
						ForeignKeyModel foreignKeyModel = (ForeignKeyModel)relationIt.next();
						Properties props = new Properties();
						props.put( "name", "list"+Coder.methodName( foreignKeyModel.getForeignTableId().getTableName() ) );
						props.put( "type", "oneToMany" );
						String[] fieldData = prepareFields( foreignKeyModel.getColumnMap() );
						props.put( "field", fieldData[0] );
						if ( !fieldData[0].equals( fieldData[1] ) ) {
							props.put( "field-out", fieldData[1] );	
						}
						props.put( "table", Coder.methodName( foreignKeyModel.getForeignTableId().getTableName() ) );
						props.put( "cascade", "false" );
						xmlGen.emptyTag( "relation", props );
					}
					xmlGen.closeTag( "relations" );
				}				
				
				if ( !tableModel.getIndexList().isEmpty() ) {
					int indexCounter = 1;
					xmlGen.openTag( "loads" );
					Iterator indexIt = tableModel.getIndexList().iterator();
					while ( indexIt.hasNext() ) {
						IndexModel indexModel = (IndexModel)indexIt.next();
						if ( !indexModel.getColumnList().isEmpty() ) {
							Properties props = new Properties();
							String indexName = indexModel.getName();
							if ( indexName == null ) {
								indexName = currentTable+"_"+indexCounter;
								indexCounter++;
							}
							LogFacade.getLog().debug( "DumpConfig.dumb() : load : indexModel : "+indexName);
							props.put( "name", Coder.methodName( indexName ) );
							props.put( "type", "one" );
							props.put( "field", prepareFields( indexModel.getColumnList() ) );
							props.put( "relations", "true" );
							xmlGen.emptyTag( "load", props );							
						}
					}
					xmlGen.closeTag( "loads" );
				}
				
				xmlGen.closeTag( "table" );
				
			} else {
				
				LogFacade.getLog().debug( "DumpConfig.dumb() : skipping : "+currentTable );
				
			}
			
		}
	
		
		conn.close();
		
		xmlGen.closeTag( "table-list" );
		
		xmlGen.closeTag( "dao-gen" );

	}
	
	private static final String VERSION = "V 0.9.3 (2011-11-16)";
	
	private static void printHelp() {
		System.out.println( "USAGE : DumpConfig -d [driver] -c [url] -u [user] -p [pass] [-s [schema]] [-i [idprefix]]" );
		System.out.println( " where : " );
		System.out.println( " -d is jdbc driver [required]" );
		System.out.println( " -c is jdbc url [required]" );
		System.out.println( " -u is connection username [required]" );
		System.out.println( " -p is connection password [required]" );
		System.out.println( " -s is database schema [optional]" );
		System.out.println( " -i prefix for id (a field startind with idprefix is condisidered daoid) [optional]" );
	}
	
	public static void main( String[] args ) {
		try {

			System.out.println( "DAOGEN DumpConfig "+VERSION+" START" );
			
	        ArgList list = ArgUtils.parseArgsDefault( args );
	        
	        Arg logArg = list.findArg( "l" );
	        if ( logArg != null ) {
	        	LogFacade.updateCurrentConfig( "log.level" , logArg.getValue() );
	        }
	        	        
	        ConnectionFactory cf = ConnArgs.createConnectionFactory( list );			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			StreamXMLGenerator xmlGen = new StreamXMLGenerator( new OutputStreamWriter( baos ) );
			
			System.out.println( "DAOGEN DumpConfig parameters : " );
			
			list.printArgs( System.out );
			
			Arg schemaArg = list.findArg( "s" );
			
			String schema = null;
			if ( schemaArg != null ) {
				schema = schemaArg.getValue();
			}
			
			LogFacade.getLog().info( "schema "+schema );
			 
			Arg useFistAsKeyArgKey = list.findArg( "k" );
			
			String useFistAsKey = null;
			if ( useFistAsKeyArgKey != null ) {
				useFistAsKey = useFistAsKeyArgKey.getValue();
			}
			 
			Arg matherArg = list.findArg( "m" );
			
			String mather = "";
			if ( matherArg != null ) {
				mather = matherArg.getValue();
			}			
			
			String id = null;
			if ( list.findArg( "i" ) != null ) {
				id = list.findArgValue( "i" );
			}
			
			String output = null;
			if ( list.findArg( "f" ) != null ) {
				output = list.findArgValue( "f" );
			}
			
			dump( cf, xmlGen, schema, mather, id, useFistAsKey );
			
			System.out.println( "DAOGEN DumpConfig "+VERSION+" END" );
			
			Document doc = DOMIO.loadDOMDoc( new StringReader( baos.toString() ) );
			
			OutputStream out = System.out;
			
			if ( output != null ) {
				out = new FileOutputStream( output );
			}
			
			DOMIO.writeDOM( doc, out );		
			
			
		} catch (Exception e) {
			printHelp();
			e.printStackTrace();
		}
	}
	
	
	
}
