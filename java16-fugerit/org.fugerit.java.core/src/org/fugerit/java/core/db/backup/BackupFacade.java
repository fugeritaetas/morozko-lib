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
 * @(#)BackupFacade.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.backup
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.backup;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.fugerit.java.core.db.backup.seq.GenericSequenceReset;
import org.fugerit.java.core.db.connect.CfConfig;
import org.fugerit.java.core.db.sql.ExecFacade;
import org.fugerit.java.core.log.LogFacade;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class BackupFacade {

	public static final String VERSION = "BackupFacade 1.1.0 - 2016-06-17";
	
	public static int backup( Element config ) throws Exception {
		return backup( config, CfConfig.EMPTY_CONFIG );
	}
	
	public static int backup( Element config, CfConfig cfConfig ) throws Exception {
		BackupConfig backupConfig = new BackupConfig();
		backupConfig.setCfConfig( cfConfig );
		backupConfig.configure( config );
		return backup( backupConfig, cfConfig );
	}
	
	private static int runSqlList( Connection c, List<String> list ) throws SQLException {
		int res = 0;
		if ( !list.isEmpty() ) {
			c.setAutoCommit( false );
			Statement stm = c.createStatement();
			Iterator<String> it = list.iterator();
			while ( it.hasNext() ) {
				String sql = (String) it.next();
				boolean result = stm.execute( sql );
				LogFacade.getLog().info( "BackupConfig.backup() query result : "+result+" - "+sql );
			}
			c.commit();
			c.setAutoCommit( true );	
		}
		return res;
	}
	
	public static int backup( BackupConfig backupConfig ) throws Exception {
		return backup( backupConfig, CfConfig.EMPTY_CONFIG );
	}
	
	public static int backup( BackupConfig backupConfig, CfConfig cfConfig ) throws Exception {
		int result = 0;
		LogFacade.getLog().info( "Using version : "+VERSION );
		if ( backupConfig.deleteFirst() ) {
			int delete = 0;
			for ( int k=backupConfig.getTableList().size()-1; k>=0; k-- ) {
				TableConfig tableConfig = (TableConfig)backupConfig.getTableList().get( k );
				String tableName = tableConfig.getTable();
				String sql = " DELETE FROM "+tableName+" ";
				int currentDelete = ExecFacade.executeUpdate( backupConfig.getFactoryTo() , sql );
				LogFacade.getLog().info( "BackupConfig.backup() Executed delete command : "+sql+" result : "+currentDelete );
				delete+= currentDelete;
			}
			LogFacade.getLog().info( "BackupConfig.backup() Total delete result : "+delete );
		}
		
		Connection from = backupConfig.getFactoryFrom().getConnection();
		Connection to = backupConfig.getFactoryTo().getConnection();		
		
		// pre script
		runSqlList( from, backupConfig.getPreSqlFromList() );
		runSqlList( to, backupConfig.getPreSqlToList() );
		
		
		GenericSequenceReset sequenceReset = new GenericSequenceReset();
		
		Iterator<SequenceConfig> itSequenceList = backupConfig.getSequenceList().iterator();
		while ( itSequenceList.hasNext() ) {
			SequenceConfig sequenceConfig = itSequenceList.next();
			String sequenceName = sequenceConfig.getSequenceName();
			long value = sequenceReset.loadNextValue( from , sequenceName );
			sequenceReset.saveNextValue( to, sequenceName, value );
		}
		
		Iterator<TableConfig> itTableList = backupConfig.getTableList().iterator();
		while ( itTableList.hasNext() ) {
			TableConfig tableConfig = itTableList.next();
			String tableName = tableConfig.getTable();
			String sql = tableConfig.getSelect();
			if ( sql == null ) {
				result+= backupConfig.getTableBackup().backupTable( tableName, from, to, tableConfig );	
			} else {
				result+= backupConfig.getTableBackup().backupTable( tableName, from, to, sql, tableConfig);
			}
		}
		
		if ( result == 0 ) {
			runSqlList( to, backupConfig.getPostSqlToList() );
			runSqlList( from, backupConfig.getPostSqlFromList() );
		} else {
			LogFacade.getLog().info( "Result errors : "+result+", skip post queries" );
		}

		from.close();
		to.close();
		LogFacade.getLog().info( "Backup return code : "+result );
		return result;
	}
	
	public static void main( String[] args ) {
		try {
			String file = args[0];
			
			Document config = DOMIO.loadDOMDoc( new File( file ) );
			
			int result = BackupFacade.backup( config.getDocumentElement() );
			
			System.out.println( "Backup result : "+result );
			
			System.exit( result );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
