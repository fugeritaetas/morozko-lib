package org.morozko.java.mod.dbsrc.config.ds.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.csv.CSVColumn;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.Field;

public class XmlFile {

	private CSVColumn[] descriptor;
	
	public static XmlFile newFile( ConfigTag config, Map<String, CSVColumn> genCol ) throws ConfigException {
		String name = config.getName();
		String fileName = config.getProps().getProperty( "xml-file", config.getName()+".xml" );
		XmlFile xmlFile = new XmlFile( name, fileName );
		Properties props = config.getProps();
		int num = Integer.parseInt( props.getProperty( "column-number" ) );
		xmlFile.descriptor = new CSVColumn[num];
		xmlFile.recordPath = props.getProperty( "record-path" );
		xmlFile.recordTag = props.getProperty( "record-tag" );
		String extCols = props.getProperty( "ext-cols" );
		if ( extCols == null ) {
			for ( int k=1; k<=num; k++ ) {
				String colname = props.getProperty( "column-"+k+"-name" );
				String coltype = props.getProperty( "column-"+k+"-type" );
				String colformat = props.getProperty( "column-"+k+"-format" );
				xmlFile.columns.add( colname );
				CSVColumn column =  new CSVColumn( colname, Field.parseType( coltype ), colformat );
				xmlFile.columnMap.put( colname, column );
				xmlFile.descriptor[k-1] = column;
			}			
		} else {
			String[] cols = extCols.split( ";" );
			for ( int k=0; k<cols.length; k++ ) {
				String colName = cols[k];
				String colType = config.getProps().getProperty( colName );
				CSVColumn column = genCol.get( colType );
				if ( column == null ) {
					throw ( new ConfigException( "Undefined ext column : "+colName ) );
				}
				column = new CSVColumn( cols[k], column.getType(), column.getFormat() );
				xmlFile.descriptor[k] = column;
				xmlFile.columnMap.put( cols[k] , column );
			}
		}
		
		// gestione unsafe
		xmlFile.unsafeDefault = new HashMap<String, String>();
		String unsafeColumns = props.getProperty( "unsafe-columns" );
		if ( unsafeColumns != null ) {
			String[] colList = unsafeColumns.split( ";" );
			for ( int k=0; k<colList.length; k++ ) {
				String current = colList[k];
				String def = props.getProperty( "unsafe-default-"+current );
				if ( def != null ) {
					xmlFile.unsafeDefault.put( current , def );
				} else {
					throw new ConfigException( "no default for unsafe column : "+current );
				}
			}
		}
		
		LogFacade.getLog().debug( "ExcelFile "+fileName );
		return xmlFile;
	}
	
	public Map<String, String> getUnsafeDefault() {
		return unsafeDefault;
	}

	public String getRecordPath() {
		return recordPath;
	}

	public CSVColumn[] getDescriptor() {
		return descriptor;
	}

	public String getName() {
		return name;
	}
	
	private String name;
	
	private String fileName;
	
	private String recordPath;
	
	private String recordTag;
	
	private Map<String, String> unsafeDefault;
	
	public String getRecordTag() {
		return recordTag;
	}

	public String getFileName() {
		return fileName;
	}

	private XmlFile(String name, String fileName) {
		super();
		this.name = name;
		this.fileName = fileName;
		this.columns = new ArrayList<String>();
		this.columnMap = new HashMap<String, CSVColumn>();
	}

	private List<String> columns;
	
	private Map<String, CSVColumn> columnMap;
	
	public Iterator<String> getColumnNames() {
		return this.columns.iterator();
	}
	
	public CSVColumn getColumn( String name ) {
		return this.columnMap.get( name );
	}
	
	public int getColumnCount() {
		return this.columns.size();
	}
	
	public CSVColumn getColumnAt( int index ) {
		return this.columnMap.get( this.columns.get( index ) );
	}
	
}
