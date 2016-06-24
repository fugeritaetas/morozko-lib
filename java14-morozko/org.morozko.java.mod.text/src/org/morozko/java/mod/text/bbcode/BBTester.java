package org.morozko.java.mod.text.bbcode;

import java.io.File;

public class BBTester {

	public static void main( String[] args ) {
		try {
			String config = args[0];
			String text = args[1];
			BBCodeFactory factory = new BBCodeFactory();
			factory.registerConfigFile( "test" , config );
			System.out.println( "PRE  >>> "+text );
			System.out.println( "POST >>> "+BBCodeFactory.process( factory.newHandler( "test" ) , text ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
