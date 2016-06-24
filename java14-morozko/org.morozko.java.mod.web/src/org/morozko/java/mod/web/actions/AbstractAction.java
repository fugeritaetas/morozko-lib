/*****************************************************************
<copyright>
	Morozko Java Library org.opinf.jlib.mod.app.web 

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
 * @(#)AbstractAction.java
 *
 * @project    : jlibWeb
 * @package    : org.morozko.java.mod.web.actions
 * @creation   : 5-mag-2006
 */
package org.morozko.java.mod.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.log.LogObject;
import org.morozko.java.core.log.Logger;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public abstract class AbstractAction extends Action implements LogObject {
	
	private Logger logger;
	
	public AbstractAction() {
		this.logger = LogFacade.createLogger( this );
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.core.log.LogObject#getLog()
	 */
	public Logger getLog() {
		return this.logger;
	}	
	
	public static final String STANDARD_FORWARD_SUCCESS = "Success";
	public static final String STANDARD_FORWARD_FAILURE = "Failure";
	
	public abstract ActionForward doExecute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public static boolean setSessionParameter( HttpServletRequest request, String name, Object value ) {
		boolean result = value != null;
		LogFacade.getLog().debug( "AbstractAction.setSessionParameter() : "+result );
		if ( result ) {
			request.getSession().setAttribute( name, value );
		} else {
			request.getSession().removeAttribute( name );
		}
		return result;
	}	
	
	public static boolean setRequestParameter( HttpServletRequest request, String name, Object value ) {
		boolean result = value != null;
		LogFacade.getLog().debug( "AbstractAction.setRequestParameter() : "+result );
		if ( result ) {
			request.setAttribute( name, value );
		} else {
			request.removeAttribute( name );
		}
		return result;
	}
	
	public static String findParameter( HttpServletRequest request, String name ) {
		String result = request.getParameter( name );
		if ( result == null ) {
			result = (String)request.getAttribute( name );
		}
		LogFacade.getLog().info( "Abstract.findParameter name='"+name+"', value='"+result+"'" );
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		ActionForward forward = null;
		ActionProcessor ap = ActionProcessor.getProcessor( this.getServlet().getServletContext() );
		ap.before(mapping, form, request, response, this);
		try {
			forward = this.doExecute( mapping, form , request, response );
		} catch (Throwable t) {
			forward = ap.error(mapping, form, request, response, t, this);
		} finally {
			ap.after(mapping, form, request, response, this);
		}
		return forward;
	}

}
