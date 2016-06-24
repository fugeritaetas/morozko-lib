package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

public class CommandOperation extends BasicDbsrcOperation {
	
	@Override
	public int apply() throws DbsrcException {
		int result = EXIT_CODE_OK;
		RecordIterator ri = this.getFromDs().getRecords( this.getFromDsConfigTag() );
		if ( ri.hasNext() ) {
			result = EXIT_CODE_KO;
		}
		return result;
	}

	@Override
	public void configure(String name, DbsrcDataSource from, DbsrcDataSource to, ConfigTag config) throws ConfigException {
		super.configure(name, from, to, config);
	}

}
