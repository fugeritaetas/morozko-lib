/*****************************************************************
<copyright>
	Morozko Java Library org.morozko.java.core.ent 

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
 * @(#)RequestFacade.java
 *
 * @project    : org.morozko.java.core.ent
 * @package    : org.morozko.java.core.ent.servlet.request
 * @creation   : 01/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.core.ent.servlet.request;

import javax.servlet.http.HttpServletRequest;

import org.morozko.java.core.log.LogFacade;

/**
 * <p></p>
 *
 * @author Morozko
 *
 */
public class RequestFacade {

	public static String getRelativeUrl( HttpServletRequest request, String relUrl)  {
		return getBaseUrl( request )+relUrl;
	}
	
	public static String getRelativeUrl( HttpServletRequest request, String relUrl, ParamMap params )  {
		return getBaseUrl( request )+relUrl+params.getQueryString();
	}
	
	public static String getBaseUrl( HttpServletRequest request ) {
		StringBuffer url = new StringBuffer();
		url.append( request.getScheme()+"://" );
		url.append( request.getServerName()+":" );
		url.append( request.getServerPort() );
		url.append( request.getContextPath() );
		return url.toString();
	}
	
	public static String findParameter( HttpServletRequest request, String name ) {
		String param = request.getParameter( name );
		if ( param == null ) {
			param = (String)request.getAttribute( name );
		}
		return param;
	}
	
	public static String contextRealativeURI( HttpServletRequest request ) {
		String url = request.getRequestURI();
		LogFacade.getLog().debug( "RequestFacade.contextRealativeURI() uri pre  : '"+url+"'" );
		url = url.substring( request.getContextPath().length() );
		LogFacade.getLog().debug( "RequestFacade.contextRealativeURI() uri post : '"+url+"'" );
		return url;
	}
	
}
