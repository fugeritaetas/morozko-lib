package org.morozko.java.mod.dbsrc.config.ds.excel;

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
import org.morozko.java.mod.dbsrc.data.Field;

public class ExcelFile {

	private CSVColumn[] descriptor;
	
	public static ExcelFile newFile( ConfigTag config, Map<String, CSVColumn> genCol ) throws ConfigException {
		String name = config.getName();
		String fileName = config.getProps().getProperty( "excel-file", config.getName()+".xls" );
		ExcelFile csvFile = new ExcelFile( name, fileName );
		Properties props = config.getProps();
		int num = Integer.parseInt( props.getProperty( "column-number" ) );
		csvFile.headerHeight = Integer.parseInt( props.getProperty( "header-height", "1" ) );
		csvFile.descriptor = new CSVColumn[num];
		String extCols = props.getProperty( "ext-cols" );
		csvFile.sheetIndex = Integer.parseInt( props.getProperty( "sheet-index", "0" ) );
		if ( extCols == null ) {
			for ( int k=1; k<=num; k++ ) {
				String colname = props.getProperty( "column-"+k+"-name" );
				String coltype = props.getProperty( "column-"+k+"-type" );
				String colformat = props.getProperty( "column-"+k+"-format" );
				csvFile.columns.add( colname );
				CSVColumn column =  new CSVColumn( colname, Field.parseType( coltype ), colformat );
				csvFile.columnMap.put( colname, column );
				csvFile.descriptor[k-1] = column;
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
				csvFile.descriptor[k] = column;
				csvFile.columnMap.put( cols[k] , column );
			}
		}
		LogFacade.getLog().debug( "ExcelFile "+fileName );
		return csvFile;
	}
	
	public int getHeaderHeight() {
		return headerHeight;
	}

	public CSVColumn[] getDescriptor() {
		return descriptor;
	}

	public String getName() {
		return name;
	}
	
	private int headerHeight;
	
	private String name;
	
	private String fileName;
	
	private int sheetIndex;
	
	public int getSheetIndex() {
		return sheetIndex;
	}

	public String getFileName() {
		return fileName;
	}

	private ExcelFile(String name, String fileName) {
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
