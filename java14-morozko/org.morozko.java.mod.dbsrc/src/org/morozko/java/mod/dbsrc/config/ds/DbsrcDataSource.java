package org.morozko.java.mod.dbsrc.config.ds;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.Dbsrc;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.RecordHandler;
import org.morozko.java.mod.dbsrc.data.RecordIterator;


public interface DbsrcDataSource {

	public void configure( ConfigTag config ) throws ConfigException;
	
	public RecordIterator getRecords( ConfigTag config ) throws DbsrcException;

	public RecordHandler setRecords( ConfigTag config ) throws DbsrcException;
	
	public Dbsrc getDbsrc();
	
	public void setDbsrc(Dbsrc dbsrc);
	
}
