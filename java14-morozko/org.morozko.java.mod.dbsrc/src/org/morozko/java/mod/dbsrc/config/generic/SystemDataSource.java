package org.morozko.java.mod.dbsrc.config.generic;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.BaseDataSource;
import org.morozko.java.mod.dbsrc.data.BasicRecordHandler;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

public class SystemDataSource extends BaseDataSource {

	public RecordHandler setRecords(ConfigTag config) throws DbsrcException {
		return new SystemRecordHandler( config );
	}

	@Override
	public RecordIterator getRecords(ConfigTag config) throws DbsrcException {
		return null;
	}

	@Override
	public void configure(ConfigTag config) throws ConfigException {

	}

}

class SystemRecordHandler extends BasicRecordHandler {

	private int count = 0;
	
	public SystemRecordHandler(ConfigTag config) {
		super(config);
	}

	@Override
	public int release() throws DbsrcException {
		return 0;
	}

	@Override
	public void setRecord(Record record) throws DbsrcException {
		count++;
		String[] data = new String[ record.length() ];
		System.out.println( "Record : "+count );
		for ( int k=0; k<record.length(); k++ ) {
			Field f = record.getField( k );
			System.out.print( "Field : " );
			if ( f.getData() == null ) {
				System.out.println( "" );
				data[k] = "";
			} else {
				System.out.println( String.valueOf( f.getData() ) );
			}
		}
		
	}
	
}