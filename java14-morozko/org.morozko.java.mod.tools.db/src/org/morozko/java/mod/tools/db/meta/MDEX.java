/*****************************************************************
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
*****************************************************************/
/*
 * @(#)MDEX.java
 *
 * @project    : org.morozko.java.mod.tools.db
 * @package    : org.morozko.java.mod.tools.db.meta
 * @creation   : 05/apr/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.tools.db.meta;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.metadata.ColumnModel;
import org.morozko.java.mod.db.metadata.DataBaseModel;
import org.morozko.java.mod.db.metadata.ForeignKeyModel;
import org.morozko.java.mod.db.metadata.IndexModel;
import org.morozko.java.mod.db.metadata.MetaDataUtils;
import org.morozko.java.mod.db.metadata.TableModel;
import org.morozko.java.mod.tools.ToolUtils;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class MDEX {

	public static final String VERSION = "0.1.4 [2010-04-30]";
	
    private static void printInfo() {
        ToolUtils.printInfo("MDEX v. "+VERSION, "Morozko");
    }	
	
	private Cell newCell( String label, int style, int cs, int rs, int ha, int va ) throws Exception {
		Cell c = new Cell( new Paragraph( label, new Font( Font.HELVETICA, 10, style ) ) );
		c.setColspan( cs );
		c.setRowspan( rs );
		c.setHorizontalAlignment( ha );
		c.setVerticalAlignment( va );
		return c;
	}    
    
    private void handleTableList( Document document, DataBaseModel dataBaseModel, List tableListNames ) throws Exception {
    	
    	float[] indexTableWidths = { (float)0.05, (float)0.05, (float)0.05, (float)0.05, (float)0.05,
    								(float)0.05, (float)0.05, (float)0.05, (float)0.05, (float)0.05,
    								(float)0.05, (float)0.05, (float)0.05, (float)0.05, (float)0.05,
    								(float)0.05, (float)0.05, (float)0.05, (float)0.05, (float)0.05, };
    	
		int indexTableCols = indexTableWidths.length;
		Table indexTable = new Table( indexTableCols );
		indexTable.setBorderWidth(0);	
		indexTable.setWidth(100);
		indexTable.setBorderColor( Color.black );
		indexTable.setPadding(2);
		indexTable.setSpacing(0);		
		indexTable.setWidths( indexTableWidths );    	
    	
		
		Iterator itTablesNamesIndex = tableListNames.iterator();
		while ( itTablesNamesIndex.hasNext() ) {
			String tableName = (String)itTablesNamesIndex.next();
			LogFacade.getLog().info( "tableName  : "+tableName );
			TableModel tableModel = (TableModel)dataBaseModel.getTableNameMap().get( tableName );
			LogFacade.getLog().info( "tableModel : "+tableModel );
			indexTable.addCell( this.newCell( tableName, Font.BOLD, 8, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );
			indexTable.addCell( this.newCell( tableModel.getComment(), Font.NORMAL, 12, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );
		}
		
		indexTable.addCell( this.newCell( " ", Font.BOLD, 20, 3, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
		
        Iterator itTablesNames = tableListNames.iterator();
        
        int[] csColumns = { 4, 4, 2, 2, 8 };
        
        while ( itTablesNames.hasNext() ) {
        	String tableName = (String)itTablesNames.next();
        	
        	TableModel tableModel = (TableModel)dataBaseModel.getTableNameMap().get( tableName );
        	
        	// titolo della tabella
        	indexTable.addCell( this.newCell( "Tabella : "+tableName, Font.BOLD, 20, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        	// commento alla tabella
        	indexTable.addCell( this.newCell( tableModel.getComment(), Font.NORMAL, 20, 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );
        	// titolo per le colonne
        	indexTable.addCell( this.newCell( "Colonna", Font.BOLD, csColumns[0], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        	indexTable.addCell( this.newCell( "Tipo", Font.BOLD, csColumns[1], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        	indexTable.addCell( this.newCell( "PK", Font.BOLD, csColumns[2], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        	indexTable.addCell( this.newCell( "NULL", Font.BOLD, csColumns[3], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        	indexTable.addCell( this.newCell( "Commenti", Font.BOLD, csColumns[4], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        	Iterator itColumns = tableModel.getColumnList().iterator();
        	IndexModel primaryKey = tableModel.getPrimaryKey();
        	while ( itColumns.hasNext() ) {
        		ColumnModel columnModel = (ColumnModel)itColumns.next();
            	indexTable.addCell( this.newCell( columnModel.getName().toLowerCase(), Font.NORMAL, csColumns[0], 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );
            	String type = columnModel.getTypeName().toLowerCase();
            	if ( type.toLowerCase().indexOf( "char" ) != -1 ) {
            		type+= " ("+columnModel.getSize()+")";
            	}
            	indexTable.addCell( this.newCell( type, Font.NORMAL, csColumns[1], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
            	String pk = "no";
            	if ( primaryKey.getColumnMap().containsKey( columnModel.getName() ) ) {
            		pk = "si";	
            	}
            	indexTable.addCell( this.newCell( pk,Font.NORMAL,  csColumns[2], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
            	String nullable = "";
            	if ( columnModel.getNullable() == ColumnModel.NULLABLE_TRUE ) {
            		nullable = "si";
            	} else if ( columnModel.getNullable() == ColumnModel.NULLABLE_FALSE ) {
            		nullable = "no";
            	}
            	indexTable.addCell( this.newCell( nullable, Font.NORMAL, csColumns[3], 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
            	indexTable.addCell( this.newCell( columnModel.getComment(), Font.NORMAL, csColumns[4], 1, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );        		
        	}
        	
        	// indici
        	Iterator itIndex = tableModel.getIndexList().iterator();
        	if ( itIndex.hasNext() ) {
        		String indici = "Indici : \n";
        		while ( itIndex.hasNext() ) {
            		IndexModel indexModel = (IndexModel) itIndex.next();
            		if ( indexModel.getName() != null ) {
                		indici+= "- "+indexModel.getName()+" [";
                		Iterator itColIndex = indexModel.getColumnList().iterator();
                		while ( itColIndex.hasNext() ) {
                			ColumnModel columnModel = (ColumnModel)itColIndex.next();
                			indici+= " "+columnModel.getName();
                		}
                		indici+= " ]\n";            			
            		}
            	}
        		indexTable.addCell( this.newCell( indici, Font.NORMAL, 20, 3, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );
        	}
        	
        	// chiavi esterne
        	Iterator itKeys = tableModel.getForeignKeyList().iterator();
        	if ( itKeys.hasNext()) {
        		String chiaviEsterne = "Chiavi esterne : \n";
        		while ( itKeys.hasNext() ) {
            		ForeignKeyModel foreignKeyModel = (ForeignKeyModel)itKeys.next();
            		TableModel foreignTableModel = (TableModel)dataBaseModel.getTableMap().get( foreignKeyModel.getForeignTableId() );
            		chiaviEsterne+= "- "+foreignKeyModel.getName();
            		String part1 = "[ ";
            		String part2 = foreignTableModel.getName()+" [";
            		Enumeration e = foreignKeyModel.getColumnMap().keys();
            		while ( e.hasMoreElements() ) {
            			String key = (String)e.nextElement();
            			String value = foreignKeyModel.getColumnMap().getProperty( key );
            			part1+= value+" ";
            			part2+= key+" ";
            		}
            		part1+= "] ";
            		part2+= "] ";
            		chiaviEsterne+= part1+" riferimento a "+part2+"\n";
            	}
        		indexTable.addCell( this.newCell( chiaviEsterne, Font.NORMAL, 20, 3, Element.ALIGN_LEFT, Element.ALIGN_CENTER) );
        	}
        	
    		        	
        	// spaziatura
        	indexTable.addCell( this.newCell( " ", Font.NORMAL, 20, 1, Element.ALIGN_CENTER, Element.ALIGN_CENTER) );
        }    	
        
        document.add( indexTable );
    	
    }
    
    
    public static void main(String[] arg) {
        try {
            
        	long startTime = System.currentTimeMillis();
        	
        	printInfo();
        	
            ArgList list = ArgUtils.parseArgs(arg);
            
            ConnectionFactory cp = ConnArgs.createConnectionFactory( list );
            
            //cp = ConnectionFactoryPool.newFactory( cp );
            
            LogFacade.getLog().info( "DRIVER INFO : "+ConnectionFactoryImpl.getDriverInfo( cp ) );
            
            String fileOutput = list.findArgValue( "o" );
            
            String allTables = list.findArgValue( "a" );
            
            String tableList = list.findArgValue( "t" );
            
            String schema = list.findArgValue( "ts" );
            
            String catalog = list.findArgValue( "tc" );
            
            List tableListNames = new ArrayList();
            
            LogFacade.getLog().info( "Loading database model..." );
            
            DataBaseModel dataBaseModel = MetaDataUtils.createModel( cp, catalog, schema );
            
            LogFacade.getLog().info( "Database model loaded [OK]" );
            
            LogFacade.getLog().info( "Generating documentation..." );
            
            if ( "true".equalsIgnoreCase( allTables ) ) {
            	System.out.println( "TEST 1" );
                Iterator itTables = dataBaseModel.getTableList().iterator();
                while ( itTables.hasNext() ) {
                	TableModel tableModel = (TableModel)itTables.next();
                    tableListNames.add( tableModel.getName() );	
                }
            } else if ( "pattern".equalsIgnoreCase( allTables ) ) {
                System.out.println( "TEST 3" );
                Iterator itTables = dataBaseModel.getTableList().iterator();
                while ( itTables.hasNext() ) {
                	TableModel tableModel = (TableModel)itTables.next();
                    if ( tableModel.getName().indexOf( tableList ) != -1 ) {
                    	tableListNames.add( tableModel.getName() );	
                    }   	
                }
            } else if ( tableList != null ) {
            	System.out.println( "TEST 2" );
            	String[] tables = tableList.split( ";" );
            	tableListNames.addAll( Arrays.asList( tables ) );
            }
            
            FileOutputStream fos = new FileOutputStream( new File( fileOutput ) );
            
            Document document = new Document();
            
            RtfWriter2.getInstance( document, fos );
            
            document.open();
            
            (new MDEX()).handleTableList( document, dataBaseModel, tableListNames );
            
            document.close();
            
            fos.close();
            
            LogFacade.getLog().info( "Documentation generated [OK]" );
            
            printInfo();
            
            long time = System.currentTimeMillis()-startTime;
            
            LogFacade.getLog().info( "Execution time : "+(time/1000)+"."+(time%1000) );;
            
        } catch (Exception e) {
            System.err.println(" options : ");
            System.err.println("      -d  [jdbc-driver-class] ");
            System.err.println("      -c  [jdbc-url] ");
            System.err.println("      -u  [username] ");
            System.err.println("      -p  [password] ");
            System.err.println("      -o  [output-file] ");
            System.err.println("      -a  [true|false] dump all tables ");
            System.err.println("      -t  tablename[;tablename] comma separated table names ");
            System.err.println("      -tc catalog ");
            System.err.println("      -ts schema ");
            System.err.println();
            e.printStackTrace();
        }
        
    }    
	
}

