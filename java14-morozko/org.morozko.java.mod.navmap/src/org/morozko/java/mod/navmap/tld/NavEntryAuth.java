/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.mod.navmap 

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
package org.morozko.java.mod.navmap.tld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;
import org.morozko.java.mod.navmap.auth.AuthException;
import org.morozko.java.mod.navmap.auth.AuthHandler;
import org.morozko.java.mod.navmap.servlet.NavController;
import org.morozko.java.mod.navmap.tld.helpers.NavEntryTagSupportHelper;

public class NavEntryAuth extends NavEntryTagSupportHelper {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2989122880126384390L;

	/**
	 * Tag definito per la visualizzazione di un NavEntry 
	 */
	public int doStartTag() throws JspException {
		Object o = findObject( this.getAuthName() , this.getAuthProperty(), this.getAuth() );
		String a = null;
		if ( o != null ) {
			a = String.valueOf( o );
		}
		if ( a != null ) {
			AuthHandler authHandler = ((NavController)this.pageContext.getServletContext().getAttribute( NavController.ATT_NAME_NAVCONTROLLER )).getAuthHandler();
			try {
				TagUtilsHelper.setAttibute( this.pageContext, this.getToScope(), this.getId(), String.valueOf( authHandler.checkAuth( (HttpServletRequest)this.pageContext.getRequest() , a ) ) );
			} catch (AuthException e) {
				this.pageContext.setAttribute( this.getId(), String.valueOf( AuthHandler.STATE_UNAUTHORIZED ) );
				e.printStackTrace();
			}	
		} else {
			this.pageContext.setAttribute( this.getId(), String.valueOf( this.checkAuth() ) );	
		}
		return EVAL_PAGE;
	}
	
	private String authName;
	
	private String authProperty;

	private String auth;

	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthProperty() {
		return authProperty;
	}

	public void setAuthProperty(String authProperty) {
		this.authProperty = authProperty;
	}

}
