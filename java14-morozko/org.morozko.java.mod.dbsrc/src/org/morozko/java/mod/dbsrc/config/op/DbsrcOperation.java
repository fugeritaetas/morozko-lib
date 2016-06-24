package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.Dbsrc;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public interface DbsrcOperation {

	public static final int EXIT_CODE_OK = 0;
	public static final int EXIT_CODE_KO = -1;
	
	public void configure( String name, DbsrcDataSource from, DbsrcDataSource to, ConfigTag config ) throws ConfigException;
	
	public int apply() throws DbsrcException;
	
	public String getName();
	
	public String getDescription();
	
	public Dbsrc getDbsrc();
	
	public void setDbsrc(Dbsrc dbsrc);
	
}
