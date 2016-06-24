package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public class CetusMappingOperation extends BasicDbsrcOperation {

	@Override
	public int apply() throws DbsrcException {
		return super.apply();
	}

	@Override
	public void configure(String name, DbsrcDataSource from, DbsrcDataSource to, ConfigTag config) throws ConfigException {
		super.configure(name, from, to, config);
	}

}
