package org.morozko.java.mod.web.actions;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.core.log.LogFacade;

public class ActionProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -773486825174878517L;

	private ServletContext context;
	
	public static final String ATT_NAME = "org.morozko.java.mod.web.actions.ActionProcessor.ATT_NAME";
	
	public static final ActionProcessor DEFAULT_PROCESSOR = new ActionProcessor();
	
	public static ActionProcessor getProcessor( ServletContext context )  {
		ActionProcessor ap = (ActionProcessor)context.getAttribute( ATT_NAME );
		if ( ap == null ) {
			ap = DEFAULT_PROCESSOR;
		}
		return ap;
	}
	
	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public static void setProcessor( ServletContext context, ActionProcessor ap) {
		context.setAttribute( ATT_NAME , ap );
		ap.setContext( context );
	}

	
	public void before( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object caller ) {
		LogFacade.getLog().info( "ActionProcessor."+caller.getClass().getName()+" start" );
	}
	
	public void after( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object caller ) {
		LogFacade.getLog().info( "ActionProcessor."+caller.getClass().getName()+" end" );
	}	
	
	public ActionForward error( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Throwable t, Object caller ) throws Exception {
		int sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		LogFacade.getLog().error( "ActionProcessor."+caller.getClass().getName()+" sc: "+sc+", error : "+t, t );
		response.sendError( sc );
		return null;
	}
	
	
}
