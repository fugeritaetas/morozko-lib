/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools 

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
 * @(#)Starter.java
 *
 * @project  : org.morozko.java.mod.tools
 * @package  : org.morozko.java.mod.tools
 * @creation : 20-mar-2006
 */
package org.morozko.java.mod.tools;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.morozko.java.core.lang.cl.ClassLoaderFacade;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class Starter {
	
	private static void printList( PrintStream out, Properties props ) {
		out.println( "subcommand list : " );	
        Enumeration e = props.keys();
        while ( e.hasMoreElements() ) {
        	out.println( "      "+e.nextElement() );
        }
	}	
	
	private static void printHelp( PrintStream out ) {
        out.println(" main command, ");
        out.println(" options : ");
        out.println("      -launchprops [propfile] ");
        out.println("      -launch      [command] ");
        out.println("      -launchlib   [path] ");
        out.println("      -launchlist        ");
        out.println("      -help        ");
        out.println();
        out.println( " subcommand, " );	
	}		
	
	public static void main( String [] args ) {
		try {
			
			Properties props = new Properties();
			InputStream is = Starter.class.getResourceAsStream( "/org/morozko/java/mod/tools/starter.properties" );
			props.load( is );
			is.close();
			
			ArgList argList = ArgUtils.parseArgsProps( args );
			
			Arg help = argList.findArg( "help" );
			
			Arg listArg = argList.findArg( "launchlist" );
			
			if ( help != null ) {
				printHelp( System.out );
			}
			
			if ( listArg != null ) {
				printList( System.out, props );
			} else {
				List list = new ArrayList();
				String classpath = System.getProperty( "java.class.path" );
				
				String[] cpList = classpath.split( File.pathSeparator );
				for ( int k=0; k<cpList.length; k++ ){
					list.add( new File( cpList[k] ) );
				}
				
				ClassLoader cl = Starter.class.getClassLoader();
				
				Arg lib = argList.findArg( "launchlib" );
				
				if ( lib != null ) {
					
					File libFile = new File( lib.getValue() );
					File[] listFile = libFile.listFiles();
					list.addAll( Arrays.asList( listFile ) );
					
					File[] file = new File[ list.size() ];
					for ( int k=0; k<file.length; k++ ) {
						file[k] = (File)list.get(k);
					}
					cl = ClassLoaderFacade.fromDir( file, cl );
				}
				
				Arg launch = argList.findArg( "launch" );
				String cName = props.getProperty( launch.getValue() );
				Class c = cl.loadClass( cName );
				Object obj = c.newInstance();
				Class[] pt = { String[].class };
				Method main = c.getMethod( "main", pt );
				Object[] newArgs = { argList.toArgs() };
				main.invoke( obj, newArgs );				
			}
			
			

		} catch (Exception e) {
			printHelp( System.err );
			e.printStackTrace();
		}
	}
	
}
