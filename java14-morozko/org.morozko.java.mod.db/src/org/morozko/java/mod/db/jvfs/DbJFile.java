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
 * @(#)DbJFile.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.helpers.AbstractJFile;
import org.morozko.java.mod.db.dao.DAOException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJFile extends AbstractJFile {

	private String relPath;
	
	private DbJFileDAO dbJFileDAO;
	
	private DbJFileModel dbJFileModel;
	
	private DbJFileLocator dbJFileLocator;
	
	/**
	 * <p>Creates a new instance of DbJFile.</p>
	 *
	 * @param arg0
	 * @param arg1
	 * @throws IOException 
	 */
	public DbJFile(String path, JVFS jvfs, String relPath, DbJFileDAO dbJFileDAO ) {
		super( path, jvfs );
		this.relPath = relPath;
		this.dbJFileDAO = dbJFileDAO;
		this.dbJFileLocator = DbJFileLocator.parse( relPath, jvfs.getPathResolver() );
		try {
			DbJFileLocator loc = DbJFileLocator.parse( this.relPath, this.getPathResolver() );
			this.dbJFileModel = dbJFileDAO.loadMetaByPath( loc );
		} catch (DAOException e) {
			throw ( new RuntimeException( e.toString() ) );
		}
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#canRead()
	 */
	public boolean canRead() throws IOException {
		boolean result = true;
		if ( this.dbJFileModel == null ) {
			result = false;
		} else {
			result = !this.getDbJFileModel().getReadOnly().equals( DbJFileModel.READ_ONLY_TRUE );
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#canWrite()
	 */
	public boolean canWrite() throws IOException {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#create()
	 */
	public boolean create() throws IOException {
		boolean result = false;
		if ( !this.exists() ) {
			this.dbJFileModel = new DbJFileModel();
			DbJFileLocator dbJFileLocator = DbJFileLocator.parse( this.relPath, this.getPathResolver() );
			dbJFileModel.init( dbJFileLocator.getFilePath(), dbJFileLocator.getFileName() );
			try {
				this.dbJFileDAO.insert( dbJFileModel );
			} catch (DAOException e) {
				throw ( new IOException( e.toString() ) );
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#delete()
	 */
	public boolean delete() throws IOException {
		boolean result = false;
		try {
			result = this.dbJFileDAO.delete( DbJFileLocator.parse( this.relPath, this.getPathResolver() ) )>0;
		} catch (DAOException e) {
			throw ( new IOException( e.toString() ) );
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#exists()
	 */
	public boolean exists() throws IOException {
		return this.dbJFileModel != null;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#getInputStream()
	 */
	public InputStream getInputStream() throws IOException {
		byte[] data = new byte[0];
		if ( this.dbJFileModel != null ) {
			try {
				data = this.dbJFileDAO.loadDataByPath( DbJFileLocator.parse( this.relPath, this.getPathResolver() ) ).getFileData();
			} catch (DAOException e) {
				throw ( new DbJFileIOException( e ) );
			}
		}
		return new ByteArrayInputStream( data );
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#getLastModified()
	 */
	public long getLastModified() throws IOException {
		long result = 0;
		if ( this.dbJFileModel != null ) {
			result = dbJFileModel.getModificationTime().getTime();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#getName()
	 */
	public String getName() {
		return this.dbJFileLocator.getFileName();
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#getOutputStream()
	 */
	public OutputStream getOutputStream() throws IOException {
		OutputStream os = null;
		if ( this.isDirectory() ) {
			throw ( new IOException( "This is a directory, not a file" ) );
		} else {
			os = new DbJFileOutputStream( this );
		}
		return os;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#isFile()
	 */
	public boolean isFile() throws IOException {
		return this.exists() && this.getDbJFileModel().isFile();
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#isDirectory()
	 */
	public boolean isDirectory() throws IOException {
		return this.exists() && this.getDbJFileModel().isDirectory();
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#list()
	 */
	public String[] list() throws IOException {
		String[] list = null;
		if ( this.isDirectory() ) {
			try {
				List files = this.dbJFileDAO.loadAll( );
				list = new String[ files.size() ];
				for ( int k=0; k<files.size(); k++ ) {
					DbJFileModel current = (DbJFileModel)files.get( k );
					list[k] = current.getFileName();
				}
			} catch (DAOException e) {
				throw ( new IOException( e.toString() ) );
			}
		} else {
			throw ( new IOException( "The file is not a directory" ) );
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#mkdir()
	 */
	public boolean mkdir() throws IOException {
		boolean result = false;
		if ( !this.exists() ) {
			this.dbJFileModel = new DbJFileModel();
			DbJFileLocator dbJFileLocator = DbJFileLocator.parse( this.relPath, this.getPathResolver() );
			dbJFileModel.init( dbJFileLocator.getFilePath(), dbJFileLocator.getFileName() );
			dbJFileModel.setFileType( DbJFileModel.TYPE_DIRECTORY );
			try {
				this.dbJFileDAO.insert( dbJFileModel );
			} catch (DAOException e) {
				throw ( new IOException( e.toString() ) );
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#mkdirs()
	 */
	public boolean mkdirs() throws IOException {
		throw ( new IOException( "Unsupported operation" ) );
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#setLastModified(long)
	 */
	public void setLastModified(long arg0) throws IOException {
		if ( !this.exists() ) {
			this.create();
		}
		this.dbJFileModel.setModificationTime( new Timestamp( System.currentTimeMillis() ) );
		try {
			this.dbJFileDAO.updateMeta( this.dbJFileModel );
		} catch (DAOException e) {
			throw new DbJFileIOException( e );
		}
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.helpers.AbstractJFile#setReadOnly()
	 */
	public void setReadOnly() throws IOException {
		if ( !this.exists() ) {
			this.create();
		}
		this.dbJFileModel.setReadOnly( DbJFileModel.READ_ONLY_TRUE  );
		try {
			this.dbJFileDAO.updateMeta( this.dbJFileModel );
		} catch (DAOException e) {
			throw new DbJFileIOException( e );
		}
	}

	/**
	 * @return the dbJFileDAO
	 */
	public DbJFileDAO getDbJFileDAO() {
		return dbJFileDAO;
	}

	/**
	 * @param dbJFileDAO the dbJFileDAO to set
	 */
	public void setDbJFileDAO(DbJFileDAO dbJFileDAO) {
		this.dbJFileDAO = dbJFileDAO;
	}

	/**
	 * @return the dbJFileModel
	 */
	public DbJFileModel getDbJFileModel() {
		return dbJFileModel;
	}

	/**
	 * @param dbJFileModel the dbJFileModel to set
	 */
	public void setDbJFileModel(DbJFileModel dbJFileModel) {
		this.dbJFileModel = dbJFileModel;
	}

	/**
	 * @return the relPath
	 */
	public String getRelPath() {
		return relPath;
	}

	/**
	 * @param relPath the relPath to set
	 */
	public void setRelPath(String relPath) {
		this.relPath = relPath;
	}

}
