package org.morozko.java.mod.tools.dbex;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.tools.db.ConnArgs;
import org.morozko.java.mod.tools.dbex.qo.QueryOutputFun;
import org.morozko.java.mod.tools.dbex.qo.QueryOutputFunCSV;
import org.morozko.java.mod.tools.dbex.qo.QueryOutputFunXLS;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class QueryOutput {
	
	public static final String VERSION = "QueryOutput v 1.0.0 (2015-12-23) ";
	
	public static final String OUTPUT_MODE_CSV = "csv";
	public static final String OUTPUT_MODE_XLS = "xls";
	
	public static final String OUTPUT_MODE_PARAM = "m";
	
	private static final HashMap MODES = new HashMap();
	static {
		MODES.put( OUTPUT_MODE_CSV , QueryOutputFunCSV.class.getName() );
		MODES.put( OUTPUT_MODE_XLS , QueryOutputFunXLS.class.getName() );
	}
	
	public static void doOutput( ArgList list ) throws Exception {
			ConnectionFactory cf = ConnArgs.createConnectionFactory( list );
			String file = list.findArgValue( "f" );
			String sql = list.findArgValue( "q" );
			String qf = list.findArgValue( "qf" );
			String ff = list.findArgValue( "ff", String.valueOf( QueryOutputFun.DEFAULT_FLUSH_FRAME ) );
			if ( qf != null ) {
				System.out.println( "Reading query file : "+qf );
				sql = FileIO.readString( qf );
			}
			String outputMode = list.findArgValue( "m", OUTPUT_MODE_CSV );
			String modeType = (String)MODES.get( outputMode );
			if ( sql == null ) {
				sql = FileIO.readString( list.findArgValue( "qf" ) );
			}
			System.out.println( "SQL > "+sql );
			QueryOutputFun fun = (QueryOutputFun) ClassHelper.newInstance( modeType );
			FileOutputStream fos = new FileOutputStream( new File( file ) );
			fun.output( cf.getConnection() , sql, fos, Integer.parseInt( ff ) );
			fos.close();
	}
	
	public static void main( String[] args ) {
		try {
			System.out.println( VERSION+" START" );
			ArgList list = ArgUtils.parseArgsProps( args );
			QueryOutput.doOutput( list );
			System.out.println( VERSION+" END" );
		} catch (Exception e) {
			System.out.println( VERSION+" ERROR" );
			e.printStackTrace();
		}
	} 
	
}
