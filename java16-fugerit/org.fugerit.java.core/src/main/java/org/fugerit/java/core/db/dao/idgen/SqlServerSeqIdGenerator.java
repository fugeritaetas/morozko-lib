/*
 * @(#)SqlServerSeqIdGenerator.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.dao.idgen
 * @creation   : 02/apr/07
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.dao.idgen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.helpers.DAOID;


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
public class SqlServerSeqIdGenerator extends BasicSeqIdGenerator {

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
				CallableStatement cs = conn.prepareCall( "{call nextval( ?, ? )}" );
				cs.setString( 1, this.getSequenceName() );
				cs.registerOutParameter( 2, Types.BIGINT );
				cs.execute();
				id = new DAOID( cs.getLong( 2 ) );
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
