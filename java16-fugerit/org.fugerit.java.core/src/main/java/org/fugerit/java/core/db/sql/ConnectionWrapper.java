/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

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
 * @(#)ConnectionWrapper.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.sql
 * @creation   : 05/apr/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ConnectionWrapper implements Connection {
	
	private Connection wrappedConnection;
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
		
		this.getWrappedConnection().clearWarnings();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#close()
	 */
	public void close() throws SQLException {
		
		this.getWrappedConnection().close();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#commit()
	 */
	public void commit() throws SQLException {
		
		this.getWrappedConnection().commit();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#createStatement()
	 */
	public Statement createStatement() throws SQLException {
		
		return this.getWrappedConnection().createStatement();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#createStatement(int, int, int)
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		
		return this.getWrappedConnection().createStatement(resultSetType, resultSetConcurrency,
				resultSetHoldability);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#createStatement(int, int)
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		
		return this.getWrappedConnection().createStatement(resultSetType, resultSetConcurrency);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getAutoCommit()
	 */
	public boolean getAutoCommit() throws SQLException {
		
		return this.getWrappedConnection().getAutoCommit();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getCatalog()
	 */
	public String getCatalog() throws SQLException {
		
		return this.getWrappedConnection().getCatalog();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getHoldability()
	 */
	public int getHoldability() throws SQLException {
		
		return this.getWrappedConnection().getHoldability();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getMetaData()
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		
		return this.getWrappedConnection().getMetaData();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getTransactionIsolation()
	 */
	public int getTransactionIsolation() throws SQLException {
		
		return this.getWrappedConnection().getTransactionIsolation();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getTypeMap()
	 */
	public Map getTypeMap() throws SQLException {
		
		return this.getWrappedConnection().getTypeMap();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {
		
		return this.getWrappedConnection().getWarnings();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#isClosed()
	 */
	public boolean isClosed() throws SQLException {
		
		return this.getWrappedConnection().isClosed();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#isReadOnly()
	 */
	public boolean isReadOnly() throws SQLException {
		
		return this.getWrappedConnection().isReadOnly();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#nativeSQL(java.lang.String)
	 */
	public String nativeSQL(String sql) throws SQLException {
		
		return this.getWrappedConnection().nativeSQL(sql);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareCall(java.lang.String, int, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		
		return this.getWrappedConnection().prepareCall(sql, resultSetType, resultSetConcurrency,
				resultSetHoldability);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareCall(java.lang.String, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		
		return this.getWrappedConnection().prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareCall(java.lang.String)
	 */
	public CallableStatement prepareCall(String sql) throws SQLException {
		
		return this.getWrappedConnection().prepareCall(sql);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareStatement(java.lang.String, int, int, int)
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		
		return this.getWrappedConnection().prepareStatement(sql, resultSetType, resultSetConcurrency,
				resultSetHoldability);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareStatement(java.lang.String, int, int)
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		
		return this.getWrappedConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareStatement(java.lang.String, int)
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		
		return this.getWrappedConnection().prepareStatement(sql, autoGeneratedKeys);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareStatement(java.lang.String, int[])
	 */
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		
		return this.getWrappedConnection().prepareStatement(sql, columnIndexes);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareStatement(java.lang.String, java.lang.String[])
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		
		return this.getWrappedConnection().prepareStatement(sql, columnNames);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#prepareStatement(java.lang.String)
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		
		return this.getWrappedConnection().prepareStatement(sql);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#releaseSavepoint(java.sql.Savepoint)
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		
		this.getWrappedConnection().releaseSavepoint(savepoint);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#rollback()
	 */
	public void rollback() throws SQLException {
		
		this.getWrappedConnection().rollback();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#rollback(java.sql.Savepoint)
	 */
	public void rollback(Savepoint savepoint) throws SQLException {
		
		this.getWrappedConnection().rollback(savepoint);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setAutoCommit(boolean)
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		
		this.getWrappedConnection().setAutoCommit(autoCommit);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setCatalog(java.lang.String)
	 */
	public void setCatalog(String catalog) throws SQLException {
		
		this.getWrappedConnection().setCatalog(catalog);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setHoldability(int)
	 */
	public void setHoldability(int holdability) throws SQLException {
		
		this.getWrappedConnection().setHoldability(holdability);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean readOnly) throws SQLException {
		
		this.getWrappedConnection().setReadOnly(readOnly);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setSavepoint()
	 */
	public Savepoint setSavepoint() throws SQLException {
		
		return this.getWrappedConnection().setSavepoint();
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setSavepoint(java.lang.String)
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		
		return this.getWrappedConnection().setSavepoint(name);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setTransactionIsolation(int)
	 */
	public void setTransactionIsolation(int level) throws SQLException {
		
		this.getWrappedConnection().setTransactionIsolation(level);
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.jdbc.driver.ext.oracle.ConnWrapper#setTypeMap(java.util.Map)
	 */
	public void setTypeMap(Map arg0) throws SQLException {
		
		this.getWrappedConnection().setTypeMap(arg0);
	}

	/**
	 * @return the wrappedConnection
	 */
	public Connection getWrappedConnection() {
		return wrappedConnection;
	}

	/**
	 * @param wrappedConnection the wrappedConnection to set
	 */
	public void setWrappedConnection(Connection wrappedConnection) {
		this.wrappedConnection = wrappedConnection;
	}

	/**
	 * <p>Creats a new instance of ConnectionWrapper.</p>
	 *
	 * @param wrappedConnection
	 */
	public ConnectionWrapper(Connection wrappedConnection) {
		super();
		this.wrappedConnection = wrappedConnection;
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		boolean res = false;
		if ( arg0.getClass().getName().equalsIgnoreCase( this.wrappedConnection.getClass().getName() ) ) {
			res = true;
		}
		return res;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return null;
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return this.wrappedConnection.createArrayOf(typeName, elements);
	}

	public Blob createBlob() throws SQLException {
		return wrappedConnection.createBlob();
	}

	public Clob createClob() throws SQLException {
		return wrappedConnection.createClob();
	}

	public NClob createNClob() throws SQLException {
		return wrappedConnection.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		return wrappedConnection.createSQLXML();
	}

	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return wrappedConnection.createStruct(typeName, attributes);
	}

	public Properties getClientInfo() throws SQLException {
		return wrappedConnection.getClientInfo();
	}

	public String getClientInfo(String name) throws SQLException {
		return wrappedConnection.getClientInfo(name);
	}

	public boolean isValid(int timeout) throws SQLException {
		return wrappedConnection.isValid(timeout);
	}

	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		wrappedConnection.setClientInfo(properties);
	}

	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		wrappedConnection.setClientInfo(name, value);
	}

	@Override
	public void abort(Executor arg0) throws SQLException {
		this.getWrappedConnection().abort( arg0 );
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return this.getWrappedConnection().getNetworkTimeout();
	}

	@Override
	public String getSchema() throws SQLException {
		return this.getWrappedConnection().getSchema();
	}

	@Override
	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		this.getWrappedConnection().setNetworkTimeout( arg0 , arg1 );
		
	}

	@Override
	public void setSchema(String arg0) throws SQLException {
		this.getWrappedConnection().setSchema( arg0 );
	}

	

}