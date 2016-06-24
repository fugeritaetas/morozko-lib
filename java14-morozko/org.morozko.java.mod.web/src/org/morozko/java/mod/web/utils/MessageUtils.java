package org.morozko.java.mod.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MessageUtils {

	public static void queueOperationOk( HttpServletRequest request ) {
		queueUserMessage( request, new ActionMessage( "struts.message.generic.operation.ok" ) );
	}	
	
	public static void queueInsertOk( HttpServletRequest request ) {
		queueUserMessage( request, new ActionMessage( "struts.message.generic.insert.ok" ) );
	}	
	
	public static void queueInsertKo( HttpServletRequest request ) {
		queueUserMessage( request, new ActionMessage( "struts.message.generic.insert.ko" ) );
	}	
	
	public static void queueUpdateOk( HttpServletRequest request ) {
		queueUserMessage( request, new ActionMessage( "struts.message.generic.update.ok" ) );
	}	
	
	public static void queueUpdateKo( HttpServletRequest request ) {
		queueUserMessage( request, new ActionMessage( "struts.message.generic.update.ko" ) );
	}	
	
	public static void queueDeleteOk( HttpServletRequest request ) {
		queueUserMessage( request, new ActionMessage( "struts.message.generic.delete.ok" ) );
	}		

	public static void queueUserCustomMessage( HttpServletRequest request, String message ) {
	    Object[] args = { message };
		queueUserMessage( request, new ActionMessage( "struts.message.custom", args ) );
	}	
    
	public static void queueUserMessage( HttpServletRequest request, String key, Object arg ) {
		queueUserMessage( request, new ActionMessage( key, arg ) );
	}		
	
	public static void queueUserMessage( HttpServletRequest request, String key, Object[] args ) {
		queueUserMessage( request, new ActionMessage( key, args ) );
	}	
	
	public static void queueUserMessage( HttpServletRequest request, String key ) {
		queueUserMessage( request, new ActionMessage( key ) );
	}
	
	public static void queueUserMessage( HttpServletRequest request, ActionMessage message ) {
		ActionMessages actionMessages = (ActionMessages)request.getAttribute( "userMessages" );
		if ( actionMessages == null ) {
			actionMessages = new ActionMessages();
			request.setAttribute( "userMessages", actionMessages );
		}
		actionMessages.add( "userMessages" , message );
	}	
	
}
