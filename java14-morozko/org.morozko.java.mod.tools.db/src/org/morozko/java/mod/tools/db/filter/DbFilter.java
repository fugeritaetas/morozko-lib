/*******************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools.db 

	Copyright (c) 2006 Morozko

	All rights reserved. This program and the accompanying materials
	are made available under the terms of the Apache License v2.0
	which accompanies this distribution, and is available at
	http://www.apache.org/licenses/
	(txt version : http://www.apache.org/licenses/LICENSE-2.0.txt
	html version : http://www.apache.org/licenses/LICENSE-2.0.html)

   This product includes software developed at
   The Apache Software Foundation (http://www.apache.org/).
</copyright>
*******************************************************/
/*
 * @(#)DbFilter.java
 *
 * @project    : org.morozko.java.mod.tools.db
 * @package    : org.morozko.java.mod.tools.db.filter
 * @creation   : 15/dic/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.tools.db.filter;

import java.io.File;
import java.io.FileInputStream;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.db.filter.DataFilterConfig;
import org.morozko.java.mod.tools.ToolUtils;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;
import org.xml.sax.InputSource;

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
public class DbFilter {

    public static final String VERSION = "0.1.1 (2006-12-15)";
    
    private static void printInfo() {
        ToolUtils.printInfo("DbFilter v. "+VERSION, "Matteo Franci a.k.a TUX2");
    }
    
    
    public static void main(String[] arg) {
    	printInfo();
        try {
            
            ArgList list = ArgUtils.parseArgs(arg);
            String config = list.findArgValue( "f" );
            Arg l = list.findArg( "l" );
            if ( l != null ) {
            	LogFacade.updateCurrentConfig( "log.level" , l.getValue() );
            }
            File f = new File( config );
            
            LogFacade.getLog().debug( "Config file : "+f );
            
            DataFilterConfig dataFilterConfig = DataFilterConfig.parse( new InputSource( new FileInputStream( f ) )  );
            
            dataFilterConfig.filter();
            
        } catch (Exception e) {
            System.err.println(" options : ");
            System.err.println("      -f  [config-file] ");
            System.err.println("      -l  [log-level:0-6] ");
            System.err.println();
            e.printStackTrace();
        }
        printInfo();
    }   
	
}
