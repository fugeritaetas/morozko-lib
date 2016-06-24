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
 * @(#)EmailFacade.java
 *
 * @project    : org.morozko.java.mod.message
 * @package    : org.morozko.java.mod.message.email
 * @creation   : 5-giu-2006
 */
package org.morozko.java.mod.message.email;

import java.io.File;
import java.util.Properties;

import org.morozko.java.mod.message.MessageFacade;
import org.morozko.java.mod.message.MessageHandler;
import org.morozko.java.mod.message.MessageSender;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class EmailFacade {
	
	public static int send( String smtp, 
			String from, 
			String to, 
			String subject, 
			String body ) {
		return send( smtp, null, null, from, to, null, null, subject, body );
	}		
	
	public static int send( String smtp, 
			String from, 
			String to, 
			String cc, 
			String bcc,
			String subject, 
			String body ) {
		return send( smtp, null, null, from, to, cc, bcc, subject, body );
	}	
	
	public static int send( String smtp, 
			String user, 
			String pass,
			String from, 
			String to, 
			String cc, 
			String bcc,
			String subject, 
			String body ) {
		return send( smtp, user, pass, from, to, cc, bcc, subject, body, (String)null, (String)null );
	}
	
	public static int send( String smtp, 
			String user, 
			String pass,
			String from, 
			String to, 
			String cc, 
			String bcc,
			String subject, 
			String body,
			String contentType,
			String filePath ) {
			String[] file = null;
			if ( filePath != null ) {
				file = filePath.split( ";" );
			}
		return send( smtp, user, pass, from, to, cc, bcc, subject, body, null, file );
	}
		
	public static int send( String smtp, 
							String user, 
							String pass,
							String from, 
							String to, 
							String cc, 
							String bcc,
							String subject, 
							String body,
							String contentType, 
							String filePath[] ) {
		int result = MessageSender.SEND_RESULT_KO;
		try {
			EmailSender emailSender = EmailSender.newEmailSender( smtp, user, pass );
			String[] content = { body };
			File[] file = new File[0];
			if ( filePath != null ) {
				File[] list = new File[ filePath.length ];
				for ( int k=0; k<list.length; k++ ) {
					list[k] = new File( filePath[k] );
				}
				file = list;
			}
			Properties headers = new Properties();
			if ( contentType != null ) {
				headers.setProperty( "Content-Type", contentType );
			}
			MessageHandler message = MessageFacade.newMessage( from, to, cc, bcc, subject, content, file, headers );
			result = emailSender.send( message );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
