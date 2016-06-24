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
/*
 * @(#)NavController.java
 *
 * @project    : org.morozko.java.mod.navmap
 * @package    : org.morozko.java.mod.navmap.servlet
 * @creation   : 8-giu-2006
 */
package org.morozko.java.mod.navmap.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.NavMapFactory;
import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.navmap.NavSwitch;
import org.morozko.java.mod.navmap.auth.AuthException;
import org.morozko.java.mod.navmap.auth.AuthHandler;
import org.morozko.java.mod.navmap.facade.NavFacade;
import org.morozko.java.mod.web.servlet.config.ConfigContext;
import org.morozko.java.mod.web.servlet.config.SessionContext;
import org.xml.sax.InputSource;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class NavController extends BasicLogObject {

	public static final String ATT_NAME_NAVCONTROLLER = "currentNavController";
	
	public static final String ATT_NAME_NAVNODE = "currentNavNode";
	
	public static final String ATT_NAME_NAVMAP = "currentNavMap";
	
	public static final String ATT_NAME_NAVPATH = "currentNavPath";	
	
	public static final String ATT_NAV_PARAM_HANDLER = "NAV_PARAM_HANDLER";
	
	public static final String ATT_NAV_PARAM_HANDLER_MAP = "NAV_PARAM_HANDLER_MAP";
	
	private AuthHandler authHandler;
	
	private NavMap navMap;
	
	private ConfigContext context;
	
	public void handle( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws ServletException, IOException {
		int code = HttpServletResponse.SC_ACCEPTED;
		// add nav facade to request
		NavFacade.putInRequest( request, this );
		String uri = request.getRequestURI();
		//String url = request.getRequestURL().toString();
		this.getLog().debug( "uri     : "+uri );
		//this.getLog().debug( "url     : "+url );
		String navUrl = uri.substring( request.getContextPath().length()+1, uri.length()-3 );
		this.getLog().debug( "navUrl  : "+navUrl );
		this.getLog().debug( "navMap  : "+this.getNavMap() );
		// try nav switch start
		NavSwitch navSwitch = this.navMap.getNavSwitchByUrl( navUrl );
		if ( navSwitch != null ) {
			navUrl = navSwitch.getDefaultNavEntry().getUrl();
			this.getLog().info( "nav switch chainging nav url : "+navUrl );
		}
		// try nav switch end
		NavNode navNode = this.navMap.getNavNodeByNavUrl( navUrl );
		this.getLog().info( "navNode : "+navNode );
		if ( navNode!=null ) {
			try {
				if ( !this.authHandler.checkAuthorized( request, navNode.getNavEntry().getAuth() ) ) {
					code = HttpServletResponse.SC_FORBIDDEN;
				}
			} catch (AuthException e) {
				code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			}	
		} else {
			code = HttpServletResponse.SC_NOT_FOUND;
		}
		if ( code == HttpServletResponse.SC_ACCEPTED ) {
			this.getLog().info( "ok, following filter chain : "+navUrl+" : "+navNode );
			try {
				navNode = this.navMap.handleNavNode( navNode, request, response );
			} catch (Exception e) {
				this.getLog().error( "Error : "+e );
				e.printStackTrace();
				code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			}
			request.getSession().setAttribute( ATT_NAME_NAVNODE, navNode );
			// gestione parametri
			SessionContext sessionContext = SessionContext.getHttpSessionContext( request );
			ParamMap paramMap = ParamMap.getParamMap( request );
			// v1
			List paramHandler = (List)sessionContext.getAttribute( ATT_NAV_PARAM_HANDLER , new ArrayList() );
			int depth = navNode.getDepth().intValue();
			while ( paramHandler.size() <= depth ) {
				paramHandler.add( ParamMap.newEmptyMap() );
			}
			paramHandler.set( depth , paramMap );
			// v2
			Map paramNodeMap = (Map)sessionContext.getAttribute( ATT_NAV_PARAM_HANDLER_MAP, new HashMap() );
			paramNodeMap.put( navNode.getNavEntry().getUrl() , paramMap );
			// gestione parametri
			try {
				chain.doFilter( request, response );
			} catch (Throwable e) {
				code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				this.getLog().error( "Error in filter chain : "+e, e );
			}
		} else {
			request.getSession().setAttribute( ATT_NAME_NAVNODE, this.navMap.getDefaultNavNode() );
		}		
		if ( code != HttpServletResponse.SC_ACCEPTED ) {
			this.getLog().info( "sending error "+code );
			try {
				response.sendError( code );
			} catch (Throwable e) {
				this.getLog().warn( "Error sending code : "+e );
			}
						
		}	
	}
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.ServletContextFilter#init(javax.servlet.FilterConfig)
	 */
	public void initController( String validate, String noAccessPage, String authHandlerType, String navMapConfig, ConfigContext context ) throws Exception {
		this.context = context;
		this.getLog().info( "validate-dtd : "+validate );
		boolean validateDTD = !"false".equalsIgnoreCase( validate );
		this.getLog().info( "no_access : "+noAccessPage );
		this.getLog().info( "authHandlerType : "+authHandlerType );
		if ( navMapConfig == null ) {
			navMapConfig = "/WEB-INF/nav-map.xml";
			this.getLog().info( "null config for nav map, using default" );
		}
		this.getLog().info( "navMapConfig : "+navMapConfig );
		File navMapConfigFile = null;
		try {
			navMapConfigFile = this.context.resolvePath( navMapConfig );
		} catch (Exception ioe) {
			throw ( new ServletException( ioe ) );
		}
		try {
			this.getLog().info( "navMapConfigFile path : "+navMapConfigFile.getCanonicalPath() );
			FileInputStream fis =  new FileInputStream( navMapConfigFile );
			InputSource navMapConfigSource = new InputSource( fis );
			this.setNavMap( NavMapFactory.configure( navMapConfigSource, validateDTD ) );
			this.getContext().setAttribute( ATT_NAME_NAVMAP, this.getNavMap() );
			fis.close();
			this.setAuthHandler( (AuthHandler)ClassHelper.newInstance( authHandlerType ) );
			this.getAuthHandler().init();
			this.getContext().setAttribute( AuthHandler.ATT_NAME, this.getAuthHandler() );
		} catch ( Exception e ) {
			this.getLog().fatal( "Erroe generale creazione NavFilter", e );
			throw ( new ServletException( e ) );
		}
		this.getLog().info( "auth handler config [OK]" );
	}

	/**
	 * @return Restituisce il valore di authHandler.
	 */
	public AuthHandler getAuthHandler() {
		return authHandler;
	}

	/**
	 * @param authHandler il valore di authHandler da impostare.
	 */
	public void setAuthHandler(AuthHandler authHandler) {
		this.authHandler = authHandler;
	}

	/**
	 * @return Restituisce il valore di context.
	 */
	public ConfigContext getContext() {
		return context;
	}

	/**
	 * @return Restituisce il valore di navMap.
	 */
	public NavMap getNavMap() {
		return navMap;
	}

	/**
	 * @param navMap il valore di navMap da impostare.
	 */
	public void setNavMap(NavMap navMap) {
		this.navMap = navMap;
	}	
	
}

abstract class NavControllerHandler {
	
	abstract public int handle( HttpServletRequest request, HttpServletResponse response ) throws ServletException;
	
}

class NavControllerHandlerWebApp22 extends NavControllerHandler {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.servlet.NavControllerHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public int handle(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
