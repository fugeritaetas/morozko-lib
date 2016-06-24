package org.morozko.java.mod.parser.ds.jdbc;

import org.morozko.java.mod.parser.model.FieldDescription;

public class JdbcFieldDescription implements FieldDescription {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private int length;
	
}
