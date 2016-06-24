package org.morozko.java.mod.dbsrc.config.ds;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;

public class DefaultDataSource extends BaseDataSource {

	@Override
	public void configure(ConfigTag config) throws ConfigException {

	}

	@Override
	public RecordIterator getRecords(ConfigTag config) throws DbsrcException {
		return null;
	}

	public RecordHandler setRecords(ConfigTag config) throws DbsrcException {
		return null;
	}

}
