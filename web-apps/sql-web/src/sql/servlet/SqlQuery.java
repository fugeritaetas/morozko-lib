package sql.servlet;

import java.util.HashMap;
import java.util.Map;

public class SqlQuery {

	public SqlQuery() {
		this.updateMap = new HashMap<String, SqlUpdate>();
	}
	
	public Map<String, SqlUpdate> getUpdateMap() {
		return updateMap;
	}

	private String name;
	
	private String description;
	
	private String sql;
	
	private String type;

	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private Map<String, SqlUpdate> updateMap;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
