package org.fugerit.java.core.db.sql;

import java.sql.Date;

import org.fugerit.java.core.db.helpers.DAOID;

public class QueryParam {

	public static QueryParam newQueryParam( String name, Double param ) {
		return new QueryParam( name, param );
	}
	
	public static QueryParam newQueryParam( String name, Long param ) {
		return new QueryParam( name, param );
	}
	
	public static QueryParam newQueryParam( String name, Integer param ) {
		return new QueryParam( name, param );
	}
	
	public static QueryParam newQueryParam( String name, DAOID param ) {
		return new QueryParam( name, param );
	}
	
	public static QueryParam newQueryParam( String name, String param ) {
		return new QueryParam( name, param );
	}
	
	public static QueryParam newQueryParam( String name, Date param ) {
		return new QueryParam( name, param );
	}	
	
	private QueryParam(String name, Object param) {
		super();
		this.name = name;
		this.param = param;
	}

	private String name;
	
	private Object param;

	public String getName() {
		return name;
	}

	public Object getParam() {
		return param;
	}
	
}
