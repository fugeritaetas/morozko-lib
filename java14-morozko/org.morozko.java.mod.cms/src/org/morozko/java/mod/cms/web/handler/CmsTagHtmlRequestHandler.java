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
 * @(#)CmsTagHtmlRequestHandler.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.handler
 * @creation   : 29/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.handler;

import java.text.MessageFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.mod.cms.web.config.CmsConfig;
import org.morozko.java.mod.text.regex.RegexParser;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsTagHtmlRequestHandler extends BasicCmsRequestHandler {

	private RegexParser parser;
	
	
	
	/**
	 * <p>Creates a new instance of CmsTagHtmlRequestHandler.</p>
	 *
	 */
	public CmsTagHtmlRequestHandler() {
		try {
			this.parser = RegexParser.newInstance( CmsTagHtmlRequestHandler.class.getResourceAsStream( "/org/morozko/java/mod/cms/web/res/taghtml_config.xml" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.web.handler.BasicCmsRequestHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String htmlData = this.loadHtmlData( request );
		htmlData = htmlData.replaceAll( "'" , "''" );
		htmlData = replace( htmlData, "{" , "'{'" );
		htmlData = replace( htmlData, "}" , "'}'" );		
		htmlData = this.parser.handleDoc( htmlData );

		MessageFormat mf = new MessageFormat( htmlData );
		Object[] args = { request.getContextPath(),																	// arg0 - context path
							CmsConfig.getInstance().getGeneralProps().getProperty( CmsConfig.PROP_BASE_MAP_URL ) 	// arg1 - cms servler url mapping
						};
		htmlData = mf.format( args );
		Enumeration e = request.getParameterNames();
		while ( e.hasMoreElements() ) {
			String name = (String) e.nextElement();
			String substituteFrom = "\\[html:req:param\\]"+name+"\\[/html:req:param\\]";
			String substituteTo = request.getParameter( name );
			htmlData = htmlData.replaceAll( substituteFrom , substituteTo );
		}
		this.saveHtmlData(request, htmlData);
	}
	
	public static String replace( String text, String oldToken, String newToken ) {
		StringBuffer result = new StringBuffer();
		int index = text.indexOf( oldToken );
		while ( index != -1 ) {
			result.append( text.substring( 0, index )+newToken );
			text = text.substring( index+oldToken.length() );
			index = text.indexOf( oldToken );
		}
		result.append( text );
		return result.toString();
	}	
	
//	public static void main( String[] args ) {
//		try {
//			RegexParser parser = RegexParser.newInstance( CmsTagHtmlRequestHandler.class.getResourceAsStream( "/org/opinf/jlib/mod/app/cms/web/res/taghtml_config.xml" ) );
//			String test = "ciao [cms:html:url:pageid]434342343[/cms:html:url:pageid] test";
//			System.out.println( "A : "+test );
//			String data = parser.handleDoc( test );
//			System.out.println( "B : "+data );
//			MessageFormat format = new MessageFormat( data );
//			Object[] ar1 = { "/test" };
//			System.out.println( "C : "+format.format( ar1 ) );		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
