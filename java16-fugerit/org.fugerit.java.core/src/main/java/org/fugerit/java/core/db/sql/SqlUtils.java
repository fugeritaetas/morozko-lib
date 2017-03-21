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
 * @(#)SqlUtils.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.sql
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class SqlUtils {

	public static boolean closeLoose( Connection conn, Statement stm, ResultSet rs ) {
		return closeLoose( conn ) && closeLoose( stm ) && closeLoose( rs );
	}	
	
	public static boolean closeLoose( Connection conn, Statement stm ) {
		return closeLoose( conn ) && closeLoose( stm );
	}	
	
	public static boolean closeLoose( Connection conn ) {
		boolean close = true;
		try {
			conn.close();
		} catch (Exception e) {
			close = false;
		}
		return close;
	}
	
	public static boolean closeLoose( ResultSet rs ) {
		boolean close = true;
		try {
			rs.close();
		} catch (Exception e) {
			close = false;
		}
		return close;
	}	
	
	public static boolean closeLoose( Statement stm ) {
		boolean close = true;
		try {
			stm.close();
		} catch (Exception e) {
			close = false;
		}
		return close;
	}		
	
}
