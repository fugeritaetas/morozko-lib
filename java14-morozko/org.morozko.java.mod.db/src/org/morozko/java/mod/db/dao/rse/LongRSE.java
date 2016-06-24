package org.morozko.java.mod.db.dao.rse;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.morozko.java.mod.db.dao.RSExtractor;

public class LongRSE implements RSExtractor {

	public static final RSExtractor DEFAULT = new LongRSE();
	
	public Object extractNext(ResultSet rs) throws SQLException {
		Object o = rs.getObject( 1 );
		if ( o != null ) {
			o = new Long( rs.getLong( 1 ) );
		}
		return o;
	}

}
