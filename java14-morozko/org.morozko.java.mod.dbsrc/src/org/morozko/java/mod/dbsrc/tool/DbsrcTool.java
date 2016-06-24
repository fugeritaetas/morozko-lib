package org.morozko.java.mod.dbsrc.tool;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Properties;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.text.regex.ParamFinder;
import org.morozko.java.mod.dbsrc.config.ConfigurationData;
import org.morozko.java.mod.dbsrc.config.Dbsrc;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class DbsrcTool {

	public static final String VERSION = "0.1 (2009-05-20)";
	
	private static void printHelp() {
		System.out.println( "DbsrcTool help "+VERSION );
		System.out.println();
		System.out.println( "usage : DbsrcTool [options] " );
		System.out.println( "	-c config-file" );
		System.out.println( "	-o operation-name" );
		System.out.println( "	-v key=value[;key=value]" );
		System.out.println();
	}
	
	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
		try {
			
			ArgList list = ArgUtils.parseArgsDefault( args );

			Arg h = list.findArg( "h" );
			
			if ( h == null ) {
				long starTime = System.currentTimeMillis();
				String c = list.findArgValue( "c" );
				String o = list.findArgValue( "o" );
				String v = list.findArgValue( "v" );
				
				String config = FileIO.readString( new File( c ) );
				
				if ( v != null ) {
					ParamFinder finder = ParamFinder.newFinder();
					Properties props = new Properties();
					String[] vars = v.split( ";" );
					for ( int k=0; k<vars.length; k++ ) {
						String[] curr = vars[k].split( "=" );
						props.setProperty( curr[0] , curr[1] );
					}
					config = finder.substitute( config , props );
				}
				
				//System.out.println( "config >> "+config );
				
				StringReader sr = new StringReader( config );
				Dbsrc dbsrc = ConfigurationData.parseConfiguration( sr );
				sr.close();
				dbsrc.exec( o );
				long totalTime = (System.currentTimeMillis()-starTime);
				System.out.println( "total exec time : "+(totalTime/1000)+"."+(totalTime%1000) );
			} else {
				printHelp();
			}
			
		} catch (Throwable e) {
			e.printStackTrace( System.out );
			printHelp();
		}
	}
	
}
