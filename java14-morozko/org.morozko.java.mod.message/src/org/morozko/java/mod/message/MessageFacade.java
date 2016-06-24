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
 * @(#)MessageFacade.java
 *
 * @project    : org.morozko.java.mod.message
 * @package    : org.morozko.java.mod.message
 * @creation   : 5-giu-2006
 */
package org.morozko.java.mod.message;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class MessageFacade {

	public static MessageHandler newMessage( String sender, String to, String cc, String bcc, String subject, String[] textParts, File[] fileParts, Properties headers ) {
		return newMessage(sender, null, to, cc, bcc, subject, textParts, fileParts, headers);
	}

	public static MessageHandler newMessage( String sender, String replyTo, String to, String cc, String bcc, String subject, String[] textParts, File[] fileParts, Properties headers ) {
		List parts = parts(textParts, fileParts);
		MessageDefault message = new MessageDefault( parts,
												new MessageAddress( sender ),
												MessageAddress.parse( to ),
												MessageAddress.parse( cc ),
												MessageAddress.parse( bcc ),
												subject,
												MessageHeaders.fromProperties( headers ));
		if ( replyTo != null ) {
			message.setReplyTo( new MessageAddress( replyTo ) );
		} else {
			message.setReplyTo( new MessageAddress( sender ) );
		}
		return message;
	}

	
	public static List parts( String[] textParts, File[] fileParts ) {
		List parts = new ArrayList();
		for ( int k=0; k<textParts.length; k++ ) {
			parts.add( newTextPart( textParts[k] ) );
		}
		if ( fileParts != null ) {
			for ( int k=0; k<fileParts.length; k++ ) {
				parts.add( newFilePart( fileParts[k] ) );
			}		
		}
		return parts;
	}
	
	public static MessageFilePart newFilePart( File file ) {
		return new MessageFilePartDefault( file );
	}
	
	public static MessageTextPart newTextPart( String content ) {
		return new MessageTextPartDefault( content );
	}
	
}

class MessageDefault implements MessageHandler {

	private List parts;

	private MessageAddress sender;
	
	private MessageAddress replyTo;
	
	public MessageAddress getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(MessageAddress replyTo) {
		this.replyTo = replyTo;
	}

	private MessageAddress[] toList;
	
	private MessageAddress[] ccList;
	
	private MessageAddress[] bccList;
	
	private String subject;
	
	private MessageHeaders headers;

	/**
	 * @return Restituisce il valore di bccList.
	 */
	public MessageAddress[] getBccList() {
		return bccList;
	}

	/**
	 * @return Restituisce il valore di ccList.
	 */
	public MessageAddress[] getCcList() {
		return ccList;
	}

	/**
	 * @return Restituisce il valore di headers.
	 */
	public MessageHeaders getHeaders() {
		return headers;
	}

	/**
	 * @return Restituisce il valore di sender.
	 */
	public MessageAddress getSender() {
		return sender;
	}

	/**
	 * @return Restituisce il valore di subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return Restituisce il valore di toList.
	 */
	public MessageAddress[] getToList() {
		return toList;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.message.MessageHandler#getMessageParts()
	 */
	public Iterator getMessageParts() {
		return this.parts.iterator();
	}

	/**
	 * @param parts
	 * @param sender
	 * @param toList
	 * @param ccList
	 * @param bccList
	 * @param subject
	 * @param headers
	 */
	public MessageDefault(List parts, MessageAddress sender, MessageAddress[] toList, MessageAddress[] ccList, MessageAddress[] bccList, String subject, MessageHeaders headers) {
		super();
		this.parts = parts;
		this.sender = sender;
		this.toList = toList;
		this.ccList = ccList;
		this.bccList = bccList;
		this.subject = subject;
		this.headers = headers;
	}
	
}

class MessageTextPartDefault implements MessageTextPart {

	public MessageTextPartDefault( String content ) {
		this.content = content;
	}
	
	private String content;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.message.MessageTextPart#getContent()
	 */
	public String getContent() {
		return this.content;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.message.MessagePart#getType()
	 */
	public int getType() {
		return TYPE_TEXT;
	}
	
}

class MessageFilePartDefault implements MessageFilePart {

	public MessageFilePartDefault( File file ) {
		this.file = file;
	}
	
	private File file;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.message.MessagePart#getType()
	 */
	public int getType() {
		return TYPE_FILE;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.message.MessageFilePart#getFile()
	 */
	public File getFile() {
		return this.file;
	}
	
}