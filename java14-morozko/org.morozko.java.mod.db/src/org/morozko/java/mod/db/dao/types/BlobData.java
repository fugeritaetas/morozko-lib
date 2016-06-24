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
 * @(#)BlobData.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.dao.types
 * @creation   : 10/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.dao.types;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import org.morozko.java.core.io.StreamIO;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class BlobData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664628013389279648L;

	public static BlobData valueOf( Blob b ) throws SQLException {
		BlobData blobData = new BlobData();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = b.getBinaryStream();
			StreamIO.pipeStream( is , baos );
			is.close();
			blobData.setData( baos.toByteArray() );
		} catch (Exception e) {
			e.printStackTrace();
			throw ( new SQLException( e.toString() ) );
		}
		return blobData;
	}
	
	private byte[] data;

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	
}
