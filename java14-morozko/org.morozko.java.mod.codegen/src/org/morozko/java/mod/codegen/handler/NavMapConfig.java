package org.morozko.java.mod.codegen.handler;

import java.io.File;
import java.io.IOException;

import org.morozko.java.core.io.FileIO;

public class NavMapConfig {

	public static void handleProps( File navmapProperties, String text ) throws IOException {
		navmapProperties.getParentFile().mkdirs();
		FileIO.writeBytes( text.getBytes() , navmapProperties );
	}	
	
	public static void handleNavMap( File navmapConfigFile, String entryText, String nodeText ) throws IOException {
		String strutsText = FileIO.readString( navmapConfigFile );
		nodeText = nodeText.replaceAll( "<nav-map>" , "" );
		nodeText = nodeText.replaceAll( "</nav-map>" , "" );
		strutsText = HandlerHelper.substituteIncludeDel( strutsText , entryText, "<nav-entry-list>", "</nav-entry-list>" );
		strutsText = HandlerHelper.substituteIncludeDel( strutsText , nodeText, "<nav-tree>", "</nav-tree>" );
		FileIO.writeBytes( strutsText.getBytes() , navmapConfigFile );
	}
	
}
