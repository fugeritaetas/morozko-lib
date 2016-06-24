package org.morozko.java.tools.mini.file;

import java.io.File;

public class Renamer {

	public static void main( String[] args ) {
		File file = new File( args[0] );
		String[] list = file.list();
		for ( int k=0; k<list.length; k++ ) {
			File currDir = new File( file, list[k] );
			String[] fileList = currDir.list();
			for ( int i=0; i<fileList.length; i++ ) {
				File currFile = new File( currDir, fileList [i] );
				File newFile = new File( currDir, list[k]+"_"+fileList[i] );
				currFile.renameTo( newFile );
			}
		}
	}
	
}
