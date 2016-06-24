/*****************************************************************
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
*****************************************************************/
/*
 * @(#)TableDAO.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.util.table
 * @creation   : 22/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.dao.query.table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.connect.ConnectionFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.dao.BasicDAO;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.FieldList;
import org.morozko.java.mod.db.dao.RSExtractor;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryTableDAO extends BasicDAO {

	/**
	 * <p>Creates a new instance of TableDAO.</p>
	 *
	 * @param daoFactory
	 */
	public QueryTableDAO(BasicDAOFactory daoFactory) {
		super(daoFactory);
	}
	
	public List queryTable( String tableName, List params ) throws DAOException {
		StringBuffer query = new StringBuffer();
		query.append( " SELECT * FROM "+tableName+" WHERE 1=1 " );
		Iterator itParams = params.iterator();
		while ( itParams.hasNext() ) {
			QueryTableParam param = (QueryTableParam)itParams.next();
			query.append( " AND "+param.getColumnName()+" = ? " );
		}
		return this.query( query.toString(), params);
	}
	
	public List query( String query, List params ) throws DAOException {
		List list = this.newList();
		FieldList fl = this.newFieldList();
		Iterator itParams = params.iterator();
		while ( itParams.hasNext() ) {
			QueryTableParam param = (QueryTableParam)itParams.next();
			fl.addField( param.getColumnValue() );
		}
		this.loadAll(list, query, fl, TableRSE.INSTANCE);
		return list;
	}
	
	public static void main( String[] args ) {
		try {
			LogFacade.updateCurrentConfig( "log.level" , "6");
			ConnectionFactory cf = ConnectionFactoryImpl.newInstance( "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@26.2.225.58:1521:A", "secit", "secit" );
			
			ConnectionFacade.registerFactory( "def" , cf );
			
			BasicDAOFactory daoFactory = new BasicDAOFactory( cf );
			QueryTableDAO qtd = new QueryTableDAO( daoFactory );
			QueryTableParam p = new QueryTableParam();
			
			p.setColumnName( "cf" );
			p.setColumnValue( "75940254" );
			List l = new ArrayList();
//			l.add( p );
			List r = qtd.queryTable( "bilanci" , l);
			for ( int k=0; k<r.size(); k++ ) {
				QueryTableModel qtm = (QueryTableModel)r.get( k );
				System.out.println( qtm.getQueryColumn( "AVL30" ).getNumberValue() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}

class TableRSE implements RSExtractor {

	public static final TableRSE INSTANCE = new TableRSE();
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.dao.RSExtractor#extractNext(java.sql.ResultSet)
	 */
	public Object extractNext(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		QueryTableModel result = new QueryTableModel();
		for ( int k=1; k<=rsmd.getColumnCount(); k++ ) {
			int type = rsmd.getColumnType( k );
			String name = rsmd.getColumnName( k );
			if ( type == Types.VARCHAR || type == Types.CHAR ) {
				result.addColumn(name, new QueryTableColumn( rs.getString( k ), QueryTableColumn.TYPE_STRING ) );
			} else if ( type == Types.INTEGER ) {
				result.addColumn(name, new QueryTableColumn( new Integer( rs.getInt( k ) ), QueryTableColumn.TYPE_INTEGER ) );
			} else if ( type == Types.BIGINT ) {
				result.addColumn(name, new QueryTableColumn( new Long( rs.getLong( k ) ), QueryTableColumn.TYPE_LONG ) );
			} else if ( type == Types.FLOAT ) {
				result.addColumn(name, new QueryTableColumn( new Float( rs.getFloat( k ) ), QueryTableColumn.TYPE_FLOAT ) );
			} else if ( type == Types.DOUBLE || type == Types.NUMERIC ) {
				result.addColumn(name, new QueryTableColumn( new Double( rs.getDouble( k ) ), QueryTableColumn.TYPE_DOUBLE ) );
			} else if ( type == Types.DATE ) {
				result.addColumn(name, new QueryTableColumn( rs.getDate( k ) , QueryTableColumn.TYPE_DATE ) );
			} else if ( type == Types.TIME ) {
				result.addColumn(name, new QueryTableColumn( rs.getTime( k ) , QueryTableColumn.TYPE_TIME ) );
			} else if ( type == Types.TIMESTAMP ) {
				result.addColumn(name, new QueryTableColumn( rs.getTimestamp( k ) , QueryTableColumn.TYPE_TIMESTAMP ) );
			} else {
				throw ( new SQLException( "TableRSE : unhandled data type : "+type+" ("+rsmd.getColumnTypeName( k )+")" ) );
			}
		}
		return result;
	}


	
}
