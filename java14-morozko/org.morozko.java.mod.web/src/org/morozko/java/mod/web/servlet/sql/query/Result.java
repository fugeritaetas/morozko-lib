package org.morozko.java.mod.web.servlet.sql.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.web.servlet.sql.SqlQuery;


public class Result {
	
	private SqlQuery query;
	
	public SqlQuery getQuery() {
		return query;
	}

	public static Result query( ConnectionFactory cf, SqlQuery query ) throws Exception {
		Result result = new Result();
		Connection conn = cf.getConnection();
		try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( query.getSql() );
			ResultSetMetaData rsmd = rs.getMetaData();
			ArrayList header = new ArrayList();
			for ( int k=0; k<rsmd.getColumnCount(); k++ ) {
				header.add( rsmd.getColumnLabel( k+1 ) );
			}
			result.setHeader( header );
			while ( rs.next() ) {
				Row row = new Row( query );
				for ( int k=0; k<rsmd.getColumnCount(); k++ ) {
					row.add( new Cell( (String)header.get( k ) , String.valueOf( rs.getObject( k+1 ) ) ) );
				}
				result.getRows().add( row );
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
		result.query = query;
		return result;
	}
	
	public Result() {
		this.rows = new ArrayList();
	}
	
	public ArrayList getHeader() {
		return header;
	}

	public void setHeader(ArrayList header) {
		this.header = header;
	}

	public List getRows() {
		return rows;
	}

	private List rows;
	
	private ArrayList header;
	
	public int getColumns() {
		return this.header.size();
	}
	
}
