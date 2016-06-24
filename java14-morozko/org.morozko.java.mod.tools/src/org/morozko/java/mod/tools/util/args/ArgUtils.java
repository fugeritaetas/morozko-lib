/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.tools 

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
 * @(#)ArgUtils.java
 *
 * @project     : org.morozko.java.core
 * @package     : org.morozko.java.mod.tools.util.args
 * @creation	: 28-dic-2004 9.37.10
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.tools.util.args;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.core.util.StringUtils;

/**
 * <p></p>
 * 
 * @author  Matteo Franci a.k.a. TUX2
 */
public class ArgUtils {
    
	public static final String ARG_DEFAULT_LOGGER_LOG_LEVEL = "log-level";
	
	public static final String ARG_DEFAULT_LOGGER_LOG_LEVEL_2 = "log-level";
	
	
	public static final String ARG_SYS_PROP_FILE = "sys-prop";
	
	public static void handleProxyParams( ArgList list ) {
		String host = list.findArgValue( "proxyHost" );
		if ( host != null ) {
			String port = list.findArgValue( "proxyPort" );
			String user = list.findArgValue( "proxyUser" );
			String pass = list.findArgValue( "proxyPassword" );
			System.getProperties().put( "http.proxyHost", host );
			System.getProperties().put( "http.proxyPort", port );
			System.getProperties().put( "http.proxyUser", user );
			System.getProperties().put( "http.proxyPassword", pass );	
		}
	}
	
	public static void systemPropertyFile( ArgList list ) throws IOException {
		String sysProp = list.findArgValue( ARG_SYS_PROP_FILE );
		if ( sysProp != null ) {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream( sysProp );
			props.load( fis );
			fis.close();
			Enumeration keys = props.keys();
			while ( keys.hasMoreElements() ) {
				Object k = keys.nextElement();
				Object v = props.get( k );
				System.setProperty( k.toString(), v.toString() );
			}
		}
	}
	
	public static ArgList parseArgsDefault( String[] args ) {
		ArgList list = parseArgs( args );
		Arg loggerLogLevelArg = list.findArg( ARG_DEFAULT_LOGGER_LOG_LEVEL );
		if ( loggerLogLevelArg != null ) {
			LogFacade.updateCurrentConfig( "log.level" , loggerLogLevelArg.getValue() );
		}
		return list;
	}
	
	public static ArgList parseArgsProps( String[] args ) throws IOException {
		ArgList argList = parseArgsDefault( args );
		Arg propFile = argList.findArg( "launchprops" );
		if ( propFile != null ) {
			populateArgList( argList , new File( propFile.getValue() ) );
		}
		return argList;
	}
	
	public static void populateArgList( ArgList argList, File propFile ) throws IOException {
		if ( propFile != null ) {
			Properties config = PropsIO.loadFromFile( propFile );
			Enumeration e = config.keys();
			while ( e.hasMoreElements() ) {
				
				String key = (String)e.nextElement();
				if ( argList.findArg( key ) == null ) {
					argList.add( new Arg( key, config.getProperty( key ) ) );
				}
			}				
		}
	}
	
    public static ArgList parseArgs(String[] args) {
        ArgList list = new ArgList();
        Vector argValues = new Vector();
        String argName = null;
        int k = 0;
        if (args!=null && args.length>0) {
            while (k<args.length) {
                String currentArg = args[k];
                if (currentArg.indexOf("-")==0) {
                    if (argName != null) {
                        list.add(new Arg(argName, StringUtils.toArray(argValues)));
                        argValues.clear();
                    }
                    argName = currentArg.substring(1);
                } else {
                    argValues.add(currentArg);
                }
                k++;
            }
            list.add(new Arg(argName, StringUtils.toArray(argValues)));
        }
        return list;
    }
    
    public static String[] parseCommaSeparated(String value) {
        return parse(value, ",");
    }
    
    public static String[] parse(String value, String separator) {
        Vector v = new Vector();
        int index = value.indexOf(separator);
        while (index != -1) {
            v.add(value.substring(0, index).trim());
            value = value.substring(index+separator.length());
        }
        v.add(value);
        return StringUtils.toArray(v);
    }

    private ArgUtils() {
        super();
    }

}
