package org.morozko.java.mod.tools.util.text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class TextReplace {

	public static final String VERSION = "TextReplace v 1.0.2 2014-05-14";
	
	public static void printHelp() {
		System.out.println( VERSION );
		System.out.println( " usage: TextReplace [options]" );
		System.out.println( "                    -p   [params-to-replaces] (property file)" );
		System.out.println( "                    -in  [input-file] (input-file)" );
		System.out.println( "                    -out [input-file] (output-file)" );
		System.out.println( "                    -f   (force, if set overwrite output if exists)" );
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgsDefault( args );
			String h = argList.findArgValue( "?" );
			if ( h != null ) {
				printHelp();
			} else {
				String p = argList.findArgValue( "p" );
				String i = argList.findArgValue( "in" );
				String o = argList.findArgValue( "out" );
				String f = argList.findArgValue( "f" );
				if ( p == null || i == null || o == null ) {
					System.out.println( "p, in and out are mandaroty parameters" );
					printHelp();
				} else {
					File input = new File( i );
					File output = new File( o );
					File params = new File( p );
					System.out.println( "Reading input file : "+input.getCanonicalPath() );
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					StreamIO.pipeStream( new FileInputStream( input ) , buffer, StreamIO.MODE_CLOSE_BOTH );
					String content = buffer.toString();
					System.out.println( "Reading parameters to replace : "+params.getCanonicalPath() );
					Properties props = PropsIO.loadFromFile( params );
					Iterator itParams = props.keySet().iterator();
					while ( itParams.hasNext() ) {
						String param = (String)itParams.next();
						String value = props.getProperty( param );
						System.out.println( "Replace "+param+" -> "+value );
						content = content.replaceAll( param , value );
					}
					System.out.println( "Writing to file : "+output.getCanonicalPath() );
					StreamIO.pipeStream( new ByteArrayInputStream( content.getBytes() ) , new FileOutputStream( output ), StreamIO.MODE_CLOSE_BOTH );
				}
			}
		} catch (Exception e) {
			printHelp();
			e.printStackTrace();
		}
		
	}
	
}
