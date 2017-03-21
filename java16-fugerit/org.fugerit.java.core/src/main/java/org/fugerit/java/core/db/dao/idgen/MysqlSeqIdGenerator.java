/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)MysqlSeqIdGenerator.java
 *
 * @project     : org.fugerit.java.core.db
 * @package     : org.fugerit.java.core.db.dao.idgen
 * @creation	: 03/ott/07
 * @release		: xxxx.xx.xx
 */
package org.fugerit.java.core.db.dao.idgen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.helpers.DAOID;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class MysqlSeqIdGenerator extends BasicSeqIdGenerator {

	public static String createSequenceQuery( String seqName) {
		return " SELECT nextval('"+seqName+"'); ";
	}
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.idgen.BasicSeqIdGenerator#createSequenceQuery()
	 */
	protected String createSequenceQuery() {
		return createSequenceQuery( this.getSequenceName() ); 
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.idgen.BasicIdGenerator#generateId()
	 */
	public DAOID generateId() throws DAOException {
		this.getLogger().debug( "generateId start " );
		DAOID id = null;
		try {
			Connection conn = this.getConnectionFactory().getConnection();
			try {
				Statement stm = conn.createStatement();
				stm.executeUpdate( "UPDATE "+this.getSequenceName()+" SET id=LAST_INSERT_ID(id+1);" );
				ResultSet rs = stm.executeQuery( " SELECT LAST_INSERT_ID(); " );
				if ( rs.next() ) {
					id = new DAOID( rs.getLong( 1 ) );	
				}
				rs.close();
				stm.close();
			} catch (Exception e) {
				conn.close();
				throw ( new DAOException( e ) );
			}
		} catch (Exception e) {
			throw ( new DAOException( e ) );
		}
		this.getLogger().debug( "generateId end : "+id );
		return id;
	}

}
