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
 * @(#)DefaultBackupAdaptor.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.backup
 * @creation   : 25/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.backup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import org.fugerit.java.core.io.StreamIO;
import org.fugerit.java.core.log.BasicLogObject;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DefaultBackupAdaptor extends BasicLogObject implements BackupAdaptor {

	public static final DefaultBackupAdaptor DEFAULT = new DefaultBackupAdaptor();
	
	public void configure(Element config) throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.backup.BackupAdaptor#get(java.sql.ResultSet, java.sql.ResultSetMetaData, int)
	 */
	public Object get(ResultSet rs, ResultSetMetaData rsmd, int index) throws SQLException {
		int type = rsmd.getColumnType( index );
		String colName = rsmd.getColumnName( index );
		Object result = rs.getObject( colName );
        if (type==Types.DATE) {
        	result = rs.getDate( index );
        }
		return result;
	}

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.db.backup.BackupAdaptor#set(java.sql.PreparedStatement, java.sql.ResultSetMetaData, java.lang.Object, int)
	 */
	public void set(PreparedStatement ps, ResultSetMetaData rsmd, Object obj, int index) throws SQLException {
		int type = rsmd.getColumnType( index );
		this.getLogger().debug( "DefaultBackupAdaptor>>> SETTING TYPE : "+rsmd.getColumnName( index ) );
		if (  obj == null ) {
			ps.setNull( index , type );
		} else if (type==Types.DATE) {
	        ps.setDate( index , (Date)obj );
		 } else if (type==Types.BLOB) {
			Blob b = (Blob)obj;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				StreamIO.pipeStream( b.getBinaryStream() , baos, StreamIO.MODE_CLOSE_BOTH );
			} catch (IOException e) {
				throw new SQLException( e.toString() );
			}
			ps.setBytes( index , baos.toByteArray() );
	     } else {
	    	ps.setObject( index , obj );
	     }
	}

}
