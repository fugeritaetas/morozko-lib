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
 * @(#)EmailSender.java
 *
 * @project    : org.morozko.java.mod.message
 * @package    : org.morozko.java.mod.message.email
 * @creation   : 5-giu-2006
 */
package org.morozko.java.mod.message.email;

import java.util.Iterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.message.MessageAddress;
import org.morozko.java.mod.message.MessageException;
import org.morozko.java.mod.message.MessageFilePart;
import org.morozko.java.mod.message.MessageHandler;
import org.morozko.java.mod.message.MessagePart;
import org.morozko.java.mod.message.MessageSender;
import org.morozko.java.mod.message.MessageTextPart;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class EmailSender2 extends BasicLogObject implements MessageSender {

	private Properties config;

	/**
	 * @return Restituisce il valore di config.
	 */
	public Properties getConfig() {
		return config;
	}

	private EmailSender2( Properties config ) {
		this.config = config;
		this.getLog().info( "CONFIG : "+this.getConfig() );
	}
	
	public static EmailSender2 newEmailSender( Properties config ) {
		EmailSender2 sender = new EmailSender2( config );
		return sender;
	}
	
	public InternetAddress[] convert( MessageAddress[] list ) throws AddressException {
		InternetAddress[] result = new InternetAddress[ list.length ];
		for ( int k=0; k<result.length; k++) {
			result[k] = new InternetAddress( list[k].getAddress() );
			this.getLog().debug( "convert : '"+result[k]+"'" );
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.message.MessageSender#send(org.morozko.java.mod.message.MessageHandler)
	 */
	public int send( MessageHandler message ) throws MessageException {
		int result = SEND_RESULT_OK;
		
		try {
			
			Authenticator auth = null;
			String user = this.config.getProperty( "mail.user" );
			String pass = this.config.getProperty( "mail.password" );
			
			if ( user != null && pass != null ) {
				LogFacade.getLog().info( "user : "+user );
				LogFacade.getLog().info( "user : "+pass );
				auth = new PassAuth(user, pass);
				LogFacade.getLog().info( "auth : "+auth );
			}

			Session session = Session.getDefaultInstance( this.getConfig() , auth );
			session.setDebug(false);
			
			Message msg = new MimeMessage(session);
			
			String from = message.getSender().getAddress();

			Address fromAddress = new InternetAddress(from);
			msg.setFrom(fromAddress);
			
			if (  message.getReplyTo() != null ) {
				Address[] rt = { new InternetAddress( message.getReplyTo().getAddress() ) };
				msg.setReplyTo( rt );
			}
			
			if ( message.getToList().length > 0 ) {
				InternetAddress[] toList = convert( message.getToList() );
				msg.setRecipients( RecipientType.TO, toList );
			}
			
			if ( message.getCcList().length > 0 ) {
				InternetAddress[] ccList = convert( message.getCcList() );
				msg.setRecipients( RecipientType.CC, ccList );
			}
			
			if ( message.getBccList().length > 0 ) {
				InternetAddress[] bccList = convert( message.getBccList() );
				msg.setRecipients( RecipientType.BCC, bccList );
			}
			
			msg.setSubject( message.getSubject());
			
			Multipart mp = new MimeMultipart();
			
			Iterator it = message.getMessageParts();
			while ( it.hasNext() ) {
				MessagePart part = (MessagePart)it.next();
				System.out.println( "ADD PART "+part+" -> "+part.getType() );
				if ( part.getType() == MessagePart.TYPE_TEXT ) {
					MessageTextPart messageTextPart = (MessageTextPart)part;
					this.getLog().debug( "send text part : "+messageTextPart.getContent() );
					MimeBodyPart textPart = new MimeBodyPart();
					textPart.setContent( messageTextPart.getContent(), "text/html" );
					mp.addBodyPart( textPart );
				} else if ( part.getType() == MessagePart.TYPE_FILE ) {
					MessageFilePart messageFilePart = (MessageFilePart)part;
					this.getLog().debug( "send file part : "+messageFilePart.getFile() );
					MimeBodyPart attachFilePart = new MimeBodyPart();
					FileDataSource fds = new FileDataSource( messageFilePart.getFile() );
					attachFilePart.setDataHandler(new DataHandler( fds ) );
					attachFilePart.setFileName( fds.getName() );
					mp.addBodyPart( attachFilePart );
				}
			}
			
			msg.setContent(mp);
			
			System.out.println( "TRASPORT SEND START -> "+msg );
			Transport.send(msg);
			System.out.println( "TRASPORT SEND END" );
			
		} catch (Exception e) {
			throw ( new MessageException( e ) );
		}
		
		return result;
	}

}

class PassAuth extends Authenticator {
	
	private String user;
	
	private String pass;
	
	public PassAuth( String user, String pass ) {
		this.user = user;
		this.pass = pass;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication( this.user, this.pass );
	}
	
}