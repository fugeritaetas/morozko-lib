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
 * @(#)QueryFacade.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.metadata.query
 * @creation   : 25/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.metadata.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.morozko.java.core.log.LogFacade;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryMetadataFacade {

	public static QueryColumnMap columnMap( Connection conn, String select, boolean noModify ) throws Exception {
		Statement stm = conn.createStatement();
		String metaSelect = select;
		if ( !noModify ) {
			metaSelect = " SELECT * FROM ( "+select+" ) v WHERE 1=0 ";
		}
		LogFacade.getLog().info( "QueryMetadataFacade.columnMap metaSelect: "+metaSelect );
		ResultSet rs = stm.executeQuery( metaSelect );
		ResultSetMetaData rsmd = rs.getMetaData();
		QueryColumnMap map = columnMap( rsmd );
		rs.close();
		stm.close();
		return map;
	}		

	
	public static QueryColumnMap columnMap( Connection conn, String select ) throws Exception {
		return columnMap(conn, select, false);
	}		
	
	public static QueryColumnMap columnMap( ResultSetMetaData rsmd ) throws Exception {
		QueryColumnMap map = new QueryColumnMap();
		for ( int k = 1; k<= rsmd.getColumnCount(); k++ ) {
			QueryColumnModel model = new QueryColumnModel();
			model.setName( rsmd.getColumnName( k ) );
			model.setDisplay( rsmd.getColumnLabel( k ) );
			model.setType( rsmd.getColumnType( k ) );
			model.setTypeName( rsmd.getColumnTypeName( k ) );
			model.setTypeClass( rsmd.getColumnClassName( k ) );
			map.add( model );
		}
		return map;
	}	
	
}
