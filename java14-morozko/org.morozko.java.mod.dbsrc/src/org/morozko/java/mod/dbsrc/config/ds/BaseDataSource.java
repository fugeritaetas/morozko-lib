package org.morozko.java.mod.dbsrc.config.ds;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.Dbsrc;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.RecordIterator;
 
public abstract class BaseDataSource extends BasicLogObject implements DbsrcDataSource {

	private Dbsrc dbsrc;

	public Dbsrc getDbsrc() {
		return dbsrc;
	}

	public void setDbsrc(Dbsrc dbsrc) {
		this.dbsrc = dbsrc;
	}


	public abstract RecordIterator getRecords(ConfigTag config) throws DbsrcException;

	public abstract void configure(ConfigTag config) throws ConfigException;

}
