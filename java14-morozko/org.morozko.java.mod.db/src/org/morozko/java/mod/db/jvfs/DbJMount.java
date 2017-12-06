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
 * @(#)DbJMount.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.jvfs
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.jvfs;

import java.util.Properties;

import org.morozko.java.core.cfg.ConfigException;
import org.morozko.java.core.cfg.helpers.DefaultConfigurableObject;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JMount;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbJMount extends DefaultConfigurableObject implements JMount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4299060127268731483L;

	public static final String PROP_TABLE_NAME = "db-table-name";
	
	public static final String PROP_DAO_CLASS = "db-dao-class";
		
	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.cfg.helpers.DefaultConfigurableObject#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		try {
			this.getLog().info( "configure START" );
			this.dbJFileDAO = (DbJFileDAO) ClassHelper.newInstance( props.getProperty( PROP_DAO_CLASS ) );
			this.getLog().info( "configure dao class created : "+this.dbJFileDAO );
			this.dbJFileDAO.setTableName( props.getProperty( PROP_TABLE_NAME ) );
			this.getLog().info( "configure table name : "+this.dbJFileDAO.getTableName() );
			this.dbJFileDAO.setConnectionFactory( ConnectionFactoryImpl.newInstance( props ) );
			this.dbJFileDAO.initGenerator();
			this.getLog().info( "configure END" );
		} catch (Exception e) {
			new ConfigException( e );
		}
	}
	
	public DbJMount() {
		
	}

	public DbJMount( String dbJFileDAOClass, String tableName, ConnectionFactory connectionFactory ) throws Exception {
		this.dbJFileDAO = (DbJFileDAO) ClassHelper.newInstance( dbJFileDAOClass );
		this.dbJFileDAO.setTableName(tableName);
		this.dbJFileDAO.setConnectionFactory(connectionFactory);
		this.dbJFileDAO.initGenerator();
	}

	
	private DbJFileDAO dbJFileDAO;
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.jvfs.JMount#getJFile(org.opinf.jlib.std.jvfs.JVFS, java.lang.String, java.lang.String)
	 */
	public JFile getJFile(JVFS jvfs, String point, String relPath) {
		String path = jvfs.getPathResolver().getChild(point, relPath );
		this.getLog().debug( "point   : "+point );
		this.getLog().debug( "relPath : "+relPath );
		this.getLog().debug( "path    : "+path );
		return new DbJFile( path, jvfs, relPath, this.dbJFileDAO );
	}

}
