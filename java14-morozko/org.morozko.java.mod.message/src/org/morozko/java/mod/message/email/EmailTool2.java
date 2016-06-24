package org.morozko.java.mod.message.email;

import java.io.File;
import java.io.PrintStream;
import java.util.Properties;

import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.util.PropsIO;
import org.morozko.java.mod.message.MessageFacade;
import org.morozko.java.mod.message.MessageHandler;
import org.morozko.java.mod.tools.util.args.Arg;
import org.morozko.java.mod.tools.util.args.ArgList;
import org.morozko.java.mod.tools.util.args.ArgUtils;


public class EmailTool2 {

	public final static String[] ACCEPT_ARGS = {
			"p", // 0
			"f", // 1
		    "t", // 2
			"c", // 3
			"b", // 4
			"s", // 5
			"m", // 6
			"a", // 7
			"d", }; // 8

	public static final String ARG_SESSION_PROPS = ACCEPT_ARGS[0];
	public static final String ARG_FROM_ADDRESS = ACCEPT_ARGS[1];
	public static final String ARG_TO_LIST = ACCEPT_ARGS[2];
	public static final String ARG_CC_LIST = ACCEPT_ARGS[3];
	public static final String ARG_BCC_LIST = ACCEPT_ARGS[4];
	public static final String ARG_SUBJECT = ACCEPT_ARGS[5];
	public static final String ARG_BODY = ACCEPT_ARGS[6];
	public static final String ARG_ATTACH = ACCEPT_ARGS[7];
	public static final String ARG_CONTENT_TYPE = ACCEPT_ARGS[8];

	private static void printHelp(PrintStream out) {
		out.println(" options : ");
		out.println("      -" + ACCEPT_ARGS[0] + " [mail-session-config] ");
		out.println("      -" + ACCEPT_ARGS[1] + " [from-address] ");
		out.println("      -" + ACCEPT_ARGS[2] + " [to-address;to-address] ");
		out.println("      -" + ACCEPT_ARGS[3] + " [cc-address;cc-address] ");
		out.println("      -" + ACCEPT_ARGS[4] + " [bcc-address;bcc-address] ");
		out.println("      -" + ACCEPT_ARGS[5] + " [subject] ");
		out.println("      -" + ACCEPT_ARGS[6] + " [body] ");
		out.println("      -" + ACCEPT_ARGS[7] + " [attach] ");
		out.println("      -" + ACCEPT_ARGS[8] + " [content-type] ");
		out.println();
	}

	public static void main(String[] args) {
		try {
			ArgList argList = ArgUtils.parseArgs(args);
			// arguments
			String mailConfig = argList.findArgValue( ARG_SESSION_PROPS );
			String from = argList.findArgValue( ARG_FROM_ADDRESS );
			String to = argList.findArgValue( ARG_TO_LIST );
			String cc = argList.findArgValue( ARG_CC_LIST );
			String bcc = argList.findArgValue( ARG_BCC_LIST );
			String subject = argList.findArgValue( ARG_SUBJECT );
			String body = argList.findArgValue( ARG_BODY );
			String attach = argList.findArgValue( ARG_ATTACH );
			String type = argList.findArgValue( ARG_CONTENT_TYPE );
			// logging
			LogFacade.getLog().debug( "EmailTool2.param config   : '"+mailConfig+"'" );
			LogFacade.getLog().debug( "EmailTool2.param from     : '"+from+"'" );
			LogFacade.getLog().debug( "EmailTool2.param to       : '"+to+"'" );
			LogFacade.getLog().debug( "EmailTool2.param cc       : '"+cc+"'" );
			LogFacade.getLog().debug( "EmailTool2.param bcc      : '"+bcc+"'" );
			LogFacade.getLog().debug( "EmailTool2.param subject  : '"+subject+"'" );
			LogFacade.getLog().debug( "EmailTool2.param body     : '"+body+"'" );
			LogFacade.getLog().debug( "EmailTool2.param attach   : '"+attach+"'" );
			LogFacade.getLog().debug( "EmailTool2.param type     : '"+type+"'" );
			// sender configuration
			Properties props = PropsIO.loadFromFile( mailConfig );
			EmailSender2 mailer = EmailSender2.newEmailSender( props );
			String textParts[] = { body };
			File fileParts[] = null;
			if ( attach != null ) {
				File a[] = { new File (attach) };
				fileParts = a;
			}
			Properties headers = new Properties();
			if ( type != null ) {
				headers.setProperty( "Content-Type", type );
			}
			MessageHandler message = MessageFacade.newMessage( from, to, cc, bcc, subject, textParts, fileParts, headers);
			mailer.send( message );
		} catch (Exception e) {
			printHelp(System.err);
			e.printStackTrace();
		}
	}
}
