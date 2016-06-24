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
 * @(#)NavFilter.java
 *
 * @project	   : org.morozko.java.mod.navmap
 * @package	   : org.morozko.java.mod.navmap.servlet
 * @creation   : 12-lug-2005 8.35.12
 */
package org.morozko.java.mod.navmap.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.filter.ServletContextFilter;
import org.morozko.java.mod.navmap.NavMap;
import org.morozko.java.mod.navmap.NavNode;
import org.morozko.java.mod.web.servlet.config.ConfigContext;

public class NavFilter extends ServletContextFilter {

	public static final String ATT_NAME_NAVNODE = NavController.ATT_NAME_NAVNODE;
	
	public static final String ATT_NAME_NAVMAP = NavController.ATT_NAME_NAVMAP;
	
	public static final String ATT_NAME_NAVPATH = NavController.ATT_NAME_NAVPATH;

	public static final String ATT_NAME_NAVCONTROLLER = NavController.ATT_NAME_NAVCONTROLLER;
	
	private NavController navController;
	
	private String excludePath;
	
	public static void changeCurrentNaveNode( ServletContext context, HttpServletRequest request, String path ) {
		NavMap navMap = (NavMap)context.getAttribute( ATT_NAME_NAVMAP );
		NavNode navNode = navMap.getNavNode( path );
		request.setAttribute( ATT_NAME_NAVNODE , navNode );
	}
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String requestUrl = request.getRequestURI();
		String actionUrl = requestUrl.substring( requestUrl.indexOf( request.getContextPath() ) + request.getContextPath().length() )+";";
		this.getLog().info( "actionUrl:'"+actionUrl+"' vs exclude:'"+this.excludePath+"'" );
		if ( this.excludePath != null && this.excludePath.indexOf( actionUrl ) != -1 ) {
			chain.doFilter( request, response );
		} else {
			if ( this.navController == null ) {
				this.navController = (NavController)this.getContext().getAttribute( ATT_NAME_NAVCONTROLLER );
			}
			this.navController.handle( request, response, chain );
		}
		
		
	}

	/* (non-Javadoc)8
	 * @see org.opinf.jlib.ent.servlet.filter.ServletContextFilter#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.ServletContextFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		String skipInit = config.getInitParameter( "skip-init" );
		String excludePath = config.getInitParameter( "exclude_path" );
		this.excludePath = excludePath;
		this.logInit( "NavFilter config excludePath : "+this.excludePath );
		if ( "true".equalsIgnoreCase(skipInit) ) {
			this.logInit( "navController init skip "+skipInit );
		} else {
			String validate = config.getInitParameter( "validate-dtd" );
			String noAccessPage = config.getInitParameter( "no_access" );
			String authHandlerType = config.getInitParameter( "auth_handler" );
			String navMapConfig = config.getInitParameter( "nav_map" );
			try {
				this.navController = new NavController();
				this.navController.initController( validate, noAccessPage, authHandlerType, navMapConfig, new ConfigContext( config.getServletContext() ) );
				this.logInit( "navController config [OK]" );	
				config.getServletContext().setAttribute( NavController.ATT_NAME_NAVCONTROLLER, this.navController );
			} catch ( Exception e ) {
				this.logInit( "navController config [KO]" );
				this.getLog().fatal( "Errore generale creazione NavFilter", e );
				throw ( new ServletException( e ) );
			}			
		}
	}

}
