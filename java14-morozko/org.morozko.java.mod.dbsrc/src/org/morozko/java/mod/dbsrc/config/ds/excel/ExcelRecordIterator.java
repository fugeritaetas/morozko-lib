package org.morozko.java.mod.dbsrc.config.ds.excel;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordIterator;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;

public class ExcelRecordIterator extends BasicRecordIterator {

	private File file;
	
	private ExcelFile excelFile;
	
	private Workbook workbook;
	
	private Sheet sheet;
	
	private int currentRow;
	
	public ExcelRecordIterator( ExcelFile excelFile, File file ) {
		super( excelFile.getDescriptor() );
		this.file = file;
		this.excelFile = excelFile;
		this.currentRow = -1;
	}
	
	public boolean end()  throws DbsrcException {
		boolean ok = true;
		try {
			this.workbook.close();
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	public boolean hasNext()  throws DbsrcException {
		boolean ok = true;
		try {
			ok = ( this.currentRow < this.sheet.getRows() );
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	public Record next()  throws DbsrcException {
		Field[] fl = null;
		try {
			LogFacade.getLog().debug( "ExcelRecordIterator.next() "+this.currentRow+" / "+this.sheet.getRows() );
			Cell[] row = this.sheet.getRow( this.currentRow );
			fl = new Field[ row.length ];
			for ( int k=0; k<row.length; k++ ) {
				String field = row[k].getContents();
				fl[k] = this.excelFile.getDescriptor()[k].getField( field );
				LogFacade.getLog().debug( "record : value : "+k+" : "+field+" : "+fl[k] );
			}
		} catch (Exception e) {
			String rawData = null;
			try {
				// try to get raw data on record
				rawData = String.valueOf( this.sheet.getRow( this.currentRow ) );
			} catch (Exception e1) {}
			String message = e.getMessage() + " [raw record="+rawData+"]";
			throw ( new DbsrcException( message , e ) );
		} finally {
			this.currentRow++;
		}
		return Record.newRecord( fl, this.excelFile.getDescriptor() );
	}

	public boolean start()  throws DbsrcException {
		boolean ok = true;
		try {
			this.workbook = Workbook.getWorkbook( this.file );
			this.sheet = this.workbook.getSheet( this.excelFile.getSheetIndex() );
			this.currentRow = this.excelFile.getHeaderHeight();
			LogFacade.getLog().debug( "ExcelRecordIterator.start() row number : "+this.sheet.getRows() );
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

}
