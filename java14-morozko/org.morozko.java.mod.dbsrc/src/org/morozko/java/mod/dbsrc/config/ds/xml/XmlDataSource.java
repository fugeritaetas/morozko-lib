package org.morozko.java.mod.dbsrc.config.ds.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.BaseDataSource;
import org.morozko.java.mod.dbsrc.config.ds.csv.CSVColumn;
import org.morozko.java.mod.dbsrc.config.ds.excel.ExcelFile;
import org.morozko.java.mod.dbsrc.config.ds.excel.ExcelRecordHandler;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlDataSource extends BaseDataSource {

	private Map<String, XmlFile> fileMap;
	
	private Map<String, CSVColumn> genColumnMap;
	
	private File baseDir;
	
	public RecordHandler setRecords(ConfigTag config) throws DbsrcException {
		XmlRecordHandler handler = null;
		try {
			String csvName = config.getProps().getProperty( "xml-name" );
			System.out.println( "table name : "+csvName );
			XmlFile csvFile = this.fileMap.get( csvName );
			
			File file = new File( this.baseDir, csvFile.getFileName() );
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element root = doc.createElement( "Fatture" );
			doc.appendChild( root );
	
			handler = new XmlRecordHandler( csvFile, file, doc , root, config );

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

	@Override
	public RecordIterator getRecords(ConfigTag config) throws DbsrcException {
		RecordIterator ri = null;
		try {
			String xmlName = config.getProps().getProperty( "xml-name" );
			XmlFile xmlFile = this.fileMap.get( xmlName );
			File file = new File( this.baseDir, xmlFile.getFileName() );
			ri = new XmlRecordIterator( xmlFile, file );
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ri;
	}

	@Override
	public void configure(ConfigTag config) throws ConfigException {
		this.fileMap = new HashMap<String, XmlFile>();
		this.genColumnMap = new HashMap<String, CSVColumn>();
		Properties props = config.getProps();
		this.baseDir = new File( props.getProperty( "base-dir" ) );
		if ( !this.baseDir.exists() ) {
			throw ( new ConfigException( "Xml base dir does not exist : "+this.baseDir ) );
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
				XmlFile file = XmlFile.newFile( ctTable, this.genColumnMap );
				this.fileMap.put( file.getName() , file );
			}
		}		
	}

}
