/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)WebAccessDataFilter.java
 *
 * @project     : org.morozko.java.mod.web
 * @package     : org.morozko.java.mod.web.filter
 * @creation	: 03/ott/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.web.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.morozko.java.core.ent.servlet.filter.HttpFilter;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.web.dg.WebDAOFactory;
import org.morozko.java.mod.web.dg.core.dao.WebAccessDataDAO;
import org.morozko.java.mod.web.dg.core.model.WebAccessDataModel;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class WebAccessDataFilter extends HttpFilter {


	public static final int SPEC_LEVEL_MIN = 1;
	
	public static final int SPEC_LEVEL_AVG = 2;
	
	public static final int SPEC_LEVEL_MAX = 3;
	
	private int specLevel;
	
	private boolean printTrace;
	
	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#destroy()
	 */
	public void destroy() {
		this.getLog().info( "DESTROY : OK" );
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws IOException, ServletException {
		WebAccessDataDAO webAccessDataDAO = WebDAOFactory.getInstance().getWebCoreDAOFactory().getWebAccessDataDAO();
		WebAccessDataModel webAccessDataModel = new WebAccessDataModel();
		HttpServletResponseSC responseWrapper = new HttpServletResponseSC( response );
		long startTime = System.currentTimeMillis();
		try {
			chain.doFilter( request, responseWrapper );	
		} catch (Throwable e) {
			throw ( new ServletException( e ) );
		} finally {
			long endTime = System.currentTimeMillis();
			try {
				webAccessDataModel.setIdWebAccessData( webAccessDataDAO.generateId() );
				webAccessDataModel.setRequestUri( request.getRequestURI() );
				webAccessDataModel.setRequestUrl( request.getRequestURL().toString() );
				webAccessDataModel.setSessionId( request.getRequestedSessionId() );
				webAccessDataModel.setRequestMethod( request.getMethod() );
				webAccessDataModel.setRequestQueryString( request.getQueryString() );
				webAccessDataModel.setRequestContextPath( request.getContextPath() );
				webAccessDataModel.setRequestRemoteAddr( request.getRemoteAddr() );
				webAccessDataModel.setRequestRemoteHost( request.getRemoteHost() );
				webAccessDataModel.setRequestRemoteUser( request.getRemoteUser() );
				webAccessDataModel.setRequestServerName( request.getServerName() );
				webAccessDataModel.setRequestServerPort( new Integer( request.getServerPort() ) );
				webAccessDataModel.setRequestScheme( request.getScheme() );
				webAccessDataModel.setResponseStatusCode( new Integer( responseWrapper.getStatusCode() ) );
				webAccessDataModel.setApplicationHost( InetAddress.getLocalHost().getHostAddress() );
				webAccessDataModel.setRequestStart( new Timestamp( startTime ) );
				webAccessDataModel.setRequestEnd( new Timestamp( endTime ) );
				// average level access data
				if ( this.specLevel > SPEC_LEVEL_AVG ) {
					webAccessDataModel.setRequestRemotePort( new Integer( request.getRemotePort() ) );
				}
				webAccessDataDAO.insert( webAccessDataModel );
				this.getLog().info( webAccessDataModel );
			} catch (Throwable e) {
				if ( this.printTrace ) {
					this.getLog().warn( "failed to register web access data", e );
				} else {
					this.getLog().warn( "failed to register web access data" );
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.filter.HttpFilter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.getLog().info( "INIT : START" );
		String dsName = config.getInitParameter( "ds_name" );
		String specLevel = config.getInitParameter( "spec_level" );
		if ( "average".equalsIgnoreCase( specLevel ) ) {
			this.specLevel = SPEC_LEVEL_AVG;
		} else if ( "maximum".equalsIgnoreCase( specLevel ) ) {
			this.specLevel = SPEC_LEVEL_MAX;
		} else {
			this.specLevel = SPEC_LEVEL_MIN;
		}
		this.getLog().info( "INIT : ds_name : "+dsName );
		this.getLog().info( "INIT : spec_level : "+specLevel+" : "+this.specLevel );
		if ( dsName == null ) {
			String message = "init parameter ds_name must not be null";
			this.getLog().error( "INIT : "+message );
			throw ( new ServletException( message ) );
		} else {
			try {
				ConnectionFactory cf = ConnectionFactoryImpl.newInstance( dsName );
				WebDAOFactory.init( cf );
				this.getLog().info( "INIT : OK" );
			} catch (Exception e) {
				this.getLog().error( "INIT : cannot initialize dao factory" );
				throw ( new ServletException( e ) );
			}
		}
		this.printTrace = "true".equalsIgnoreCase( config.getInitParameter( "print_trace" ) );
		this.getLog().info( "printTrace : "+this.printTrace );
		this.getLog().info( "INIT : END" );
	}


}

class HttpServletResponseSC extends HttpServletResponseWrapper {

	private int statusCode;
	
	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Restituisce il valore del campo statusCode.</jdl:text>
	 * 		<jdl:text lang='en'>Returns the value of statusCode.</jdl:text>  
	 *  </jdl:section>
	 * </p>
	 *
	 * @return <jdl:section>
	 *         		<jdl:text lang='it'>il valore del campo statusCode.</jdl:text>
	 *         		<jdl:text lang='en'>the value of statusCode.</jdl:text> 
	 * 		   </jdl:section>
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * <p>
	 *  <jdl:section>
	 * 		<jdl:text lang='it'>Imposta il campo statusCode.</jdl:text>
	 * 		<jdl:text lang='en'>Sets statusCode.</jdl:text>  
	 *	</jdl:section>
	 * </p>
	 *
	 * @param 	<jdl:section>
	 * 				<jdl:text lang='it'>statusCode il valore di statusCode da impostare.</jdl:text>
	 * 				<jdl:text lang='en'>statusCode the statusCode to set.</jdl:text>
	 * 			</jdl:section>
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * <jdl:section>
	 * 		<jdl:text lang='it'><p>Crea una nuova istanza di HttpServletResponseSC.</p></jdl:text>
	 * 		<jdl:text lang='en'><p>Creates a new instance of HttpServletResponseSC.</p></jdl:text>
	 * </jdl:section>
	 *
	 * @param arg0
	 */
	public HttpServletResponseSC(HttpServletResponse response) {
		super( response );
		this.statusCode = HttpServletResponse.SC_OK;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponseWrapper#sendError(int, java.lang.String)
	 */
	public void sendError(int arg0, String arg1) throws IOException {
		this.setStatusCode( arg0 );
		super.sendError(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponseWrapper#sendError(int)
	 */
	public void sendError(int arg0) throws IOException {
		this.setStatusCode( arg0 );
		super.sendError(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponseWrapper#setStatus(int, java.lang.String)
	 */
	public void setStatus(int arg0, String arg1) {
		this.setStatusCode( arg0 );
		super.setStatus(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponseWrapper#setStatus(int)
	 */
	public void setStatus(int arg0) {
		this.setStatusCode( arg0 );
		super.setStatus(arg0);
	}
	
}