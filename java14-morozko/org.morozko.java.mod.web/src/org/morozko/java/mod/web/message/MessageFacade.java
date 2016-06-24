package org.morozko.java.mod.web.message;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MessageFacade {

	public static final String DEFAULT_MESSAGE_KEY = "userMessages";
	
	public static void queueUserMessage( HttpServletRequest request, String key ) {
		queueUserMessage( request, new ActionMessage( key ) );
	}
	
	public static void queueUserMessage( HttpServletRequest request, ActionMessage message ) {
		queueUserMessage( DEFAULT_MESSAGE_KEY , request, message );
	}
	
	public static void queueUserMessage( String messageKey, HttpServletRequest request, ActionMessage message ) {
		ActionMessages actionMessages = (ActionMessages)request.getAttribute( messageKey );
		if ( actionMessages == null ) {
			actionMessages = new ActionMessages();
			request.setAttribute( messageKey, actionMessages );
		}
		actionMessages.add( messageKey , message );
	}	
	
}
