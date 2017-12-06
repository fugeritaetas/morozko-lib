package org.morozko.java.mod.tools.dbex;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class TableScript {

//	private static void extractTable( Connection conn, String table, PrintWriter pw ) throws Exception {
//		
//		String sql = " SELECT * FROM "+table+" ";
//		
//		Statement stm = conn.createStatement();
//		
//		ResultSet rs = stm.executeQuery( sql );
//		
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int cols = rsmd.getColumnCount();
//		
//		String basic = " INSERT INTO "+table+" ( ";
//		for ( int k=1; k<cols; k++ ) {
//			basic+= rsmd.getColumnName( k )+" ,";
//		}
//		basic+= rsmd.getColumnName( cols )+" ) VALUES ( ";
//		
//		while ( rs.next() ) {
//			String current = basic;
//			for ( int k=1; k<cols; k++ ) {
//				current+= addCurrent( rs, rsmd, k )+" , ";
//			}
//			current+= addCurrent( rs, rsmd, cols )+" ); ";
//			pw.println( current );
//		}
//		
//		
//	}
	
	public static void main( String[] args ) {
		
		try {
			
			ArgList argList = ArgUtils.parseArgsDefault( args );

			PrintWriter pw = new PrintWriter( System.out );
					
			ConnectionFactory cf = ConnArgs.createConnectionFactory( argList );
			
			Connection conn = cf.getConnection();
	
			DatabaseMetaData dbmd = conn.getMetaData();
			
			String[] types = { "TABLE" };
			
			String s = argList.findArgValue( "s" );
			
			ResultSet rs = dbmd.getTables( null, s, null, types );
			
			while (rs.next()) {
				String schema = rs.getString( "TABLE_SCHEM" );
				String name = rs.getString( "TABLE_NAME" );
				if ( schema != null ) {
					name = "\""+schema+"\"."+name;
				}
				pw.println( "GRANT INSERT, UPDATE, DELETE, SELECT ON "+name+" TO MTTFRANCI;");
			}
			
			
			conn.close();
			
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
