package org.morozko.java.mod.tools.util.crypt;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.util.crypt.CryptObject;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class Crypt {

	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgs( args );
			String fi = argList.findArgValue( "i" );
			String fk = argList.findArgValue( "k" );
			String fo = argList.findArgValue( "o" );
			String op = argList.findArgValue( "e" );
			System.out.println( "file input   : "+fi );
			System.out.println( "file key     : "+fk );
			System.out.println( "file output  : "+fo );
			System.out.println( "operation    : "+op );
			CryptObject co = CryptObject.newCryptObject( fk );
			byte[] input = FileIO.readBytes( fi );
			byte[] output = null;
			if ( "enc".equalsIgnoreCase( op ) ) {
				output = co.encrypt( new String( input ) );
			} else {
				output = co.decrypt( input ).getBytes();
			}
			FileIO.writeBytes( output , fo );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
