/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)ConnectionFactoryPool.java
 *
 * @project     : org.morozko.java.mod.db
 * @package     : org.morozko.java.mod.db.connect.pool
 * @creation	: 21/nov/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.db.connect.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.db.connect.ConnectionFacadeWrapper;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.sql.wrapper.ConnectionWrapper;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class ConnectionFactoryPool extends ConnectionFacadeWrapper {

	public static final int DEFAULT_MAX_CONNECTIONS = 30;
	
	public static final int DEFAULT_IDLE_CONNECTIONS = 10;
	
	public static final int DEFAULT_MIN_CONNECTIONS = 5;
	
	public static ConnectionFactory newFactory( ConnectionFactory cf ) throws DAOException {
		return newFactory( cf, DEFAULT_MIN_CONNECTIONS, DEFAULT_MAX_CONNECTIONS, DEFAULT_IDLE_CONNECTIONS );
	}
	
	public static ConnectionFactory newFactory( ConnectionFactory cf, int minConnection, int maxConnection, int idlConnection ) throws DAOException {
		return new ConnectionFactoryPool( cf, minConnection, maxConnection, idlConnection );
	}
	
	/**
	 * @param wrapperConnectionFactory
	 * @throws DAOException 
	 */
	private ConnectionFactoryPool(ConnectionFactory wrapperConnectionFactory, int minConnection, int maxConnection, int idlConnection ) throws DAOException {
		super(wrapperConnectionFactory);
		this.minConnection = minConnection;
		this.maxConnection = maxConnection;
		this.idlConnection = idlConnection;
		this.connList = new ArrayList();
		this.lendList = new ArrayList();
		this.released = false;
		for ( int k=0; k<this.minConnection; k++ ) {
			this.connList.add( this.getWrapperConnectionFactory().getConnection() );
		}		
	}
	
	private int maxConnection;
	
	private int minConnection;
	
	private int idlConnection;
	
	private ConnectionFactory wrapped;
	
	private List connList;
	
	private List lendList;
	
	private boolean released;
	
	private int totalOpenedConnections() {
		return this.connList.size()+this.lendList.size();
	}
	
	private ConnectionWrapperPool getOne() {
		Connection conn = (Connection) this.connList.remove( 0 );
		this.lendList.add( conn );
		return new ConnectionWrapperPool( conn, this );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.connect.ConnectionFactory#getConnection()
	 */
	public Connection getConnection() throws DAOException {
		Connection conn = null;
		if ( released ) {
			throw ( new DAOException ( "This pool has been released" ) );
		} else {
			if ( !this.connList.isEmpty() ) {
				conn = getOne();
			} else {
				if ( this.totalOpenedConnections()<this.maxConnection ) {
					this.connList.add( this.wrapped.getConnection() );
					conn = this.getOne();
				} else {
					throw ( new DAOException( "Maximum connections limit reached" ) );
				}
			}			
		}
		return conn;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.connect.ConnectionFactory#release()
	 */
	public void release() throws DAOException {
		this.wrapped.release();
		this.released = true;
		this.maxConnection = 0;
		this.minConnection = 0;
		this.idlConnection = 0;
		List list = new ArrayList();
		list.addAll( this.connList );
		this.connList.clear();
		this.connList = null;
		list.addAll( this.lendList );
		this.lendList.clear();
		this.lendList = null;
		for ( int k=0; k<list.size(); k++ ) {
			try {
				((Connection)list.get(k)).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void give( Connection conn ) throws SQLException {
		if ( this.lendList.remove( conn ) ) {
			this.connList.add( conn );	
		} else {
			throw ( new SQLException( "Connection is not part of this pool" ) );
		}
	}
	
	/**
	 * <p>ConnectionFactoryPool : Inner class for handling a connection of the connection pool</p>
	 *
	 * @author Morozko
	 *
	 */
	class ConnectionWrapperPool extends ConnectionWrapper {

		private ConnectionFactoryPool factory;
		
		/**
		 * @param wrappedConnection
		 */
		public ConnectionWrapperPool(Connection wrappedConnection, ConnectionFactoryPool factory) {
			super(wrappedConnection);
			this.factory = factory;
		}
		
		/* (non-Javadoc)
		 * @see java.sql.Connection#close()
		 */
		public void close() throws SQLException {
			this.factory.give( this.getWrappedConnection() );
		}
		
	}

	private Object oggetto;
	
}

