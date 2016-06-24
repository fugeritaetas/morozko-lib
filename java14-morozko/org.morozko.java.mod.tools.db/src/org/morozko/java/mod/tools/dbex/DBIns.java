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
 * @(#)DBIns.java
 *
 * @project  : org.morozko.java.mod.tools.dbex
 * @package  : org.morozko.java.mod.tools.dbex
 * @creation : 22-feb-2006
 */
package org.morozko.java.mod.tools.dbex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.Statement;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DBIns {

	public static void main( String[] args ) {
	
	try {
		
		String drv = args[0];
		
		Driver d = (Driver)Class.forName( drv ).newInstance();

		File file = new File( args[4] );
		
		String usr = args[2];
		String pwd = args[3];
		String url = args[1];
		
		Properties info = new Properties();
		info.setProperty( "user", usr );
		info.setProperty( "password", pwd );
		
		Connection conn = d.connect( url, info );
		
		Statement stm = conn.createStatement();
		
		BufferedReader br = new BufferedReader( new FileReader( file ) );
		
		int err = 0;
		
		
		
		while (br.ready()) {
			String sql = br.readLine();
			try {
				System.out.println( "Sto eseguente il domando sql : '"+sql+"'" );
				
				int result = stm.executeUpdate( sql );
				
				if ( result==0 ){
					err++;
				}
				
				System.out.println( "COMANDO OK : "+result );
				
			} catch (Exception e) {
				System.out.println( "COMANDO KO [segue l'errore]" );
				e.printStackTrace( System.out );
			}
			
		}
		
		conn.close();
		
	} catch (Exception e) {
		System.err.println( "ERRORE GENERALE" );
		e.printStackTrace();
	}	
	
	}
	
}
