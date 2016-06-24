package org.morozko.java.mod.dbsrc.config.ds.excel;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BoldStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.BaseDataSource;
import org.morozko.java.mod.dbsrc.config.ds.csv.CSVColumn;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

public class ExcelDataSource extends BaseDataSource {

	private Map<String, ExcelFile> fileMap;
	
	private Map<String, CSVColumn> genColumnMap;
	
	private File baseDir;
	
	@Override
	public void configure(ConfigTag config) throws ConfigException {
		this.fileMap = new HashMap<String, ExcelFile>();
		this.genColumnMap = new HashMap<String, CSVColumn>();
		Properties props = config.getProps();
		this.baseDir = new File( props.getProperty( "base-dir" ) );
		if ( !this.baseDir.exists() ) {
			throw ( new ConfigException( "Excel base dir does not exist : "+this.baseDir ) );
		}
		// generic columns config
		ConfigTag ctColumnList = config.getConfigMap().getConfig( "column-list" );
		if ( ctColumnList != null ) {
			Iterator<ConfigTag> itColumnConfig = ctColumnList.getConfigMap().getConfigs();
			while ( itColumnConfig.hasNext() ) {
				ConfigTag ctColumn = itColumnConfig.next();
				String name = ctColumn.getName();
				this.getLog().info( "col : "+name );
				String type = ctColumn.getProps().getProperty( "type" );
				String format = ctColumn.getProps().getProperty( "format" );
				CSVColumn col = new CSVColumn( name, Field.parseType( type ), format );
				this.genColumnMap.put( name , col );
			}

		}
		// table config
		ConfigTag ctTableList = config.getConfigMap().getConfig( "table-list" );
		if ( ctTableList != null ) {
			Iterator<ConfigTag> itTableConfig = ctTableList.getConfigMap().getConfigs();
			while ( itTableConfig.hasNext() ) {
				ConfigTag ctTable = itTableConfig.next();
				ExcelFile file = ExcelFile.newFile( ctTable, this.genColumnMap );
				this.fileMap.put( file.getName() , file );
			}
		}
	}

	@Override
	public RecordIterator getRecords(ConfigTag config) throws DbsrcException {
		RecordIterator ri = null;
		try {
			String excelName = config.getProps().getProperty( "excel-name" );
			String sheetIndex = config.getProps().getProperty( "sheet-index" );
			ExcelFile excelFile = this.fileMap.get( excelName );
			File file = new File( this.baseDir, excelFile.getFileName() );
			ri = new ExcelRecordIterator( excelFile, file );
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ri;
	}

	public RecordHandler setRecords(ConfigTag config) throws DbsrcException {
		ExcelRecordHandler handler = null;
		try {
			String csvName = config.getProps().getProperty( "excel-name" );
			System.out.println( "table name : "+csvName );
			ExcelFile csvFile = this.fileMap.get( csvName );
			
			File file = new File( this.baseDir, csvFile.getFileName() );
			WritableWorkbook ww = Workbook.createWorkbook( file );
			WritableSheet sheet = ww.createSheet( "Sheet" , 0 );
			
			handler = new ExcelRecordHandler( ww , sheet, config, 1 );

			
			
			boolean excelHeader = true;
			if ( excelHeader ) {
				WritableCellFormat wcf = new WritableCellFormat();
				wcf.setAlignment( Alignment.CENTRE );
				WritableFont wf = new WritableFont( wcf.getFont() );
				wf.setBoldStyle( WritableFont.BOLD ); 
				wcf.setFont( wf );
				for ( int k=0; k<csvFile.getDescriptor().length; k++ ) {
					CellView cw = new CellView( sheet.getColumnView( k ) );
					cw.setSize( 256 * 20 );
					sheet.setColumnView( k, cw );
					sheet.addCell( new Label( k, 0, csvFile.getDescriptor()[k].getName(), wcf ) );
					
			}
			
		}
			
//			char csvDelimiter = config.getProps().getProperty( "csv-delimiter", "," ).charAt( 0 );
//			System.out.println( "TEST : "+csvFile );
//			
//			boolean csvAppend = Boolean.valueOf( config.getProps().getProperty( "csv-append", "false" ) );
//			this.getLog().info( "csvAppend : "+csvAppend );
//			FileWriter fw = new FileWriter( file, csvAppend );
//			CsvWriter writer = new CsvWriter( fw, csvDelimiter );
//			boolean csvHeader = Boolean.valueOf( config.getProps().getProperty( "csv-header", "true" ) );
//			this.getLog().info( "csvHeader : "+csvHeader );
//			if ( csvHeader ) {
//				String[] header = new String[ csvFile.getColumnCount() ];
//				for ( int k=0; k<header.length; k++ ) {
//					header[k] = csvFile.getColumnAt( k ).getName();
//				}
//				writer.writeRecord( header );
//			}
//			handler = new CSVRecordHandler( writer, fw, config );
		} catch (Exception e) {
			e.printStackTrace();
			throw ( new DbsrcException( e ) );
		}		
		return handler;
	}

}
