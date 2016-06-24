package org.morozko.java.mod.dbsrc.config.op;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.dbsrc.config.ConfigTag;
import org.morozko.java.mod.dbsrc.config.Dbsrc;
import org.morozko.java.mod.dbsrc.config.ds.DbsrcDataSource;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;

public class BasicDbsrcOperation extends BasicLogObject implements DbsrcOperation {

	private String description;
	
	public String getDescription() {
		return this.description;
	}

	private String name;
	
	private DbsrcDataSource fromDs;
	
	private DbsrcDataSource toDs;
	
	private ConfigTag config;
	
	private ConfigTag fromDsConfigTag;
	
	private ConfigTag toDsConfigTag;
	
	
	
	public ConfigTag getFromDsConfigTag() {
		return fromDsConfigTag;
	}

	public ConfigTag getToDsConfigTag() {
		return toDsConfigTag;
	}

	public ConfigTag getConfig() {
		return config;
	}

	public int apply() throws DbsrcException {
		return -1;
	}

	public void configure(String name, DbsrcDataSource from, DbsrcDataSource to, ConfigTag config ) throws ConfigException {
		this.name = name;
		this.fromDs = from;
		this.toDs = to;
		this.config = config;
		this.fromDsConfigTag = config.getConfigMap().getConfig( "from-ds" );
		this.toDsConfigTag = config.getConfigMap().getConfig( "to-ds" );
		this.description = config.getProps().getProperty( "description", "no description provided" ).trim();
	}

	public String getName() {
		return name;
	}

	public DbsrcDataSource getFromDs() {
		return fromDs;
	}

	public DbsrcDataSource getToDs() {
		return toDs;
	}
	
	private Dbsrc dbsrc;

	public Dbsrc getDbsrc() {
		return dbsrc;
	}

	public void setDbsrc(Dbsrc dbsrc) {
		this.dbsrc = dbsrc;
	}

}
