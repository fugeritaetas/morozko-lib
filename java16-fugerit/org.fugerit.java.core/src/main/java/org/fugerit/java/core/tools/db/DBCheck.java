package org.fugerit.java.core.tools.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.io.FileIO;
import org.fugerit.java.core.tools.util.args.ArgList;
import org.fugerit.java.core.tools.util.args.ArgUtils;
import org.fugerit.java.core.tools.util.args.ConnArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBCheck {

	private final static Logger LOGGER = LoggerFactory.getLogger( DBCheck.class );
	
	private static void print( String line ) {
		LOGGER.info( line );
	}
	
	public static void main( String[] args ) {
		print( "START" );
		try {
			ArgList list = ArgUtils.parseArgsProps( args );
			ConnectionFactory cf = ConnArgs.createConnectionFactory( list );
			Connection conn = cf.getConnection();
			try {
				String meta = list.findArgValue( "meta" );
				if ( "true".equalsIgnoreCase( meta ) ) {
					DatabaseMetaData dmbd = conn.getMetaData();
					print( "Database metadata" );
					print( "-----------------" );
					print( "ProductName="+dmbd.getDatabaseProductName() );
					print( "ProductVersion="+dmbd.getDatabaseProductVersion() );
					print( "DriverName="+dmbd.getDriverName() );
					print( "DriverVersion="+dmbd.getDriverVersion() );
				}
				String query = list.findArgValue( "query" );
				if ( query != null ) {
					print( "Execute statement : "+query );
					Statement stm = conn.createStatement();
					boolean result = stm.execute( query );
					print( "result -> "+result );
					stm.close();
				}
				String script = list.findArgValue( "script" );
				if ( script != null ) {
					print( "Execute script : "+script );
					String content = FileIO.readString( new File( script ) );
					String sqls[] = content.split( ";" );
					Statement stm = conn.createStatement();
					for ( int k=0; k<sqls.length; k++ ) {
						String current = sqls[0];
						if ( current.trim().length() > 0 ) {
							print( "Execute statement : "+current );
							boolean result = stm.execute( current );
							print( "result -> "+result );
						}							
					}
					
					
					
					stm.close();
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if ( conn != null ) {
					conn.close();
				}
			}
		} catch (Exception e) {
			LOGGER.error( "ERROR", e );
		}
		print( "END" );
	}
	
}
