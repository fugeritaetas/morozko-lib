package org.morozko.java.mod.parser.ds.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.parser.model.MetadataDescription;

public class JdbcMetadata implements MetadataDescription {

	public JdbcMetadata() {
		this.recordDescriptionList = new ArrayList<JdbcRecordDescription>();
	}
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private List<JdbcRecordDescription> recordDescriptionList;

	public List<JdbcRecordDescription> getRecordDescriptionList() {
		return recordDescriptionList;
	}
	
	private ConnectionFactory connectionFactory;
	
	private String sql;

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
