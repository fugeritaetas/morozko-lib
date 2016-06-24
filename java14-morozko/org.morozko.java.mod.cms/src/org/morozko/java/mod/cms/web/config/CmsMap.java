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
 * @(#)CmsSession.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.config
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.config;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsMap extends HashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1558040624790726646L;
	
	public static final String ATT_NAME = "CmsMap";
	
	public static CmsMap loadFromSession( HttpSession session ) {
		CmsMap cmsMap = (CmsMap) session.getAttribute( ATT_NAME );
		if ( cmsMap == null ) {
			cmsMap = new CmsMap();
			cmsMap.saveInSession( session );
		}
		return cmsMap;
	}
	
	public static CmsMap loadFromSession( HttpServletRequest request ) {
		return (CmsMap) loadFromSession( request.getSession() );
	}		
	
	public static CmsMap loadFromRequest( HttpServletRequest request ) {
		CmsMap cmsMap = (CmsMap) request.getAttribute( ATT_NAME );
		if ( cmsMap == null ) {
			cmsMap = new CmsMap();
			cmsMap.saveInRequest( request );
		}		
		return cmsMap;
	}	

	public void saveInSession( HttpSession session ) {
		session.setAttribute( ATT_NAME, this );
	}	
	
	public void saveInSession( HttpServletRequest request ) {
		this.saveInSession( request.getSession() );
	}
	
	public void saveInRequest( HttpServletRequest request ) {
		request.setAttribute( ATT_NAME, this );
	}	
	
}
