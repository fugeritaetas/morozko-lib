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
 * @(#)PropertiesExtractor.java
 *
 * @project  : org.morozko.java.mod.db
 * @package  : org.morozko.java.mod.db.dao.rse
 * @creation : 19-mar-2006
 */
package org.morozko.java.mod.db.dao.rse;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.morozko.java.mod.db.dao.RSExtractor;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class PropertiesExtractor implements RSExtractor {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.dao.RSExtractor#extractNext(java.sql.ResultSet)
	 */
	public Object extractNext(ResultSet rs) throws SQLException {
		Properties props = new Properties();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		for ( int k=1; k<=cols; k++) {
			String name = rsmd.getColumnName( k );
			String value = rs.getString( name );
			if ( value != null ) {
				props.setProperty( name, value );	
			}
		}
		return props;
	}

}
