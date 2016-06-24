package org.morozko.java.mod.db.dao.rse;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.db.dao.RSExtractor;

public class IntegerRSE implements RSExtractor {

	public static final RSExtractor DEFAULT = new IntegerRSE();
	
	public Object extractNext(ResultSet rs) throws SQLException {
		Object o = rs.getObject( 1 );
		if ( o != null ) {
			o = new Integer( rs.getInt( 1 ) );
		}
		return o;
	}

}
