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
 * @(#)ExecFacade.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.sql
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.dao.DAOException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ExecFacade {

	public static int executeUpdate( ConnectionFactory cf, String sql ) throws DAOException {
		int result = 0;
		Connection conn = cf.getConnection();
		System.out.println( "CONN "+conn );
		try {
			Statement stm = conn.createStatement();
			result = stm.executeUpdate( sql );
			stm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			SqlUtils.closeLoose( conn );
		}
		return result;
	}	
	
	public static boolean execute( ConnectionFactory cf, String sql ) throws DAOException {
		boolean result = false;
		Connection conn = cf.getConnection();
		try {
			Statement stm = conn.createStatement();
			result = stm.execute( sql );
			stm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			SqlUtils.closeLoose( conn );
		}
		return result;
	}
	
}
