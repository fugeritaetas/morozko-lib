/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.message 

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
 * @(#)EmailTool.java
 *
 * @project    : org.morozko.java.mod.message
 * @package    : org.morozko.java.mod.message.email
 * @creation   : 5-giu-2006
 */
package org.morozko.java.mod.message.email;

import java.io.PrintStream;

import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class EmailTool {

	public final static String[] ACCEPT_ARGS = { 	"h",		// 0
													"u",		// 1
													"p",		// 2
													"f", 		// 3
													"t",		// 4
													"c",		// 5
													"b",		// 6
													"s",		// 7
													"m",		// 8
													"a",		// 9
													"d", };		// 10

	public static final String ARG_SMTP_HOST = ACCEPT_ARGS[0];
	public static final String ARG_SMTP_USER = ACCEPT_ARGS[1];
	public static final String ARG_SMTP_PASS = ACCEPT_ARGS[2];
	public static final String ARG_FROM_ADDRESS = ACCEPT_ARGS[3];
	public static final String ARG_TO_LIST = ACCEPT_ARGS[4];
	public static final String ARG_CC_LIST = ACCEPT_ARGS[5];
	public static final String ARG_BCC_LIST = ACCEPT_ARGS[6];
	public static final String ARG_SUBJECT = ACCEPT_ARGS[7];
	public static final String ARG_BODY = ACCEPT_ARGS[8];
	public static final String ARG_ATTACH = ACCEPT_ARGS[9];
	public static final String ARG_CONTENT_TYPE = ACCEPT_ARGS[10];
	
	private static void printHelp( PrintStream out ) {
        out.println(" options : ");
        out.println("      -"+ACCEPT_ARGS[0]+" [smtp-host] ");
        out.println("      -"+ACCEPT_ARGS[1]+" [smtp-user] ");
        out.println("      -"+ACCEPT_ARGS[2]+" [smtp-pass] ");
        out.println("      -"+ACCEPT_ARGS[3]+" [from-address] ");
        out.println("      -"+ACCEPT_ARGS[4]+" [to-address;to-address] ");
        out.println("      -"+ACCEPT_ARGS[5]+" [cc-address;cc-address] ");
        out.println("      -"+ACCEPT_ARGS[6]+" [bcc-address;bcc-address] ");
        out.println("      -"+ACCEPT_ARGS[7]+" [subject] ");
        out.println("      -"+ACCEPT_ARGS[8]+" [body] ");
        out.println("      -"+ACCEPT_ARGS[9]+" [attach] ");
        out.println("      -"+ACCEPT_ARGS[10]+" [content-type] ");
        out.println();			
	}
	
	public static void main( String[] args ) {
		try {
			ArgList argList = ArgUtils.parseArgs( args );
			Arg help = argList.findArg( "help" );
			if ( help == null ) {
				String smtp = argList.findArgValue( ARG_SMTP_HOST );
				String user = argList.findArgValue( ARG_SMTP_USER );
				String pass = argList.findArgValue( ARG_SMTP_PASS );
				String from = argList.findArgValue( ARG_FROM_ADDRESS );
				String to = argList.findArgValue( ARG_TO_LIST );
				String cc = argList.findArgValue( ARG_CC_LIST );
				String bcc = argList.findArgValue( ARG_BCC_LIST );
				String subject = argList.findArgValue( ARG_SUBJECT );
				String body = argList.findArgValue( ARG_BODY );
				String attach = argList.findArgValue( ARG_ATTACH );
				String type = argList.findArgValue( ARG_CONTENT_TYPE );
				EmailFacade.send( smtp, user, pass, from, to, cc, bcc, subject, body, type, attach );				
			} else {
				printHelp( System.out );
			}
		} catch (Exception e) {
			printHelp( System.err );
			e.printStackTrace();
		}
	}
	
}
