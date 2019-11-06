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
 * @(#)JSQL.java
 *
 * @project  : org.morozko.java.mod.tools.db
 * @package  : org.morozko.java.mod.tools.db.jsql
 * @creation : 20-mar-2006
 */
package org.morozko.java.mod.tools.db.jsql;

import java.io.File;
import java.io.FileOutputStream;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.io.line.LineIOUtils;
import org.morozko.java.core.io.line.LineWriter;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.cmd.CMD;
import org.morozko.java.mod.cmd.CMDException;
import org.morozko.java.mod.cmd.CMDOutput;
import org.morozko.java.mod.cmd.CMDOutputFormat;
import org.morozko.java.mod.cmd.CMDPrompt;
import org.morozko.java.mod.cmd.format.BufferedOutputFormat;
import org.morozko.java.mod.cmd.format.LineFormat;
import org.morozko.java.mod.cmd.format.TableCMDFormat;
import org.morozko.java.mod.cmd.helpers.BufferedCMD;
import org.morozko.java.mod.cmd.helpers.PaddedCMDOutput;
import org.morozko.java.mod.db.cmd.sql.SQLCMDUtils;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.sql.ScriptFacade;
import org.morozko.java.mod.tools.ToolUtils;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class JSQL {
    
    public static final String VERSION = "0.2.0 (2017-12-07)";
    
    public static final int EXIT_OK = 0;
    public static final int EXIT_EXCEPTION = 1;
    
    private static void printInfo() {
        ToolUtils.printInfo("JSQL v. "+VERSION, "Fugerit/Morozko");
    }
    
    private static void format( CMDOutputFormat format, CMDOutput output, String type ) throws CMDException {
    	if ( "on".equalsIgnoreCase( type ) ) {
    		format.printCMDOutput( new PaddedCMDOutput( output ) );
    	} else if ( "off".equalsIgnoreCase( type ) ) {
    		format.printCMDOutput( output );
    	} else {
    		format.printCMDOutput( new PaddedCMDOutput( output ) );
    	}
    	
    }
    
    public static void main(String[] arg) {
    	int exit = EXIT_OK;
        try {
            
            ArgList list = ArgUtils.parseArgsProps(arg);
            
            ConnectionFactory cp = ConnArgs.createConnectionFactory( list );
            
            String execute = list.findArgValue( "e" );
            
            String file = list.findArgValue( "f" );
            
            String scriptFile = list.findArgValue( "s" );
            
            System.out.println( "SCRIPT FILE : '"+scriptFile+"'" );
            
            String fileOutput = list.findArgValue( "o" );
            
            String b = list.findArgValue( "b" );
            
            String t = list.findArgValue( "t" );
            
            boolean buffer = list.containsArg( "buffer" );

            CMD cmd = SQLCMDUtils.newSQLCMDComplete(cp);
            if ( buffer ) {
            	LogFacade.getLog().info( "adding buffered cmd : "+buffer );
            	cmd = new BufferedCMD( cmd );
            	File bufferFile = new File( list.findArgValue( "buffer" ) );
            	if ( bufferFile.exists() ) {
            		CMDOutput out = cmd.handleCommand( BufferedCMD.CMD_LOAD+" "+bufferFile.getCanonicalPath() );
            		while ( out.nextRow() ) {
            			String[] row = out.getRow();
            			for ( int ri=0; ri<row.length; ri++ ) {
            				LogFacade.getLog().info( BufferedCMD.CMD_LOAD+" result : "+row[ri] );
            			}
            		}
            	} else {
            		LogFacade.getLog().info( "buffer file does not exists : "+buffer );
            	}
            }
            
            LineWriter writer = LineIOUtils.createLineWriter( System.out );
            
            if ( fileOutput != null ) {
            	writer = LineIOUtils.createLineWriter( new FileOutputStream( new File( fileOutput ) ) );
            }
            
            CMDOutputFormat format = new TableCMDFormat(writer);
            
            if ( "csv".equalsIgnoreCase( t ) ) {
            	format = LineFormat.getFormatCSV( writer );
            } else if ( "buffered".equalsIgnoreCase( t ) ) {
            	format = new BufferedOutputFormat( format );
            }
            
            if ( execute == null && file == null && scriptFile == null ) {
            	 printInfo();
            	 CMDPrompt prompt = new CMDPrompt( LineIOUtils.createLineReader( System.in ), writer, cmd, ">>", format );
            	 System.out.println("\\? - help");
                 System.out.println("\\q - quit");
                 System.out.println();
                 prompt.start("\\q");
                 printInfo();
            } else if ( execute != null ) {
            	printInfo();
            	System.out.println( "EXECUTE : '"+execute+"'" );
            	CMDOutput out = cmd.handleCommand( execute );
            	format( format, out, b );
            	System.out.println( "ROW COUNT : '"+out.getCurrentRowCount()+"'" );
            } else if ( file != null ) {
            	printInfo();
            	format( format, cmd.handleCommand( FileIO.readString( new File( file ) ) ), b );
            } else if ( scriptFile != null ) {
            	File sf = new File( scriptFile );
            	System.out.println( "EXISTS? "+sf.exists()+" '"+sf.getCanonicalPath()+"'" ); 
            	String text = FileIO.readString( sf );
            	String[] sql = ScriptFacade.parseScript( text );
            	for ( int k=0; k<sql.length; k++ ) {
            		System.out.println( "executing : "+sql[k] );
            		format( format, cmd.handleCommand( sql[k] ), b );
            	}
            }
            
        } catch (Exception e) {
            System.err.println(" options : ");
            System.err.println("      -d  [jdbc-driver-class] ");
            System.err.println("      -c  [jdbc-url] ");
            System.err.println("      -u  [username] ");
            System.err.println("      -p  [password] ");
            System.err.println("      -e  [command-to-execute] ");
            System.err.println("      -f  [execute-file] ");
            System.err.println("      -s  [execute-script] (comma separated sql statement, unsafe) ");
            System.err.println("      -o  [output-file] ");
            System.err.println("      -b  [padded|on|off] ");
            System.err.println("      -t  [table|csv] ");
            System.err.println();
            e.printStackTrace();
            exit = EXIT_EXCEPTION;
        }
        
        System.out.println( "EXIT STATUS '"+exit+"'" );
        System.exit( exit );
    }    
    
    public JSQL() {
        super();
    }

}

