/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*****************************************************************/
/*
 * @(#)SQLType.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config.types
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config.types;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class SQLType {

	public static final SQLType DAOID = new SQLType( "id", Integer.MIN_VALUE );
	public static final SQLType DAOIDNULLZERO = new SQLType( "idnz", Integer.MIN_VALUE+1 );
	public static final SQLType DAOIDAUTO = new SQLType( "idauto", Integer.MIN_VALUE+2 );
	public static final SQLType BIGINTEGER = new SQLType( "biginteger", Integer.MIN_VALUE+3 );
	public static final SQLType BIGDECIMAL = new SQLType( "bigdecimal", Integer.MIN_VALUE+4 );
	public static final SQLType NUMERIC = new SQLType( "numeric", Types.NUMERIC );
	public static final SQLType BIGINT = new SQLType( "bigint", Types.BIGINT );
	public static final SQLType INTEGER = new SQLType( "integer", Types.INTEGER );
	public static final SQLType SMALLINT = new SQLType( "smallint", Types.SMALLINT );
	public static final SQLType TINYINT = new SQLType( "tinyint", Types.TINYINT );
	public static final SQLType DOUBLE = new SQLType( "double", Types.DOUBLE );
	public static final SQLType DECIMAL = new SQLType( "double", Types.DECIMAL );
	public static final SQLType FLOAT = new SQLType( "float", Types.FLOAT );
	public static final SQLType DATE = new SQLType( "date", Types.DATE );
	public static final SQLType TIME = new SQLType( "time", Types.TIME );
	public static final SQLType TIMESTAMP = new SQLType( "timestamp", Types.TIMESTAMP );
	public static final SQLType VARCHAR = new SQLType( "varchar", Types.VARCHAR );
	public static final SQLType LONGVARCHAR = new SQLType( "varchar", Types.LONGVARCHAR );
	public static final SQLType CHAR = new SQLType( "char", Types.CHAR );
	public static final SQLType BOOLEAN = new SQLType( "boolean", Types.BOOLEAN );
	public static final SQLType BIT = new SQLType( "bit", Types.BIT );
	public static final SQLType BLOB = new SQLType( "blob", Types.BLOB );
	public static final SQLType CLOB = new SQLType( "clob", Types.CLOB );
	public static final SQLType LONGVARBINARY = new SQLType( "blob", Types.LONGVARBINARY );
	
	private static void add( SQLType type, Map map1, Map map2 ) {
		map1.put( type.getTypeName(), type );
		map2.put( type.getSqlType(), type );
	}
	public static final Map KNOWN_TYPES_NAME = new HashMap();
	
	public static final Map KNOWN_TYPES_SQL = new HashMap();
	static {
		add( DAOID, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( DAOIDAUTO, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( DAOIDNULLZERO, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( BIGDECIMAL, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( BIGINTEGER, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( INTEGER, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( BIGINT, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( SMALLINT, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( TINYINT, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( DOUBLE, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( DECIMAL, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( FLOAT, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( DATE, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( TIME, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( TIMESTAMP, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( VARCHAR, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( LONGVARCHAR, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( BOOLEAN, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( BIT, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( CHAR, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( NUMERIC, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( BLOB, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( LONGVARBINARY, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
		add( CLOB, KNOWN_TYPES_NAME, KNOWN_TYPES_SQL );
	}
	
	private String typeName;
	
	private Integer sqlType;

	/**
	 * @param typeName
	 * @param sqlType
	 */
	public SQLType(String typeName, int sqlType) {
		super();
		this.typeName = typeName;
		this.sqlType = new Integer( sqlType );
	}

	/**
	 * @return Restituisce il valore di sqlType.
	 */
	public Integer getSqlType() {
		return sqlType;
	}

	/**
	 * @param sqlType il valore di sqlType da impostare.
	 */
	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}

	/**
	 * @return Restituisce il valore di typeName.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName il valore di typeName da impostare.
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean eq = false;
		if (obj != null && obj instanceof SQLType) {
			SQLType s = (SQLType)obj;
			eq = s.getTypeName().equals( this.getTypeName() ) && s.getSqlType().equals( this.getSqlType() );
		}
		return eq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.sqlType.intValue();
	}
	
	public String toString() {
		return this.getClass().getName()+"["+this.getSqlType()+"]";
	}
	
}
