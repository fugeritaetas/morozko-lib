package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.Record;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

/**
 * <p>Load from a source to a destination, they must have compatibles columns</p>
 * 
 * @author Morozko
 *
 */
public class LoadOperation extends BasicDbsrcOperation {

	@Override
	public int apply() throws DbsrcException {
		int result = EXIT_CODE_OK;
		RecordIterator ri = this.getFromDs().getRecords( this.getFromDsConfigTag() );
		ri.start();
		RecordHandler rh = this.getToDs().setRecords( this.getToDsConfigTag() );
		int errorCount = 0;
		while ( ri.hasNext() ) {
			try {
				Record record = ri.next();
				try {
					rh.setRecord( record );
				} catch (Exception ed) {
					ed.printStackTrace( System.out ); 
					errorCount++;
					this.getDbsrc().getErrorLogger().log( "operation : "+this.getName()+" : Error on destination : "+ed.getMessage() );	
				}
			} catch (Exception es) {
				es.printStackTrace( System.out );
				errorCount++;
				this.getDbsrc().getErrorLogger().log( "operation : "+this.getName()+" : Error on source : "+es.getMessage() );
			}
		}
		ri.end();
		int recordHandled = rh.release();
		this.getLog().info( "processed records : "+recordHandled );
		if ( errorCount != 0 ) {
			result = errorCount; 
		}
		return result;
	}

	@Override
	public void configure(String name, DbsrcDataSource from, DbsrcDataSource to, ConfigTag config) throws ConfigException {
		super.configure(name, from, to, config);
	}

}
