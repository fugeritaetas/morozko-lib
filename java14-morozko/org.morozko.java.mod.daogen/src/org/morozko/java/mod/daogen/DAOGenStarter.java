/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.daogen 

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
 * @(#)DAOGenStarter.java
 *
 * @project    : org.morozko.java.mod.daogen
 * @package    : org.morozko.java.mod.daogen
 * @creation   : 13-apr-2006
 */
package org.morozko.java.mod.daogen;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.morozko.java.mod.daogen.gen.coder.Generate;
import org.morozko.java.mod.daogen.gen.config.ConfigParser;
import org.morozko.java.mod.daogen.gen.config.DGConfig;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class DAOGenStarter {

	public static final boolean REGENERATE = false;
	
	public static final String VERSION = "DAOGEN2 v.1.9.32 [2014-05-28]";
	
	private static void log( Object message ) {
		System.out.println( message );
	}

	private static void printErrors( List list, String label ) {
		if ( list.isEmpty() ) {
			log( "Non sono stati generati "+label+" di configurazione." );
		} else {
			log( "Elenco "+label+" di configurazione." );
			Iterator it = list.iterator();
			while ( it.hasNext() ) {
				log( it.next() );
			}			
		}
	}

	public static void main( String[] args ) {
		try {
			
			Locale.setDefault( Locale.ENGLISH );
			
			log( VERSION+" START" );
			
			ArgList list = ArgUtils.parseArgsDefault( args );
			
			String confPath = list.findArgValue( "c" );
			ConfigParser cp = new ConfigParser();
			DGConfig dgConfig = cp.parseConfig( confPath );
			printErrors( dgConfig.getErrorList(), "errori" );
			printErrors( dgConfig.getWarningList(), "avvertimenti" );
			if ( dgConfig.getErrorList().isEmpty() && dgConfig.getWarningList().isEmpty() ) {
				Generate.gen( dgConfig );	
			} else {
				log( "NON PROCEDO CON LA GENERAZIONE!" );
			}
			
			log( VERSION+" END" );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
