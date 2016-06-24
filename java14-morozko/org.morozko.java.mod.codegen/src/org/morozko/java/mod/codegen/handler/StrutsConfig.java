package org.morozko.java.mod.codegen.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.log.LogFacade;

public class StrutsConfig {

	public static void handleStutsConfig( File strutsConfigFile, String formText, String actionText ) throws IOException {
		LogFacade.getLog().info( "StrutsConfig.handleTileDefs  file : "+strutsConfigFile.getCanonicalPath() );
		String strutsText = FileIO.readString( strutsConfigFile );
		strutsText = HandlerHelper.substituteIncludeDel( strutsText , formText, "<form-beans>", "</form-beans>" );
		strutsText = HandlerHelper.substituteIncludeDel( strutsText , actionText, "<action-mappings>", "</action-mappings>" );
		FileIO.writeBytes( strutsText.getBytes() , strutsConfigFile );
	}
	
	public static void handleTileDefs( File strutsConfigFile, String defText ) throws IOException {
		LogFacade.getLog().info( "StrutsConfig.handleTileDefs  file : "+strutsConfigFile.getCanonicalPath() );
		String strutsText = FileIO.readString( strutsConfigFile );
		strutsText = HandlerHelper.substituteIncludeDel( strutsText , defText, "<!-- template start -->", "<!-- template end -->" );
		FileIO.writeBytes( strutsText.getBytes() , strutsConfigFile );
	}	
	
	public static void handleMessageProps( File strutsMessageFile, List<String> messages ) throws Exception {
		PrintWriter pw = new PrintWriter( new FileWriter( strutsMessageFile ) );
		for ( int k=0; k<messages.size(); k++ ) {
			pw.println( messages.get( k ) );
		}
		pw.close();
	}
	
	
}
