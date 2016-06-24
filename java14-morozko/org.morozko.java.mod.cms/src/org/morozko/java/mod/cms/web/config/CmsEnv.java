/*****************************************************************
<copyright>
	OpenInformatica Java Library org.morozko.java.mod.app.cms 

	Copyright (c) 2006 OpenInformatica

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
 * @(#)CmsEnv.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.config
 * @creation   : 26/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsEnv {

	public CmsEnv() {
		this.requestCmsMap = new CmsMap();
		this.sessionCmsMap = new CmsMap();
	}
	
	public static final String ATT_NAME = "CmsEnv";
	
	public void saveInSession( HttpSession session ) {
		session.setAttribute( ATT_NAME, this );
	}	
	
	public void saveInSession( HttpServletRequest request ) {
		this.saveInSession( request.getSession() );
	}
	
	public void saveInRequest( HttpServletRequest request ) {
		request.setAttribute( ATT_NAME, this );
	}		
	
	public static CmsEnv loadFromSession( HttpSession session ) {
		CmsEnv cmsMap = (CmsEnv) session.getAttribute( ATT_NAME );
		if ( cmsMap == null ) {
			cmsMap = new CmsEnv();
			cmsMap.saveInSession( session );
		}
		return cmsMap;
	}
	
	public static CmsEnv loadFromSession( HttpServletRequest request ) {
		return (CmsEnv) loadFromSession( request.getSession() );
	}		
	
	public static CmsEnv loadFromRequest( HttpServletRequest request ) {
		CmsEnv cmsMap = (CmsEnv) request.getAttribute( ATT_NAME );
		if ( cmsMap == null ) {
			cmsMap = new CmsEnv();
			cmsMap.saveInRequest( request );
		}		
		return cmsMap;
	}		
	
	private CmsMap requestCmsMap;
	
	private CmsMap sessionCmsMap;

	/**
	 * @return the requestCmsMap
	 */
	public CmsMap getRequestCmsMap() {
		return requestCmsMap;
	}

	/**
	 * @param requestCmsMap the requestCmsMap to set
	 */
	public void setRequestCmsMap(CmsMap requestCmsMap) {
		this.requestCmsMap = requestCmsMap;
	}

	/**
	 * @return the sessionCmsMap
	 */
	public CmsMap getSessionCmsMap() {
		return sessionCmsMap;
	}

	/**
	 * @param sessionCmsMap the sessionCmsMap to set
	 */
	public void setSessionCmsMap(CmsMap sessionCmsMap) {
		this.sessionCmsMap = sessionCmsMap;
	}
	
}
