package org.morozko.java.mod.dbsrc.config.ds.jdbc;

import org.morozko.java.mod.dbsrc.data.BasicColumnDescriptor;

public class JdbcColumn extends BasicColumnDescriptor {

	private int jdbcType;
	
	private int jdbcLength;
	
	private String jdbcTypeName;
	
	public JdbcColumn(String name, int type, int jdbcType, String jdbcTypeName, int jdbcLength ) {
		super(name, type);
		this.jdbcType = jdbcType;
		this.jdbcTypeName = jdbcTypeName;
		this.jdbcLength = jdbcLength;
	}

	public int getJdbcType() {
		return jdbcType;
	}

	public int getJdbcLength() {
		return jdbcLength;
	}

	public String getJdbcTypeName() {
		return jdbcTypeName;
	}

}
