package org.fugerit.java.core.db.dao.rse;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fugerit.java.core.db.dao.RSExtractor;

public class StringRSE implements RSExtractor {

	public static final RSExtractor DEFAULT = new StringRSE();
	
	public Object extractNext(ResultSet rs) throws SQLException {
		Object o = rs.getObject( 1 );
		if ( o != null ) {
			o = rs.getString( 1 ) ;
		}
		return o;
	}

}
