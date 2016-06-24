package org.morozko.java.mod.web.servlet.sql;

public class SqlUpdate {

	private String field;
	
	private String column;
	
	private String type;

	private String table;
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public SqlUpdate(String field, String column, String table, String type) {
		super();
		this.field = field;
		this.column = column;
		this.table = table;
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
