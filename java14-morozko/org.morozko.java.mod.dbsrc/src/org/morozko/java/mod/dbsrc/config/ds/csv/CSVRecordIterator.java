package org.morozko.java.mod.dbsrc.config.ds.csv;

import java.io.File;
import java.io.FileReader;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordIterator;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;

import com.csvreader.CsvReader;

public class CSVRecordIterator extends BasicRecordIterator {

	private File file;
	
	private CSVFile csvFile;
	
	private CsvReader csvReader;
	
	public CSVRecordIterator( CSVFile csvFile, File file ) {
		super( csvFile.getDescriptor() );
		this.file = file;
		this.csvFile = csvFile;
	}
	
	public boolean end()  throws DbsrcException {
		boolean ok = true;
		try {
			this.csvReader.close();
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	public boolean hasNext()  throws DbsrcException {
		boolean ok = true;
		try {
			ok = this.csvReader.readRecord();
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

	public Record next()  throws DbsrcException {
		Field[] fl = null;
		try {
			String[] record= this.csvReader.getValues();
			fl = new Field[ record.length ];
			for ( int k=0; k<record.length; k++ ) {
				String field = record[k];
				fl[k] = this.csvFile.getDescriptor()[k].getField( field );
				LogFacade.getLog().debug( "record : value : "+k+" : "+field+" : "+fl[k] );
			}
		} catch (Exception e) {
			String rawData = null;
			try {
				// try to get raw data on record
				rawData = this.csvReader.getRawRecord();
			} catch (Exception e1) {}
			String message = e.getMessage() + " [raw record="+rawData+"]";
			throw ( new DbsrcException( message , e ) );
		}

		return Record.newRecord( fl, this.csvFile.getDescriptor() );
	}

	public boolean start()  throws DbsrcException {
		boolean ok = true;
		try {
			FileReader reader = new FileReader( this.file );
			this.csvReader = new CsvReader( reader, this.csvFile.getDelimiter().charAt( 0 ) );
			int hs = this.csvFile.getHeaderHeight();
			while ( hs > 0 ) {
				this.csvReader.readHeaders();
				hs--;
			}
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return ok;
	}

}
