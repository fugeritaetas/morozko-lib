package org.fugerit.java.core.tools.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OneLine {

	public static void main( String[] args ) {
		try {
			BufferedReader reader = new BufferedReader( new FileReader( new File( args[0] ) ) );
			StringBuffer result = new StringBuffer();
			while ( reader.ready() ) {
				result.append( reader.readLine() );
				result.append( " " );
			}
			System.out.println( result );
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
	
}
