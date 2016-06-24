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
 * @(#)GenericSeqIdGenerator.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.idgen
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.dao.idgen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.db.dao.IdGenerator;
import org.morozko.java.mod.db.utils.DbUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class GenericSeqIdGenerator extends BasicSeqIdGenerator {
	
	private IdGenerator idGenerator; 
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.dao.idgen.BasicSeqIdGenerator#generateId()
	 */
	public DAOID generateId() throws DAOException {
		DAOID id = null;
		if ( this.idGenerator == null ) {
			id = super.generateId();
		} else {
			id = this.idGenerator.generateId();
		}
		return id;
	}

	private int dbType;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.dao.idgen.BasicSeqIdGenerator#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		super.configure(props);
		if ( this.getConnectionFactory() == null ) {
			throw new ConfigException( "Devi associare la connection factory prima di chiamare configure()" );
		} else {
			Connection conn = null;
			try {
				conn = this.getConnectionFactory().getConnection();
				this.dbType = DbUtils.indentifyDB( conn );
				if ( this.dbType == DbUtils.DB_SQLSERVER ) {
					this.idGenerator = new SqlServerSeqIdGenerator();
					this.idGenerator.setConnectionFactory( this.getConnectionFactory() );
					this.idGenerator.configure( props );
				} else if ( this.dbType == DbUtils.DB_MYSQL ) {
					this.idGenerator = new MysqlSeqIdGenerator();
					this.idGenerator.setConnectionFactory( this.getConnectionFactory() );
					this.idGenerator.configure( props );					
				}
			} catch (Exception e) {
				throw ( new ConfigException( e ) );
			} finally {
				if ( conn != null ) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.db.dao.idgen.BasicSeqIdGenerator#createSequenceQuery()
	 */
	protected String createSequenceQuery() {
		String sql = null;
		if ( this.dbType == DbUtils.DB_ORACLE ) {
			sql = OracleSeqIdGenerator.createSequenceQuery( this.getSequenceName() );
		} else if ( this.dbType == DbUtils.DB_POSTGRESQL ) {
			sql = PostgresqlSeqIdGenerator.createSequenceQuery( this.getSequenceName() );
		} else {
			// tipo db sconosciuto
		}
		return sql;
	}

}
