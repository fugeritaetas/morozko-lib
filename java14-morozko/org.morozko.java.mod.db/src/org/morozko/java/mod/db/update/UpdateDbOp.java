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
 * @(#)UpdateDbOp.java
 *
 * @project    : org.morozko.java.mod.db
 * @package    : org.morozko.java.mod.db.update
 * @creation   : 24/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.db.update;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.lang.Op;
import org.morozko.java.core.lang.helpers.AbstractOp;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.util.ver.ProgramVersion;
import org.morozko.java.mod.db.connect.ConnectionFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.sql.ScriptFacade;

/**
 * <p>Op che permette di eseguire un elenco di operazioni sul database in modalità batch.</p>
 *
 * @author mfranci
 *
 */
public class UpdateDbOp extends AbstractOp {

	/**
	 * <p>Genera le operazioni di update per il database.</p>
	 * 
	 * @param fromVersion	la versione da cui partire (esclusa)
	 * @param toVersion		la versione a cui arrivare (inclusa se diversa da quella di partenza)
	 * @param cfName		il nome della ConnectionFactory da usare (registrate dentro ConnectionFacade)
	 * @param basePath		il path base da aggiungere ai file
	 * @param jvfs			il jvfs da utilizzare per risolvere i file
	 * @param updateList	l'elenco dei file di versione da utilizzare
	 * @return
	 * @throws Exception
	 */
	public static List updateOpAll( ProgramVersion fromVersion, ProgramVersion toVersion, String cfName, String basePath, JVFS jvfs, Properties updateList ) throws Exception {
		LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] START" );
		List op = new ArrayList();
		List enumVersions = new ArrayList( updateList.keySet() );
		Collections.sort( enumVersions );
		String versionFrom = fromVersion.getMajorMinorVersion();
		String versionTo = toVersion.getMajorMinorVersion();
		LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] version from : "+versionFrom );
		LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] version to   : "+versionTo );
		for ( int k=0; k<enumVersions.size(); k++ ) {
			String version = (String)enumVersions.get( k );
			LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] compare version : "+version );
			if ( version.compareTo( versionFrom ) > 0 && version.compareTo( versionTo ) <= 0 ) {
				LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] generating update up..." );
				JFile sqlFile = jvfs.getJFile( basePath+jvfs.getPathResolver().getSepartor()+updateList.getProperty( version ) );
				LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] sqlFile : "+sqlFile );
				op.add( UpdateDbOp.newInstance( cfName, sqlFile.getReader() ) );
				LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] DONE" );
			} else {
				LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] skip version" );
			}
		}
		LogFacade.getLog().info( "[UpdateDbOp.updateOpAll] END" );
		return op;
	}
	
	
	private List execList;
	
	private ConnectionFactory connectionFactory;
	
	public UpdateDbOp() {
		this.execList = new ArrayList();
	}
	
	public static Op newInstance( String cfName, Reader execFile ) throws IOException {
		return newInstance(cfName, Arrays.asList( ScriptFacade.parseScript( StreamIO.readString( execFile )  ) ) );
	}	
	
	public static Op newInstance( String cfName, List execList ) {
		UpdateDbOp op = new UpdateDbOp();
		op.execList = execList;
		op.connectionFactory = ConnectionFacade.getFactory( cfName );
		return op;
	}
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.std.lang.helpers.AbstractOp#doOp()
	 */
	public boolean doOp() throws Exception {
		boolean ok = true;
		this.getLog().debug( "doOp START" );
		Iterator it = this.execList.iterator();
		Connection conn = this.connectionFactory.getConnection();
		Statement stm = null;
		try {
			stm = conn.createStatement();
			while ( it.hasNext() ) {
				String current = ((String)it.next()).trim();
//				// la posizione dell' ultimo carattere
//				int index = current.length()-1;
//				// se l'ultimo carattere è un ';' lo rimuovo
//				if ( current.lastIndexOf( ";" ) == index ) {
//					current = current.substring( 0, index );
//				}
				this.getLog().debug( "doOp current : "+current );
				// aggiungo il comando corrente all' elenco di esecuzione
				stm.addBatch( current );
			}
			stm.executeBatch();
		} catch ( Exception e ){
			ConnectionFacade.closeLoose( conn, stm );
			throw e;
		}
		this.getLog().debug( "doOp END" );
		return ok;
	}

}
