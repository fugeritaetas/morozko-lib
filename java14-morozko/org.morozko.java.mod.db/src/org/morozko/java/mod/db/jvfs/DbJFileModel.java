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
 * @(#)DbJFileModel.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.sql.Timestamp;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJFileModel extends DbJFileLocator {
	
	public final static Integer READ_ONLY_FALSE = new Integer( 0 );
	
	public final static Integer READ_ONLY_TRUE = new Integer( 1 );
	
	public final static Integer TYPE_DIRECTORY = new Integer( 0 );
	
	public final static Integer TYPE_FILE = new Integer( 1 );
	
	public void init( String filePath, String fileName ) {
		Timestamp now = new Timestamp( System.currentTimeMillis() );
		byte[] data = new byte[0];
		this.setCreationTime( now );
		this.setModificationTime( now );
		this.setReadOnly( READ_ONLY_FALSE );
		this.setFilePath( filePath );
		this.setFileName( fileName );
		this.setIdFileData( new Long( 0 ) );
		this.setFileSize( new Integer( data.length ) );
		this.setFileType( TYPE_FILE );
		this.setFileData( data );
	}
	
	public void updateModificationTime() {
		this.setModificationTime( new Timestamp( System.currentTimeMillis() ) );
	}
	
	public boolean isFile() {
		return TYPE_FILE.equals( this.getFileType() );
	}
	
	public boolean isDirectory() {
		return TYPE_DIRECTORY.equals( this.getFileType() );
	}	
	
	private Long idFileData;
	
	private Integer fileSize;
	
	private Timestamp creationTime;
	
	private Timestamp modificationTime;
	
	private Integer readOnly;
	
	private Integer fileType;
	
	private byte[] fileData;

	/**
	 * @return the fileData
	 */
	public byte[] getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the creationTime
	 */
	public Timestamp getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the fileSize
	 */
	public Integer getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the modificationTime
	 */
	public Timestamp getModificationTime() {
		return modificationTime;
	}

	/**
	 * @param modificationTime the modificationTime to set
	 */
	public void setModificationTime(Timestamp modificationTime) {
		this.modificationTime = modificationTime;
	}

	/**
	 * @return the readOnly
	 */
	public Integer getReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(Integer readOnly) {
		this.readOnly = readOnly;
	}


	/**
	 * @return the idFileData
	 */
	public Long getIdFileData() {
		return idFileData;
	}

	/**
	 * @param idFileData the idFileData to set
	 */
	public void setIdFileData(Long idFileData) {
		this.idFileData = idFileData;
	}

	/**
	 * @return the fileType
	 */
	public Integer getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	
}
