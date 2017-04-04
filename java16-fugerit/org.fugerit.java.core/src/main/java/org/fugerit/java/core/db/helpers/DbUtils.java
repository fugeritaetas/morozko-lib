/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core.db 

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
 * @(#)IndentifyDB.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.helpers
 * @creation   : 30/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.log.LogFacade;

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
public class DbUtils {

	public static final int DB_UNKNOWN = 0;
	
	public static final int DB_POSTGRESQL = 100;

	public static final int DB_MYSQL = 200;
	
	public static final int DB_ORACLE = 300;
	
	public static final int DB_DB2 = 400;
	
	public static final int DB_SQLSERVER = 500;
	
	public static void close( Connection conn ) throws DAOException {
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DAOException( e );
			}
		}
	}
	
	public static int indentifyDB( Connection conn ) throws SQLException {
		int dbType = DB_UNKNOWN;
		String name = conn.getMetaData().getDriverName().toLowerCase();
		LogFacade.getLog().info( "Configuring Database specific logic, driver : "+name );
		if ( name.indexOf( "postgres" ) != -1 ) {
			dbType = DB_POSTGRESQL;
			LogFacade.getLog().info( "IdGenerator configured for : POSTGRESQL ("+dbType+")" );
		} else if ( name.indexOf( "oracle" ) != -1 ) {
			dbType = DB_ORACLE;
			LogFacade.getLog().info( "IdGenerator configured for : ORACLE ("+dbType+")" );
		} else if ( name.indexOf( "sqlserver" ) != -1 ) {
			dbType = DB_SQLSERVER;
			LogFacade.getLog().info( "IdGenerator configured for : SQLSERVER ("+dbType+")" );
		} else if ( name.indexOf( "mysql" ) != -1 ) {
			dbType = DB_MYSQL;
			LogFacade.getLog().info( "IdGenerator configured for : MYSQL ("+dbType+")" );
		} else {
			dbType = DB_UNKNOWN;
			LogFacade.getLog().info( "Unknown db type ("+dbType+")" );
		}
		return dbType;
	}
	
}
