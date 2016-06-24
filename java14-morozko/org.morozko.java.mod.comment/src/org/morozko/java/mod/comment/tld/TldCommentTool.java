package org.morozko.java.mod.comment.tld;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.comment.tld.model.DefinitionModel;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class TldCommentTool {

	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgsDefault( args );
			
			File f = new File( argList.findArgValue( "f" ) );
			
			String title = argList.findArgValue( "t" );
			
			LogFacade.getLog().info( "TldCommentTool f : "+f.getCanonicalPath() );
			
			Reader r = new FileReader( f );
			
			DefinitionModel definitionModel = TldParser.parse( r );
			
			r.close();
			
			PrintStream out = System.out;
			
			if ( argList.findArg( "o" ) != null ) {
				out = new PrintStream( new FileOutputStream( new File( argList.findArgValue( "o" ) ) ) );
			}
			
			TldParser.generateCommentHTML( out , title , definitionModel );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
