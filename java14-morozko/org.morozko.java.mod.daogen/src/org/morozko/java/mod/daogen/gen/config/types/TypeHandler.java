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
 * @(#)TypeHandler.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen.gen.config.types
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen.gen.config.types;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class TypeHandler {

	public static final TypeHandler TH_DAOID = new TypeHandler( "org.morozko.java.mod.db.dao.DAOID", "org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong(\"{0}\") )", "formatObject({0})", "toDAOID({0})" );
	public static final TypeHandler TH_BIGDECIMAL = new TypeHandler( "java.math.BigDecimal", "rs.getBigDecimal(\"{0}\")", "formatObject({0})", "toBigDecimal({0})" );
	public static final TypeHandler TH_BIGINTEGER = new TypeHandler( "java.math.BigInteger", "rs.getBigInteger(\"{0}\")", "formatObject({0})", "toBigInteger({0})" );
	public static final TypeHandler TH_DAOIDNULLZERO = new TypeHandler( "org.morozko.java.mod.db.dao.DAOID", "org.morozko.java.mod.db.dao.DAOID.valueOfNullZero( rs.getLong(\"{0}\") )", "formatObject({0})", "toDAOID({0})" );
	public static final TypeHandler TH_DAOIDAUTO = new TypeHandler( "org.morozko.java.mod.db.dao.DAOID", "org.morozko.java.mod.db.dao.DAOID.valueOf( rs.getLong(\"{0}\") )", "formatObject({0})", "toDAOID({0})" );
	public static final TypeHandler TH_NUMERIC = new TypeHandler( "Double", "new Double( rs.getDouble(\"{0}\") )", "formatObject({0})", "toDouble({0})" );
	public static final TypeHandler TH_BIGINT = new TypeHandler( "Long", "new Long( rs.getLong(\"{0}\" ) )", "formatObject({0})", "toLong({0})" );
	public static final TypeHandler TH_INTEGER = new TypeHandler( "Integer", "new Integer( rs.getInt(\"{0}\") )", "formatObject({0})", "toInteger({0})" );
	public static final TypeHandler TH_SMALLINT = new TypeHandler( "Short", "new Short( rs.getShort(\"{0}\") )", "formatObject({0})", "toShort({0})" );
	public static final TypeHandler TH_TINYINT = new TypeHandler( "Byte", "new Byte( rs.getByte(\"{0}\") )", "formatObject({0})", "toByte({0})" );
	public static final TypeHandler TH_DOUBLE = new TypeHandler( "Double", "new Double( rs.getDouble(\"{0}\") )", "formatObject({0})", "toDouble({0})" );
	public static final TypeHandler TH_FLOAT = new TypeHandler( "Float", "new Float( rs.getFloat(\"{0}\") )", "formatObject({0})", "toLong({0})" );
	public static final TypeHandler TH_DATE = new TypeHandler( "java.sql.Date", "rs.getDate(\"{0}\")", "formatObject({0})", "toDate({0})" );
	public static final TypeHandler TH_TIME = new TypeHandler( "java.sql.Time", "rs.getTime(\"{0}\")", "formatObject({0})", "toTime({0})" );
	public static final TypeHandler TH_TIMESTAMP = new TypeHandler( "java.sql.Timestamp", "rs.getTimestamp(\"{0}\")", "formatObject({0})", "toTimestamp({0})" );
	public static final TypeHandler TH_VARCHAR = new TypeHandler( "String", "rs.getString(\"{0}\")", "formatObject({0})", "toString({0})" );
	public static final TypeHandler TH_CHAR = new TypeHandler( "String", "rs.getString(\"{0}\")", "formatObject({0})", "toString({0})" );
	public static final TypeHandler TH_BOOLEAN = new TypeHandler( "Boolean", "Boolean.valueOf( rs.getBoolean(\"{0}\") )", "formatObject({0})", "toBoolean({0})" );
	public static final TypeHandler TH_BIT = new TypeHandler( "Boolean", "Boolean.valueOf(  rs.getBoolean(\"{0}\") )", "formatObject({0})", "toBoolean({0})" );
	public static final TypeHandler TH_BLOB = new TypeHandler( "org.morozko.java.mod.db.dao.types.BlobData", "org.morozko.java.mod.db.dao.types.BlobData.valueOf( rs.getBlob(\"{0}\") )", "formatObject({0})", "toBlobData({0})" );
	public static final TypeHandler TH_CLOB = new TypeHandler( "java.sql.Clob", "rs.getClob(\"{0}\")", "formatObject({0})", "toClob({0})" );

	public static Map HANDLERS = new HashMap();
	static {
		HANDLERS.put( SQLType.DAOID, TH_DAOID );
		HANDLERS.put( SQLType.DAOIDNULLZERO, TH_DAOIDNULLZERO );
		HANDLERS.put( SQLType.DAOIDAUTO, TH_DAOIDAUTO );
		HANDLERS.put( SQLType.BIGDECIMAL, TH_BIGDECIMAL );
		HANDLERS.put( SQLType.BIGINTEGER, TH_BIGINTEGER );
		HANDLERS.put( SQLType.NUMERIC, TH_NUMERIC );
		HANDLERS.put( SQLType.BIGINT, TH_BIGINT );
		HANDLERS.put( SQLType.INTEGER, TH_INTEGER );
		HANDLERS.put( SQLType.SMALLINT, TH_SMALLINT );
		HANDLERS.put( SQLType.TINYINT, TH_TINYINT );
		HANDLERS.put( SQLType.DOUBLE, TH_DOUBLE );
		HANDLERS.put( SQLType.DECIMAL, TH_DOUBLE );
		HANDLERS.put( SQLType.FLOAT, TH_FLOAT );
		HANDLERS.put( SQLType.DATE, TH_DATE );
		HANDLERS.put( SQLType.TIME, TH_TIME );
		HANDLERS.put( SQLType.TIMESTAMP, TH_TIMESTAMP );
		HANDLERS.put( SQLType.VARCHAR, TH_VARCHAR );
		HANDLERS.put( SQLType.LONGVARCHAR, TH_VARCHAR );
		HANDLERS.put( SQLType.CHAR, TH_CHAR );
		HANDLERS.put( SQLType.BOOLEAN, TH_BOOLEAN );
		HANDLERS.put( SQLType.BIT, TH_BIT );
		HANDLERS.put( SQLType.BLOB, TH_BLOB );
		HANDLERS.put( SQLType.LONGVARBINARY, TH_BLOB );
		HANDLERS.put( SQLType.CLOB, TH_CLOB );
	}
	
	private String javaType;
	private MessageFormat resultSetExtract;
	private MessageFormat modelToBean;
	private MessageFormat beanToModel;

	/**
	 * @param javaType
	 * @param resultSetExtract
	 * @param modelToBean
	 * @param beanToModel
	 */
	public TypeHandler(String javaType, String resultSetExtract, String modelToBean, String beanToModel) {
		this.javaType = javaType;
		this.resultSetExtract = new MessageFormat( resultSetExtract );
		this.modelToBean = new MessageFormat( modelToBean );
		this.beanToModel = new MessageFormat( beanToModel );
	}

	public String getJavaFieldName( String sqlFieldName ) {
		String[] split = sqlFieldName.split( "_" );
		String javaFieldName = split[0];
		for ( int k=1; k<split.length; k++ ) {
			javaFieldName+= split[k].substring( 0, 1 ).toUpperCase()+split[k].substring( 1 );
		}
		if ( javaFieldName.equals( "class" ) ) {
			javaFieldName+="Field";
		}
		return javaFieldName;
	}

	public String getResultSetExtract( String sqlFieldName ) {
		Object[] args = {  sqlFieldName };
		return this.resultSetExtract.format( args );
	}
	
	public String getBeanToModel( String sqlFieldName ) {
		Object[] args = { getJavaFieldName( sqlFieldName ) };
		return this.beanToModel.format( args );
	}
	
	public String getModelToBean( String sqlFieldName ) {
		Object[] args = { getJavaFieldName( sqlFieldName ) };
		return this.modelToBean.format( args );
	}

	/**
	 * @return Restituisce il valore di javaType.
	 */
	public String getJavaType() {
		return javaType;
	}
	
}
