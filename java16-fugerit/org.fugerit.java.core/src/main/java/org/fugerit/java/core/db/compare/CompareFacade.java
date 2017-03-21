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
 * @(#)DbCompare.java
 *
 * @project    : org.fugerit.java.core.db
 * @package    : org.fugerit.java.core.db.compare
 * @creation   : 20/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.fugerit.java.core.db.compare;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;

import org.fugerit.java.core.db.metadata.query.QueryColumnMap;
import org.fugerit.java.core.db.metadata.query.QueryColumnModel;
import org.fugerit.java.core.db.metadata.query.QueryMetadataFacade;
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
public class CompareFacade {
	
	private static int compare( QueryColumnMap columnMap1, QueryColumnMap columnMap2, PrintStream stream ) {
		Iterator it = columnMap1.iterator();
		int e1 = 0;
		int e2 = 0;
		int e3 = 0;
		int ok = 0;
		int index = 1;
		while ( it.hasNext() ) {
			QueryColumnModel col1 = (QueryColumnModel) it.next();
			QueryColumnModel col2 = (QueryColumnModel) columnMap2.get( col1.getName() );
			if ( col2 == null ) {
				stream.println( "Not exists          : ("+index+") "+col1.getName() );
				e1++;
			} else if ( !col1.getTypeClass().equals( col2.getTypeClass() ) ) {
				stream.println( "Different java type : ("+index+") "+col1.getTypeClass()+" - "+col2.getTypeClass() );
				e2++;
			} else if ( col1.getType() != col2.getType() ) {
				stream.println( "Different sql  type : ("+index+") "+col1.getTypeName()+" - "+col2.getTypeName() );
				e3++;
			} else {
				ok++;
			}
			index++;
		}
		stream.println( "Total not exists          : "+e1 );
		stream.println( "Total different java type : "+e2 );
		stream.println( "Total different sql  type : "+e3 );
		stream.println( "Total ok                  : "+ok );
		return e1+e2+e3;
	}
		
	public static void compare( Element config ) throws Exception {
		CompareConfig compareConfig = new CompareConfig();
		compareConfig.configure(config);
		compare( compareConfig , System.out );
	}
	
	public static void compare( CompareConfig compareConfig, PrintStream stream ) throws Exception {
		Connection from = compareConfig.getFactoryFrom().getConnection();
		Connection to = compareConfig.getFactoryTo().getConnection();
		try {
			
			StringBuffer tableOK = new StringBuffer();
			StringBuffer tableKO = new StringBuffer();
			
			tableOK.append( "Query ok :\n" );
			tableKO.append( "Query ko :\n" );
			
			Iterator it = compareConfig.entries();
			
			while ( it.hasNext() ) {
				
				CompareEntry compareEntry = (CompareEntry)it.next();
				
				PreparedStatement fromStm = from.prepareStatement( " SELECT * FROM ("+compareEntry.getQueryFrom()+" ) t WHERE 1=0" );
				PreparedStatement toStm = to.prepareStatement( " SELECT * FROM ("+compareEntry.getQueryTo()+" ) t WHERE 1=0" );
				
				ResultSet fromRS = fromStm.executeQuery();
				ResultSet toRS = toStm.executeQuery();
				
				ResultSetMetaData toRsmd = toRS.getMetaData();
				ResultSetMetaData fromRsmd = fromRS.getMetaData();
				
				QueryColumnMap fromMap = QueryMetadataFacade.columnMap( fromRsmd );
				QueryColumnMap toMap = QueryMetadataFacade.columnMap( toRsmd );
				
				stream.println( "From query : "+compareEntry.getQueryFrom() );
				stream.println( "To query : "+compareEntry.getQueryTo() );
				
				stream.println();
				
				stream.println( "Total columns in from query : "+fromMap.size() );
				stream.println( "Total columns in to   query : "+toMap.size() );
				
				stream.println();
				stream.println();
				
				stream.println( "Comparing from-to" );
				int r1 = compare( fromMap , toMap, stream);
				
				stream.println();
				stream.println();
				
				stream.println( "Comparing to-from" );
				int r2 = compare( toMap , fromMap, stream);
					
				fromRS.close();
				toRS.close();
				
				fromStm.close();
				toStm.close();
				
				if ( r1+r2 == 0 ) {
					tableOK.append( compareEntry.getQueryFrom()+"->"+compareEntry.getQueryTo()+"\n" );
				} else {
					tableKO.append( compareEntry.getQueryFrom()+"->"+compareEntry.getQueryTo()+" ("+(r1+r2)+")\n" );
				}
				
			}
				
			stream.println();
			stream.println();
			stream.println("Summary");
			stream.println();
			stream.println( tableOK );
			stream.println( tableKO );
			
		} catch (Exception e) {
			from.close();
			to.close();
			throw e;
		}
		
	}
	
	public static void main( String[] args ) {
		try {
			String file = args[0];
			
			Document config = DOMIO.loadDOMDoc( new File( file ) );
			
			CompareFacade.compare( config.getDocumentElement() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
}



