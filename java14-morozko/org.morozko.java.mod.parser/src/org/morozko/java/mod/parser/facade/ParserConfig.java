package org.morozko.java.mod.parser.facade;

import java.util.HashMap;
import java.util.Map;

import org.morozko.java.mod.parser.ds.DataSource;
import org.morozko.java.mod.parser.filter.FilterChain;
import org.morozko.java.mod.parser.filter.RecordFilter;

public class ParserConfig {

	public ParserConfig() {
		this.mapDS = new HashMap<String, DataSource>();
		this.mapChain = new HashMap<String, FilterChain>();
		this.mapFilter = new HashMap<String, RecordFilter>();
	}
	
	private Map<String, DataSource> mapDS;
	
	private Map<String, FilterChain> mapChain;
	
	private Map<String, RecordFilter> mapFilter;
	
	public Map<String, FilterChain> getMapChain() {
		return mapChain;
	}

	public Map<String, RecordFilter> getMapFilter() {
		return mapFilter;
	}

	public void addDS( String id, DataSource ds ) {
		this.mapDS.put( id, ds );
	}
	
	public DataSource getDS( String id ) {
		return this.mapDS.get( id );
	}
	
}
