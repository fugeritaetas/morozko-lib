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
 * @(#)ListRequestHandler.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.handler
 * @creation   : 27/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.handler;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.cms.web.config.CmsEnv;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsTagLogicRequestHandler extends BasicCmsRequestHandler {

	public static final CmsRequestHandler DEFAULT = new CmsTagLogicRequestHandler();
	
	private String handleNotEmpty( String htmlData, HttpServletRequest request, HttpServletResponse response ) {
		String p = "(?s)\\[logic:req:notEmpty name='(.*?)'\\](.*?)\\[/logic:req:notEmpty\\]";
		Pattern pattern = Pattern.compile( p );
		Matcher matcher = pattern.matcher( htmlData );
		if ( matcher.find() ) {
			String current = matcher.group();
			String name = current.replaceAll( p, "$1" );
			this.getLog().info( "handleNotEmpty : "+name );
			CmsEnv cmsEnv = CmsEnv.loadFromSession( request );
			Collection c = (Collection)cmsEnv.getRequestCmsMap().get( name );
			System.err.println( "test not empty : "+c );
			if ( c == null || c.isEmpty() ) {
				htmlData = htmlData.replaceAll( p , "" );
			} else {
				htmlData = htmlData.replaceAll( p , "$2" );
			}
		} 
		return htmlData;
	}	
	
	private String handleEmpty( String htmlData, HttpServletRequest request, HttpServletResponse response ) {
		String p = "(?s)\\[logic:req:empty name='(.*?)'\\](.*?)\\[/logic:req:empty\\]";
		this.getLog().info( "handleEmpty : "+p );
		Pattern pattern = Pattern.compile( p );
		Matcher matcher = pattern.matcher( htmlData );
		if ( matcher.find() ) {
			String current = matcher.group();
			String name = current.replaceAll( p, "$1" );
			this.getLog().info( "handleEmpty : "+name );
			CmsEnv cmsEnv = CmsEnv.loadFromSession( request );
			Collection c = (Collection)cmsEnv.getRequestCmsMap().get( name );
			System.err.println( "test empty : "+c );
			if ( c == null || c.isEmpty() ) {
				htmlData = htmlData.replaceAll( p , "$2" );
			} else {
				htmlData = htmlData.replaceAll( p , "" );
			}
		} 
		return htmlData;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.web.handler.BasicCmsRequestHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String htmlData = (String)request.getAttribute( CmsPageModel.ATT_NAME_DATA );
		htmlData = this.handleEmpty(htmlData, request, response);
		htmlData = this.handleNotEmpty(htmlData, request, response);
		request.setAttribute( CmsPageModel.ATT_NAME_DATA , htmlData );
	}

//	public static void main( String[] args ) {
//		try {
//			String htmlData = "inizio [logic:req:empty name='prova']ciao chi sei?[/logic:req:empty] fine";
//
//			
//		} catch ( Exception e ){
//			e.printStackTrace();
//		}
//	}
	
}
