package org.morozko.java.mod.tools.dbex;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.DAOException;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class ExtractScript {

	public static void table( String schema, String table, ConnectionFactory cf, PrintWriter pw ) throws DAOException, SQLException {
		Connection conn = cf.getConnection();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery( "SELECT * FROM "+schema+"."+table+" WHERE 1=0 " );
		ResultSetMetaData rsmd = rs.getMetaData();
		pw.println( "CREATE TABLE "+table+" ( " );
		for ( int k=0; k<rsmd.getColumnCount(); k++ ) {
			int current = k+1;
			pw.print( "  "+rsmd.getColumnName( current )+" "+rsmd.getColumnTypeName( current ) );
			if ( rsmd.getColumnType( current ) == Types.VARCHAR || rsmd.getColumnType( current ) == Types.CHAR ) {
				pw.print( "("+rsmd.getColumnDisplaySize( current )+")" );	
			}
			if ( current != rsmd.getColumnCount() ) {
				pw.print( ","  );	
			}
			pw.println();
		}
		pw.println( "); " );
		stm.close();
		rs.close();
		conn.close();
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgsDefault( args );
			PrintWriter pw = new PrintWriter( System.out );
			String schema = "EQUITALIA";
			String tables = "EQ_SAC_SER_AMBITO_ANAGRAFICA;EQ_SAC_SER_AMBITO_ADR;EQ_SAC_SER_AMBITO_REGIONE;EQ_SAC_SER_AMBITO_SI";
			ConnectionFactory cf = ConnArgs.createConnectionFactory( argList );
			String[] t = tables.split( ";" );
			for ( int k=0; k<t.length; k++ ) {
				table( schema, t[k] , cf ,pw );	
			}
			
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
