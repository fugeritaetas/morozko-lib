package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.Record;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

public class SimpleMappingOperation extends BasicDbsrcOperation {
	
	public int apply() throws DbsrcException {
		int result = EXIT_CODE_OK;
		RecordIterator ri = this.getFromDs().getRecords( this.getFromDsConfigTag() );
		ri.start();
		RecordHandler rh = this.getToDs().setRecords( this.getToDsConfigTag() );
		while ( ri.hasNext() ) {
			Record record = ri.next();
			rh.setRecord( record ); 
		}
		ri.end();
		int recordHandled = rh.release();
		this.getLog().info( "processed records : "+recordHandled );
		return result;
	}

	public void configure( String name, DbsrcDataSource from, DbsrcDataSource to, ConfigTag config ) throws ConfigException {
		super.configure( name, from, to, config );
	}

}
