package org.morozko.java.mod.dbsrc.config.ds.excel;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;

public class ExcelRecordHandler extends BasicRecordHandler {

	public ExcelRecordHandler( WritableWorkbook ww, WritableSheet sheet, ConfigTag config, int counter) {
		super( config );
		this.ww = ww;
		this.sheet = sheet;
		this.counter = counter;
	}
	
	private WritableWorkbook ww;
	
	private WritableSheet sheet;
	int counter;
	
	@Override
	public int release() throws DbsrcException {
		try {
			this.ww.write();
			this.ww.close();
		} catch (Exception e) {
			throw ( new DbsrcException( e ) );
		}
		return this.counter;
	}

	@Override
	public void setRecord(Record record) throws DbsrcException {
		for ( int k=0; k<record.length(); k++ ) {
			Field f = record.getField( k );
			String current = "";
			if ( f.getData() != null ) {
				current = String.valueOf( f.getData() );
			}
			try {
				this.sheet.addCell( new jxl.write.Label( k, this.counter, current ) );
			} catch (Exception e) {
				throw ( new DbsrcException( e ) );
			}
		}
		this.counter++;
	}

	
	
}
