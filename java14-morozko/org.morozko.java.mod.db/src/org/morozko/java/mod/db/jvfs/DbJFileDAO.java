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
 * @(#)DbJFileHandler.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.db.dao.IdGenerator;
import org.morozko.java.mod.db.dao.idgen.BasicSeqIdGenerator;
import org.morozko.java.mod.db.dao.idgen.GenericSeqIdGenerator;

/**
 * <p></p>
 *
 *  <p>standard ddl :</p>
	<code>
		CREATE SEQUENCE seq_id_file_data START WITH 1 INCREMENT BY 1;
 		CREATE TABLE file_data (
 			id_file_data BIGINT NOT NULL,
  			file_path VARCHAR(512) NOT NULL,
  			file_name VARCHAR(128) NOT NULL,
  			creation_time TIMESTAMP NOT NULL,
  			modification_time TIMESTAMP NOT NULL,
  			file_size INTEGER NOT NULL,
  			read_only INTEGER NOT NULL,
  			file_type INTEGER NOT NULL,
  			file_data BLOB NOT NULL
  		);
  		ALTER TABLE file_data ADD CONSTRAINT file_data_pk PRIMARY KEY ( id_file_data );
  		ALTER TABLE file_data ADD CONSTRAINT file_data_un1 UNIQUE ( file_path, file_name );
	</code>
	
	<p>for postgresql :</p> 
	<code>
		CREATE SEQUENCE seq_id_file_data START WITH 1 INCREMENT BY 1;
 		CREATE TABLE file_data (
 			id_file_data BIGINT NOT NULL,
  			file_path VARCHAR(512) NOT NULL,
  			file_name VARCHAR(128) NOT NULL,
  			creation_time TIMESTAMP NOT NULL,
  			modification_time TIMESTAMP NOT NULL,
  			file_size INTEGER NOT NULL,
  			read_only INTEGER NOT NULL,
  			file_type INTEGER NOT NULL,  			
  			file_data BYTEA NOT NULL
  		);
  		ALTER TABLE file_Data ADD CONSTRAINT file_data_pk PRIMARY KEY ( id_file_data );
  		ALTER TABLE file_data ADD CONSTRAINT file_data_un1 UNIQUE ( file_path, file_name );
	</code>	
	
	<p>for oracle :</p> 
	<code>	
		CREATE SEQUENCE seq_id_file_data START WITH 1 INCREMENT BY 1;
 		CREATE TABLE file_data (
 			id_file_data INTEGER NOT NULL,
  			file_path VARCHAR(512) NOT NULL,
  			file_name VARCHAR(128) NOT NULL,
  			creation_time DATE NOT NULL,
  			modification_time DATE NOT NULL,
  			file_size INTEGER NOT NULL,
  			read_only INTEGER NOT NULL,
  			file_type INTEGER NOT NULL,
  			file_data BLOB NOT NULL
  		);
  		ALTER TABLE file_data ADD CONSTRAINT file_data_pk PRIMARY KEY ( id_file_data );
  		ALTER TABLE file_data ADD CONSTRAINT file_data_un1 UNIQUE ( file_path, file_name );
	</code>			
	
 *
 * @author mfranci
 *
 */
public class DbJFileDAO extends BasicLogObject {
	
	private IdGenerator idGenerator;
	
	private static final ResourceBundle QUERIES = ResourceBundle.getBundle( "org.morozko.java.mod.db.jvfs.query" );
	
	private String tableName;
	
	private ConnectionFactory connectionFactory;
	
	public DbJFileDAO() {
	}
	
	public void initGenerator() throws ConfigException {
		this.idGenerator = new GenericSeqIdGenerator();
		this.idGenerator.setConnectionFactory( this.connectionFactory );
		Properties props = new Properties();
		props.setProperty( BasicSeqIdGenerator.PROP_SEQ_NAME, "seq_id_"+this.tableName );
		this.idGenerator.configure( props );
	}
	
	public DbJFileDAO( String tableName,  ConnectionFactory connectionFactory) {
		this.tableName = tableName;
		this.connectionFactory = connectionFactory;
	}

	public DbJFileModel loadDataByPath( DbJFileLocator dbJFileLocator ) throws DAOException {
		return this.loadByPath(dbJFileLocator, false);
	}
	
	public DbJFileModel loadMetaByPath( DbJFileLocator dbJFileLocator ) throws DAOException {
		return this.loadByPath(dbJFileLocator, true);
	}
	
	private DbJFileModel loadByPath( DbJFileLocator dbJFileLocator, boolean meta ) throws DAOException {
		DbJFileModel dbJFileModel = null;
		Connection conn = this.connectionFactory.getConnection();
		String sql = this.getQuery( "file.load.meta.by.path" );
		if ( !meta ) {
			sql = this.getQuery( "file.load.data.by.path" );
		}
		try {
			PreparedStatement pstm = conn.prepareStatement( sql );
			pstm.setString( 1, dbJFileLocator.getFilePath() );
			pstm.setString( 2, dbJFileLocator.getFileName() );
			ResultSet rs = pstm.executeQuery();
			if ( rs.next() ) {
				if ( meta ) {
					dbJFileModel = this.extractMeta( rs );	
				} else {
					dbJFileModel = this.extract( rs );
				}
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			this.close( conn );
		}
		return dbJFileModel;
	}	
	
	public boolean exists( DbJFileLocator dbJFileLocator ) throws DAOException {
		return ( this.loadDataByPath( dbJFileLocator ) != null );
	}
	
	public int save( DbJFileModel dbJFileModel ) throws DAOException {
		int result = 0;
		if ( this.exists( dbJFileModel ) ) {
			result = this.update( dbJFileModel );
		} else {
			result = this.insert( dbJFileModel );
		}
		return result;
	}
	
	public int delete( DbJFileLocator dbJFileLocator ) throws DAOException {
		Connection conn = this.connectionFactory.getConnection();
		String sql = this.getQuery( "file.delete.by.sk" );
		int result = 0;
		try {
			PreparedStatement pstm = conn.prepareStatement( sql );
			pstm.setString( 1, dbJFileLocator.getFilePath() );
			pstm.setString( 2, dbJFileLocator.getFileName() );
			result = pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			this.close( conn );
		}
		return result;
	}		
	
	public int updateMeta( DbJFileModel dbJFileModel ) throws DAOException {
		return updateWorker( dbJFileModel, true );
	}	
		
	
	public int update( DbJFileModel dbJFileModel ) throws DAOException {
		return updateWorker( dbJFileModel, false );
	}	
	
	private int updateWorker( DbJFileModel dbJFileModel, boolean meta ) throws DAOException {
		Connection conn = this.connectionFactory.getConnection();
		String sql = this.getQuery( "file.update" );
		if ( meta ) {
			sql = this.getQuery( "file.update.meta" );
		}
		int result = 0;
		try {
			PreparedStatement pstm = conn.prepareStatement( sql );
			if ( meta ) {
				this.setMeta(pstm, dbJFileModel);
				pstm.setLong( 8, dbJFileModel.getIdFileData().longValue() );
			} else {
				this.set(pstm, dbJFileModel);
				pstm.setLong( 9, dbJFileModel.getIdFileData().longValue() );	
			}
			result = pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			this.close( conn );
		}
		return result;
	}		
	
	public int insert( DbJFileModel dbJFileModel ) throws DAOException {
		Connection conn = this.connectionFactory.getConnection();
		String sql = this.getQuery( "file.insert" );
		int result = 0;
		try {
			PreparedStatement pstm = conn.prepareStatement( sql );
			this.set(pstm, dbJFileModel);
			dbJFileModel.setIdFileData( new Long( this.idGenerator.generateId().longValue() ) );
			pstm.setLong( 9, dbJFileModel.getIdFileData().longValue() );
			result = pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			this.close( conn );
		}
		return result;
	}	
	
	public List loadAll( ) throws DAOException {
		List list = new ArrayList();
		Connection conn = this.connectionFactory.getConnection();
		String sql = this.getQuery( "file.load.meta.all" );
		try {
			PreparedStatement pstm = conn.prepareStatement( sql );
			ResultSet rs = pstm.executeQuery();
			while ( rs.next() ) {
				list.add( this.extractMeta( rs ) );
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw ( new DAOException( e ) );
		} finally {
			this.close( conn );
		}
		return list;
	}	
	
	protected String getQuery( String key ) {
		String query = QUERIES.getString( key );
		MessageFormat mf = new MessageFormat( query );
		Object[] args = { this.tableName };
		query = mf.format( args );
		return query;
	}
	
	protected void close( Connection conn ) {
		try {
			conn.close();
		} catch (SQLException e) {
			this.getLog().error( e );
		}
	}
	
	protected DbJFileModel extract( ResultSet rs ) throws SQLException {
		DbJFileModel dbJFileModel = this.extractMeta( rs );
		dbJFileModel.setFileData( this.extractData( rs ) );
		return dbJFileModel;
	}
	
	protected byte[] extractData( ResultSet rs ) throws SQLException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			StreamIO.pipeStream( rs.getBinaryStream( "file_data" ), baos, StreamIO.MODE_CLOSE_IN_ONLY );
		} catch (IOException e) {
			throw ( new SQLException( e.toString() ) );
		}
		return baos.toByteArray();
	}
	
	protected DbJFileModel extractMeta( ResultSet rs ) throws SQLException {
		DbJFileModel dbJFileModel = new DbJFileModel();
		dbJFileModel.setIdFileData( new Long( rs.getLong( "id_file_data" ) ) );
		dbJFileModel.setFilePath( rs.getString( "file_path" ) );
		dbJFileModel.setFileName( rs.getString( "file_name" ) );
		dbJFileModel.setFileSize( new Integer ( rs.getInt( "file_size" ) ) );
		dbJFileModel.setCreationTime( rs.getTimestamp( "creation_time" ) );
		dbJFileModel.setModificationTime( rs.getTimestamp( "modification_time" ) );
		dbJFileModel.setFileType( new Integer ( rs.getInt( "file_type" ) ) );
		dbJFileModel.setReadOnly( new Integer ( rs.getInt( "read_only" ) ) );
		return dbJFileModel;
	}

	protected void set( PreparedStatement pstm, DbJFileModel dbJFileModel ) throws SQLException {
		this.setMeta(pstm, dbJFileModel);
		this.setData(pstm, dbJFileModel);
	}
	
	protected void setData( PreparedStatement pstm, DbJFileModel dbJFileModel ) throws SQLException {
		pstm.setBytes( 8 , dbJFileModel.getFileData() );
	}	
	
	protected void setMeta( PreparedStatement pstm, DbJFileModel dbJFileModel ) throws SQLException {
		pstm.setString( 1, dbJFileModel.getFilePath() );
		pstm.setString( 2, dbJFileModel.getFileName() );
		pstm.setInt( 3, dbJFileModel.getFileSize().intValue() );
		pstm.setTimestamp( 4 , dbJFileModel.getCreationTime() );
		pstm.setTimestamp( 5,  dbJFileModel.getModificationTime() );
		pstm.setInt( 6 , dbJFileModel.getFileType().intValue() );
		pstm.setInt( 7 , dbJFileModel.getReadOnly().intValue() );
	}

	/**
	 * @return the connectionFactory
	 */
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * @param connectionFactory the connectionFactory to set
	 */
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
