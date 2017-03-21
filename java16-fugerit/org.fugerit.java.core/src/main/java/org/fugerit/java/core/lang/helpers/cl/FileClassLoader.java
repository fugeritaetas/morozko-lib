/*****************************************************************
<copyright>
	Morozko Java Library org.fugerit.java.core 

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
 * @(#)FileClassLoader.java
 *
 * @project	   : org.fugerit.java.core
 * @package	   : org.fugerit.java.core.lang.cl
 * @creation   : 14-set-2005 1.04.55
 */
package org.fugerit.java.core.lang.helpers.cl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class FileClassLoader extends URLClassLoader {

	private static URL[] resolveURL( File f, File tmpDir ) {
		URL[] list= new URL[1];
		if (f.isDirectory()) {
			try {
				list[0] = new URL( "file://"+f.getCanonicalPath()+File.separator );
			} catch (MalformedURLException e) {
				throw (new RuntimeException( e ));
			} catch (IOException e) {
				throw (new RuntimeException( e ));
			}
		} else {
			try {
				JarInputStream jis = new JarInputStream( new FileInputStream( f ) );
				JarEntry entry = jis.getNextJarEntry();
				while (entry!=null) {
					String name = entry.getName();
					long size = entry.getSize();
					System.out.println( "NAME : "+name );
					System.out.println( "SIZE : "+size );
					if ( name.charAt( name.length()-1 )=='/' ) {
						File current = new File( tmpDir, name+"/" );
						System.out.println( "DIR : "+current.mkdirs() );
					} else {
						File current = new File( tmpDir, name );
						System.out.println( "FILE" );
						FileOutputStream fos = new FileOutputStream( current );
						while (jis.available()>0) {
							fos.write( jis.read() );
						}
						
						fos.flush();
						fos.close();
					}
					jis.closeEntry();
					entry = jis.getNextJarEntry();
				}
				jis.close();
				list = resolveURL( tmpDir, tmpDir );
			} catch (IOException e) {
				throw (new RuntimeException( e ));
			}
		}
		return list; 
	}
	
	public FileClassLoader( File f, File tmpDir ) {
		this( f, tmpDir , ClassLoader.getSystemClassLoader());
	}
	
	public FileClassLoader(File f , File tmpDir, ClassLoader parent) {
		super( resolveURL( f, tmpDir ) );
	}

	public static void main(String[] args) {
		try {
			FileClassLoader cl = new FileClassLoader( 
						new File( "C:\\Programmi\\PostgreSQL\\8.0\\jdbc\\postgresql-8.0-310.jdbc2ee.jar" ),
						new File( "C:\\test" )
					);
			cl.loadClass( "org.postgresql.Driver" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
