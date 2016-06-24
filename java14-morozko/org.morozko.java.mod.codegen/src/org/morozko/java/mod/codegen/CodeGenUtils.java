package org.morozko.java.mod.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.MessageFormat;

import org.morozko.java.core.io.StreamIO;

public class CodeGenUtils {

	public static void createFile( File file, String resourcePath, Object[] params ) throws Exception {
		if ( !file.exists() ) {
			file.getParentFile().mkdirs();
			StringWriter sw = new StringWriter();
			StreamIO.pipeChar( new InputStreamReader( CodeGen.class.getResourceAsStream( resourcePath ) ) , sw, StreamIO.MODE_CLOSE_BOTH );
			String text = sw.toString();
			if ( params != null) {
				MessageFormat mf = new MessageFormat( text );
				text = mf.format( params );
			}
			StreamIO.pipeChar( new StringReader( text ) , new FileWriter( file ), StreamIO.MODE_CLOSE_BOTH );
		}	
	}
	
	public static void createFile( File file, String resourcePath ) throws Exception {
		createFile(file, resourcePath, null);
	}
	
	public static void genFile( String templateText, Object[] params, File outputFile, boolean overwrite ) throws Exception {
		if ( !outputFile.exists() || overwrite ) {
			String fileText = genText( templateText, params);
			writeFile(outputFile, fileText);	
		}
	}
	
	public static void writeFile( File outputFile, String fileText ) throws Exception {
		outputFile.getParentFile().mkdirs();
		FileWriter w = new FileWriter( outputFile );
		w.write( fileText.toCharArray() );
		w.close();
	}
	
	public static String genText( String templateText, Object[] params ) throws Exception {
		MessageFormat format = new MessageFormat( templateText );
		return format.format( params );
	}

	
}

