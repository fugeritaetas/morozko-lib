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
 * @(#)BasicSeqIdGenerator.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.dao.idgen
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.dao.idgen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.helpers.DAOID;
import org.fugerit.java.core.xml.dom.DOMUtils;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public abstract class BasicSeqIdGenerator extends BasicIdGenerator {

	protected abstract String createSequenceQuery();
	
	private String sequenceName;
	
	public static final String PROP_SEQ_NAME = "sequenceName";
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.idgen.BasicIdGenerator#configure(org.w3c.dom.Element)
	 */
	public void configure(Element tag) throws ConfigException {
		this.configure( DOMUtils.attributesToProperties( tag ) ); 
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.idgen.BasicIdGenerator#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		this.setSequenceName( props.getProperty( PROP_SEQ_NAME ) );
	}	
	
	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.dao.idgen.BasicIdGenerator#generateID()
	 */
	public DAOID generateId() throws DAOException {
		this.getLogger().debug( "generateId start " );
		DAOID id = null;
		Connection conn = this.getConnectionFactory().getConnection();
		try {
			String sql = this.createSequenceQuery();
			this.getLogger().debug( "generateId query : '"+sql+"'" );
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery( sql );
			if ( rs.next() ) {
				id = new DAOID( rs.getLong( 1 ) );
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			try {
				conn.close();	
			} catch ( SQLException sqle ) {
				sqle.printStackTrace();
			}
		}
		this.getLogger().debug( "generateId end : "+id );
		return id;
	}

	/**
	 * @return the sequenceName
	 */
	public String getSequenceName() {
		return sequenceName;
	}

	/**
	 * @param sequenceName the sequenceName to set
	 */
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}



}
