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
 * @(#)GenericSequenceReset.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.backup.seq
 * @creation   : 30/ott/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.backup.seq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.fugerit.java.core.db.backup.SequenceReset;
import org.fugerit.java.core.db.helpers.DbUtils;
import org.fugerit.java.core.log.BasicLogObject;

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
public class GenericSequenceReset extends BasicLogObject implements SequenceReset {

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.backup.SequenceReset#loadNextValue(java.sql.Connection, java.lang.String)
	 */
	public long loadNextValue(Connection conn, String sequenceName) throws SQLException {
		long value = 0;
		int dbType = DbUtils.indentifyDB( conn );
		if ( dbType == DbUtils.DB_ORACLE ) {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( "SELECT "+sequenceName+".NEXTVAL FROM DUAL" );
			if ( rs.next() ) {
				value = rs.getLong( 1 );
			}
			rs.close();
			stm.close();
			this.getLogger().info( "loadNextValue (oracle) : "+value );
		} else {
			throw ( new SQLException( "Unidentified database type" ) );
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.backup.SequenceReset#saveNextValue(java.sql.Connection, java.lang.String, long)
	 */
	public void saveNextValue(Connection conn, String sequenceName, long value) throws SQLException {
		int dbType = DbUtils.indentifyDB( conn );
		if ( dbType == DbUtils.DB_ORACLE ) {
			try {
				value++;
				//String storedProcedure = StreamIO.readString( this.getClass().getResourceAsStream( "/org/opinf/jlib/mod/db/backup/seq/oracle-seq-reset.sql" ) );
				Statement stm = conn.createStatement();
				//stm.execute( storedProcedure );
				this.getLogger().info( "Calling reset_sequence (oracle:"+sequenceName+":"+value+")" );
				//stm.execute( "CALL reset_sequence ( '"+sequenceName+"', "+value+" ) " );
				stm.execute( "DROP SEQUENCE "+sequenceName );
				stm.execute( "CREATE SEQUENCE "+sequenceName+" INCREMENT BY 1 START WITH "+value+" NOCYCLE NOCACHE NOORDER" );				  
				stm.close();
			} catch (Exception e) {
				throw ( new SQLException( e.toString() ) );
			}
		} else {
			throw ( new SQLException( "Unidentified database type" ) );
		}
	}

	
	
}
