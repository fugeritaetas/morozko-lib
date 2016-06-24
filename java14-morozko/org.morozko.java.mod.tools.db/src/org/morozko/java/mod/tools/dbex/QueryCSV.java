package org.morozko.java.mod.tools.dbex;

import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

public class QueryCSV {

	public static void main( String[] args ) {
		try {
			ArgList list = ArgUtils.parseArgsProps( args );
			Arg mode = list.findArg( QueryOutput.OUTPUT_MODE_PARAM );
			if ( mode == null ) {
				list.add( new Arg( QueryOutput.OUTPUT_MODE_PARAM , QueryOutput.OUTPUT_MODE_CSV ) );
			}
			QueryOutput.doOutput( list );
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
}
