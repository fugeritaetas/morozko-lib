package org.morozko.java.mod.dbsrc.config.ds.csv;

import java.io.FileWriter;
import java.io.IOException;

import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;

import com.csvreader.CsvWriter;

public class CSVRecordHandler extends BasicRecordHandler {

	public CSVRecordHandler(CsvWriter writer, FileWriter fw, ConfigTag config) {
		super( config );
		this.writer = writer;
		this.fw = fw;
		this.counter = 0;
	}

	private CsvWriter writer;
	
	private FileWriter fw;

	int counter;
	
	@Override
	public int release() throws DbsrcException {
		try {
			this.writer.close();
			this.fw.close();
		} catch (IOException e) {
			throw ( new DbsrcException( e ) );
		}
		return this.counter;
	}

	@Override
	public void setRecord(Record record) throws DbsrcException {
		String[] data = new String[ record.length() ];
		for ( int k=0; k<record.length(); k++ ) {
			Field f = record.getField( k );
			if ( f.getData() == null ) {
				data[k] = "";
			} else {
				data[k] = String.valueOf( f.getData() );
			}
		}
		try {
			this.writer.writeRecord( data );
			this.counter++;
		} catch (IOException e) {
			throw ( new DbsrcException( e ) );
		}
	}

	
	
}
