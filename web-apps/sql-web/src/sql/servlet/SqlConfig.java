package sql.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.morozko.java.mod.db.connect.ConnectionFactory;

public class SqlConfig {

	private ConnectionFactory connectionFactory;
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	private List<SqlQuery> listQuery;
	
	private Map<String, SqlQuery> mapQuery;
	
	public SqlConfig() {
		this.listQuery = new ArrayList<SqlQuery>();
		this.mapQuery = new HashMap<String, SqlQuery>();
	}
	
	public void addQuery( SqlQuery query ) {
		this.listQuery.add( query );
		this.mapQuery.put( query.getName() , query );
	}
	
	public Collection<SqlQuery> getQueryCollection() {
		return this.listQuery;
	}
	
	public SqlQuery getQuery( String name ) {
		return this.mapQuery.get( name );
	
	}
	
}
