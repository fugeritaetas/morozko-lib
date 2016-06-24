package org.morozko.java.mod.codegen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.io.StreamIO;

public class SetupHandler {

	private static final String[] TLDS = {
		"mdoc-1-0.tld", "mformat-1-0.tld", "mhtml-1-0.tld", "mlist-1-0.tld",
		"mlogic-1-0.tld", "mmap-1-0.tld", "navmap.tld",
		"struts-bean.tld", "struts-html.tld", "struts-logic.tld",
		"struts-nested.tld", "struts-tiles.tld",
		"c.tld", "x.tld", "fn.tld", "fmt.tld", "sql.tld"
	};
	
	public static void setupTlds( File dirBase ) throws IOException {
		File dirBaseTlds = new File( dirBase, "tlds" );
		if ( !dirBaseTlds.exists() ) {
			File webXml = new File( dirBase, "web.xml"  );
			String content = FileIO.readString( webXml );
			ByteArrayOutputStream jspConfig = new ByteArrayOutputStream();
			StreamIO.pipeStream( SetupHandler.class.getResourceAsStream( "/org/morozko/java/mod/codegen/res/jspconfig.txt" ) , jspConfig, StreamIO.MODE_CLOSE_BOTH );
			int index = content.indexOf( "</welcome-file-list>" )+"</welcome-file-list>".length();
			content = content.substring( 0, index )+jspConfig.toString()+content.substring( index );
			StreamIO.pipeStream( new ByteArrayInputStream( content.getBytes() ) , new FileOutputStream( webXml ) );
			dirBaseTlds.mkdirs();
			for ( int k=0; k<TLDS.length; k++ ){
				StreamIO.pipeStream( 
						SetupHandler.class.getResourceAsStream( "/org/morozko/java/mod/codegen/res/tlds/"+TLDS[k] ) , 
						new FileOutputStream( new File( dirBaseTlds, TLDS[k] ) ), 
						StreamIO.MODE_CLOSE_BOTH );
			}
		}
	}
	
}
