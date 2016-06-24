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
 * @(#)DbJFileOutputStream.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.morozko.java.mod.db.dao.DAOException;


/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJFileOutputStream extends ByteArrayOutputStream {

	public DbJFileOutputStream( DbJFile dbJFile ) {
		this.dbJFile = dbJFile;
	}
	
	private DbJFile dbJFile;
	
	/**
	 * @return the dbJFile
	 */
	public DbJFile getDbJFile() {
		return dbJFile;
	}

	/**
	 * @param dbJFile the dbJFile to set
	 */
	public void setDbJFile(DbJFile dbJFile) {
		this.dbJFile = dbJFile;
	}

	/* (non-Javadoc)
	 * @see java.io.ByteArrayOutputStream#close()
	 */
	public void close() throws IOException {
		this.dbJFile.create();
		this.dbJFile.getDbJFileModel().setFileData( this.toByteArray() );
		try {
			this.dbJFile.getDbJFileModel().updateModificationTime();
			this.dbJFile.getDbJFileDAO().update( dbJFile.getDbJFileModel() );
		} catch (DAOException e) {
			e.printStackTrace();
			throw ( new DbJFileIOException( e ) );
		}
		super.close();
	}



}
