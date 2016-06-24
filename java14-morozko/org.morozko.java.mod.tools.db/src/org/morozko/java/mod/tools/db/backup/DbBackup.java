/*****************************************************************
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
*****************************************************************/
/*
 * @(#)DbBackup.java
 *
 * @project    : org.morozko.java.mod.tools.db
 * @package    : org.morozko.java.mod.tools.db.backup
 * @creation   : 05/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.tools.db.backup;

import java.io.File;

import org.morozko.java.mod.db.backup.BackupFacade;
import org.morozko.java.mod.db.connect.CfConfig;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.tools.ToolUtils;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMIO;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DbBackup {

    public static final String VERSION = "0.1.4 (2014-06-25)";
    
    private static void printInfo() {
        ToolUtils.printInfo("DbBackup v. "+VERSION, "Matteo Franci a.k.a TUX2");
    }
    
    
    public static void main(String[] arg) {
    	printInfo();
        try {
            
            ArgList list = ArgUtils.parseArgs(arg);
            String config = list.findArgValue( "f" );
            String cf = list.findArgValue( "cf-config" );
            Arg l = list.findArg( "l" );
            if ( l != null ) {
            	LogFacade.updateCurrentConfig( "log.level" , l.getValue() );
            }
            File f = new File( config );
            LogFacade.getLog().debug( "Config file : "+f );
            
            CfConfig cfConfig = CfConfig.EMPTY_CONFIG;
            if ( cf != null ) {
            	File cfile = new File( cf );
            	cfConfig = ConnectionFactoryImpl.parseCfConfig( DOMIO.loadDOMDoc( cfile ).getDocumentElement() );
            }
            
            BackupFacade.backup( DOMIO.loadDOMDoc( f ).getDocumentElement(), cfConfig );
            
        } catch (Exception e) {
            System.out.println(" options : ");
            System.out.println("      -f  [config-file] ");
            System.out.println("      -l  [log-level:0-6] ");
            System.out.println();
            e.printStackTrace( System.out );
        }
        printInfo();
    }    
	
}
