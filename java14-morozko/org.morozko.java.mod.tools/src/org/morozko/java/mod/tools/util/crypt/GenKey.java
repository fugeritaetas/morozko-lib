package org.morozko.java.mod.tools.util.crypt;

import org.morozko.java.core.util.crypt.CryptObject;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class GenKey {

	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgs( args );
			String t = argList.findArgValue( "t", "rsa" );
			String l = argList.findArgValue( "l", "128" );
			String f = argList.findArgValue( "f" );
			System.out.println( "type   : "+t );
			System.out.println( "length : "+l );
			System.out.println( "file   : "+f );			
			CryptObject.generateKey( f , Integer.parseInt( l ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
