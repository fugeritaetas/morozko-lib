package org.fugerit.java.core.tools.util.file;

import java.io.File;

import org.fugerit.java.core.tools.util.args.ArgList;
import org.fugerit.java.core.tools.util.args.ArgUtils;

public class Substitute {

	public static void recurse( File f ) {
		String name = f.getName();
		String newName = name.toLowerCase().replaceAll( " " , "_" );
		System.out.println( "renaming : "+name+" -> "+newName );
		f.renameTo( new File( f.getParentFile(), newName ) );
		if ( f.isDirectory() ) {
			File[] list = f.listFiles();
			for ( int k=0; k<list.length; k++ ) {
				recurse( list[k] );
			}
		}
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgs( args );
			String baseDir = argList.findArgValue( "dir" );
			File dir = new File( baseDir );
			recurse( dir );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
