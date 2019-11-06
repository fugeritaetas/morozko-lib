package org.morozko.java.mod.tools.util.file;

import java.io.File;

import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class Rename {

	public static void recurse( File f, String from, String to ) {
		if ( f.isDirectory() ) {
			File[] list = f.listFiles();
			for ( int k=0; k<list.length; k++ ) {
				recurse( list[k], from, to );
			}
		} else {
			String oldName = f.getName();
			String newName = oldName.replaceAll( from , to );
			if ( !oldName.equals( newName ) ) {
				System.out.println( "from:"+f.getName()+" to:"+newName+" result:"+f.renameTo( new File( f.getParentFile(), newName ) ) );
					
			}
		}
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgs( args );
			String baseDir = argList.findArgValue( "dir" );
			String from = argList.findArgValue( "from" );
			String to = argList.findArgValue( "to" );
			File dir = new File( baseDir );
			recurse( dir, from, to );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
