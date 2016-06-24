package org.morozko.java.mod.dbsrc.config.ds.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.db.dao.RSExtractor;
import org.morozko.java.mod.dbsrc.config.generic.DbsrcException;
import org.morozko.java.mod.dbsrc.data.Field;
import org.morozko.java.mod.dbsrc.data.Record;

public class DbsrcRSE implements RSExtractor {

	public static DbsrcRSE newRSE( JdbcColumn[] columns ) {
		return new DbsrcRSE( columns );
	}
	
	private DbsrcRSE(JdbcColumn[] columns) {
		super();
		this.columns = columns;
	}

	public JdbcColumn[] getColumns() {
		return columns;
	}

	private JdbcColumn[] columns = null;
	
	public Object extractNext(ResultSet rs) throws SQLException {
		if ( columns == null ) {
			columns = JdbcDataSource.describe( rs.getMetaData() );
		}
		Field[] fieldList = new Field[ this.columns.length ];
		for ( int k=0; k<fieldList.length; k++ ) {
			Object current = rs.getObject( k+1 );
			try {
				Field f = Field.newField( current , this.columns[k].getType() );;
				fieldList[k] = f;
			} catch (DbsrcException e) {
				new SQLException( "Error while generating gield list : "+e.getMessage() );
			}
		}
		return Record.newRecord( fieldList, this.columns );
	}

}
