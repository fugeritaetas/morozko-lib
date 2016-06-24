/*******************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.db 

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
*******************************************************/
/*
 * @(#)AbstractDataFilter.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.filter
 * @creation   : 15/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.utils.DbUtils;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 *
 */
public class DefaultDataFilter extends BasicLogObject implements DataFilter {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.filter.DataFilter#applyFilter(org.morozko.java.mod.db.connect.ConnectionFactory, java.lang.String, java.lang.String[], java.lang.String[])
	 */
	public void applyFilter(ConnectionFactory cf, String table, String[] columnsName, String[] columnsKeyName ) throws DAOException {
		Connection conn = cf.getConnection();
		try {
			ResultSet rs = this.query( conn, table, columnsName, columnsKeyName );
			while ( rs.next() ) {
				Object[] columnsKeyValue = new Object[columnsKeyName.length];
				Object[] columnsValue = new Object[ columnsName.length ];
				for ( int k=0; k<columnsName.length; k++ ) {
					columnsValue[k] = rs.getObject( columnsName[k] );
				}
				for ( int k=0; k<columnsKeyName.length; k++ ) {
					columnsKeyValue[k] = rs.getObject( columnsKeyName[k] );
				}
				Object[] newValues = filter( columnsName, columnsValue );
				int result = update( cf , table, columnsName, newValues, columnsKeyName, columnsKeyValue );
				this.getLog().info( "update : "+result );
			}
		} catch (Exception e) {
			new DAOException( e );
		} finally {
			DbUtils.close( conn );
		}
	}

	public Object filter( String columnName, Object columnValue ) {
		Object obj = columnValue;
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.filter.DataFilter#filter(java.lang.String[], java.lang.String[])
	 */
	public Object[] filter(String[] columnsName, Object[] columnsValue) throws DAOException {
		Object[] result = new Object[ columnsValue.length ];
		for ( int k = 0; k<columnsValue.length; k++ ) {
			result[k] = filter( columnsName[k], columnsValue[k] );
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.filter.DataFilter#query(org.morozko.java.mod.db.connect.ConnectionFactory, java.lang.String[])
	 */
	public ResultSet query(Connection conn, String table, String[] columnsName , String[] columnsKeyName  ) throws DAOException {
		ResultSet columnsValue = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append( "SELECT " );
			query.append( columnsName[0] );
			for ( int k=1; k<columnsName.length; k++ ) {
				query.append( " , "+columnsName[k] );	
			}
			for ( int k=0; k<columnsKeyName.length; k++ ) {
				query.append( " , "+columnsKeyName[k] );	
			}
			query.append( " FROM "+table );
			this.getLog().debug( "query : "+query );
			PreparedStatement ps = conn.prepareStatement( query.toString() );
			columnsValue = ps.executeQuery();
		} catch (Exception e) {
			throw new DAOException( e );
		}
		return columnsValue;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.filter.DataFilter#update(org.morozko.java.mod.db.connect.ConnectionFactory, java.lang.String, java.lang.String[], java.lang.String[])
	 */
	public int update(ConnectionFactory cf, String table, String[] columnsName, Object[] columnsValue , String[] columnsKeyName, Object[] columnsKeyValue ) throws DAOException {
		int result = 0;
		Connection conn = cf.getConnection();
		try {
			StringBuffer query = new StringBuffer();
			query.append( "UPDATE "+table );
			query.append( " SET "+columnsName[0]+"=?" );
			for ( int k=1; k<columnsName.length; k++ ) {
				query.append( " , "+columnsName[k]+"=?" );	
			}
			query.append( " WHERE "+columnsKeyName[0]+"=?" );
			for ( int k=1; k<columnsKeyName.length; k++ ) {
				query.append( "AND "+columnsKeyName[k]+"=?" );	
			}
			this.getLog().debug( "query : "+query );
			PreparedStatement ps = conn.prepareStatement( query.toString() );
			int count = 1;
			for ( int k=0; k<columnsValue.length; k++ ) {
				ps.setObject( count , columnsValue[k] );
				this.getLog().debug( "setting param "+count+" : "+columnsValue[k] );
				count++;
			}
			for ( int k=0; k<columnsKeyValue.length; k++ ) {
				ps.setObject( count , columnsKeyValue[k] );
				this.getLog().debug( "setting param "+count+" : "+columnsKeyValue[k] );
				count++;
			}
			result = ps.executeUpdate();
		} catch (Exception e) {
			throw new DAOException( e );
		} finally {
			DbUtils.close( conn );
		}
		return result;
	}

}
