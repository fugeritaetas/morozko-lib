/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools.db 

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
 * @(#)DBExtract.java
 *
 * @project  : org.morozko.java.mod.tools.dbex
 * @package  : org.morozko.java.mod.tools.dbex
 * @creation : 22-feb-2006
 */
package org.morozko.java.mod.tools.dbex;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DBExtract {

	private static String prepareString( String v ) {
		return v.replaceAll( "'", "''" ).replaceAll( "\\\\" , "\\\\" );
		
	}
	
	private static String addCurrent( ResultSet rs, ResultSetMetaData rsmd, int index ) throws SQLException {
		String current = "";
		int type = rsmd.getColumnType( index );
		Object obj = rs.getObject( index );
		if ( obj == null ) {
			current = "null";
		} else if ( type == Types.LONGVARCHAR || type == Types.VARCHAR || type == Types.CHAR || type == Types.DATE || type == Types.TIME || type == Types.TIMESTAMP ){
			current = "'"+prepareString( obj.toString() )+"'";
		} else {
			current =  obj.toString();
		}
		return current;
	}
	
	private static void extractTable( Connection conn, String table, PrintWriter pw ) throws Exception {
		
		String sql = " SELECT * FROM "+table+" ";
		
		Statement stm = conn.createStatement();
		
		ResultSet rs = stm.executeQuery( sql );
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		
		String basic = " INSERT INTO "+table+" ( ";
		for ( int k=1; k<cols; k++ ) {
			basic+= rsmd.getColumnName( k )+" ,";
		}
		basic+= rsmd.getColumnName( cols )+" ) VALUES ( ";
		
		while ( rs.next() ) {
			String current = basic;
			for ( int k=1; k<cols; k++ ) {
				current+= addCurrent( rs, rsmd, k )+" , ";
			}
			current+= addCurrent( rs, rsmd, cols )+" ); ";
			pw.println( current );
		}
		
		
	}
	
	public static void main( String[] args ) {
		
		try {
			
			ArgList argList = ArgUtils.parseArgsDefault( args );

			PrintWriter pw = new PrintWriter( System.out );
			
			Arg fileArg = argList.findArg( "f" );
		
			if ( fileArg != null ) {
				pw = new PrintWriter( new FileWriter( new File( fileArg.getValue() ) ) );
			}
		
			String table = "";
			List tableList = null;
			Arg tableArg = argList.findArg( "t" );
			
			String s = argList.findArgValue( "s" );
			
			if ( tableArg != null ) {
				table = tableArg.getValue().toLowerCase();
				tableList = Arrays.asList( table.toLowerCase().split( ";" ) );
			}
			
			ConnectionFactory cf = ConnArgs.createConnectionFactory( argList );
			
			Connection conn = cf.getConnection();
			
			DatabaseMetaData dbmd = conn.getMetaData();
			
			String[] types = { "TABLE" };
			
			ResultSet rs = dbmd.getTables( null, s, null, types );
			
			 
			
			while (rs.next()) {
				String schema = rs.getString( "TABLE_SCHEM" );
				String name = rs.getString( "TABLE_NAME" );
				if ( schema != null ) {
					name = schema+"."+name;
				}
				System.out.println( "check : '"+name.toLowerCase()+"' -> '"+table+"' ("+tableList.contains( name.toLowerCase() )+" )" );
				if ( tableList == null || tableList.contains( name.toLowerCase() ) ) {
					pw.println( "-- table : "+name );
					extractTable( conn, name, pw);
					pw.println();
					pw.println();
				}
			}
			
			
			conn.close();
			
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
