/*
 * @(#)Copyright.java
 *
 * @project    : org.morozko.java.mod.tools
 * @package    : org.opinf.jlib.tools.mod.source
 * @creation   : 03/nov/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.tools.source;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.io.file.AbstractFileFun;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p>
 *	<jdl:section>
 * 		<jdl:text lang='it'>
 * 		</jdl:text>
 * 		<jdl:text lang='en'>
 * 		</jdl:text>  
 *	</jdl:section>
 * </p>
 *
 * @author mfranci
 *
 */
public class Copyright {

	protected final static ResourceBundle CONFIG = ResourceBundle.getBundle( "org.morozko.java.mod.tools.source.copyright" );
	
	public static void main( String[] args ) {
		try {
			ArgList list = ArgUtils.parseArgsDefault( args );
			String f = list.findArgValue( "f" );
			String d = list.findArgValue( "d" );
			Arg p = list.findArg( "p" );
			Arg projectDir = list.findArg( "project-dir" );
			String text = FileIO.readString( f );
			LogFacade.getLog().debug( "Copyright text string simple   : "+text );
			LogFacade.getLog().debug( "Copyright text string modified : "+text );
			String[] dirList = d.split( ";" );
			for ( int k=0; k<dirList.length; k++ ) {
				File currentDir =  new File( dirList[k] );
				LogFacade.getLog().info( "Handling dir : "+currentDir.getCanonicalPath() );
				String copyright = text;
				if ( p != null ) {
					String project = p.getValue();
					if ( projectDir != null ) {
						project+= currentDir.getName();
					}
					copyright = copyright.replaceAll( CONFIG.getString( "copyright.config.project.name.pattern" ) , project );
				}				
				CopyrightFileFun fun = new CopyrightFileFun( copyright );
				FileIO.recurseDir( currentDir , fun );	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class CopyrightFileFun extends AbstractFileFun {

	private static final String CONFIG_TAG_OPEN = Copyright.CONFIG.getString( "copyright.config.copyright.tag.open" );
	private static final String CONFIG_TAG_CLOSE = Copyright.CONFIG.getString( "copyright.config.copyright.tag.open" );
	private static final String CONFIG_PATTERN = Copyright.CONFIG.getString( "copyright.config.copyright.pattern" );
	
	
	
	public CopyrightFileFun( String copyright ) {
		this.copyright = copyright;
	}
	
	private String copyright; 
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.io.file.AbstractFileFun#handleFile(java.io.File)
	 */
	public void handleFile(File file) throws IOException {
		if ( file.isFile() ) {
			if ( file.getName().indexOf( ".java" ) != -1 ) {
				LogFacade.getLog().info( "CopyrightFileFun.handleFile (java) file : "+file );
				String text = FileIO.readString( file );
				if ( text.indexOf( Copyright.CONFIG.getString( "copyright.config.copyright.check" ) ) == -1 ) {
					LogFacade.getLog().info( "CopyrightFileFun.handleFile adding copyright info" );
					String add = "/*******************************************************\n";
					add+= CONFIG_TAG_OPEN+"\n";
					add+= this.copyright+"\n";
					add+= CONFIG_TAG_CLOSE+"\n";
					add+= "*******************************************************/\n";
					text = add+text;
				} else {
					LogFacade.getLog().info( "CopyrightFileFun.handleFile changing copyright info" );
					String pattern = CONFIG_PATTERN;
					text = text.replaceAll( pattern, CONFIG_TAG_OPEN+"\n"+this.copyright+"\n"+CONFIG_TAG_CLOSE );
				}
				LogFacade.getLog().debug( "CopyrightFileFun.handleFile text : "+text );
				FileIO.writeBytes( text.getBytes() , file );
			}
		}
	}
	
}