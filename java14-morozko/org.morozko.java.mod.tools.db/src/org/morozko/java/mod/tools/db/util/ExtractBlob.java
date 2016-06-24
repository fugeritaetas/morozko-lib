package org.morozko.java.mod.tools.db.util;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.morozko.java.core.io.StreamIO;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class ExtractBlob {

	public static void printHelp() {
        System.err.println(" options : ");
        System.err.println("      -d  [jdbc-driver-class] ");
        System.err.println("      -c  [jdbc-url] ");
        System.err.println("      -u  [username] ");
        System.err.println("      -p  [password] ");
        System.err.println("      -o  [output-file] ");
        System.err.println("      -q  [query] must have a single blob field in result ");
        System.err.println();
	}
	
    public static void main(String[] arg) {
        try {
            ArgList list = ArgUtils.parseArgsProps(arg);
            if ( list.findArgValue( "?" ) != null ) {
            	printHelp();
            } else {
            	ConnectionFactory cp = ConnArgs.createConnectionFactory( list );
                String query = list.findArgValue( "q" );
                String out = list.findArgValue( "o" );
                if ( out == null ) {
                	throw new Exception( "output file (-o) is a mandatory parameter" );
                }
                if ( query == null ) {
                	throw new Exception( "query (-q) is a mandatory parameter" );
                }	
                Connection conn = cp.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery( query );
                if ( rs.next() ) {
                	Blob b = rs.getBlob( 1 );
                	StreamIO.pipeStream( b.getBinaryStream() , new FileOutputStream( new File( out ) ), StreamIO.MODE_CLOSE_BOTH );
                }
                conn.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
}
