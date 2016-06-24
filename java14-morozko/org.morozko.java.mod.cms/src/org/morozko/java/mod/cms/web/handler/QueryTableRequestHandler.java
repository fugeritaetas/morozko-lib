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
 * @(#)QueryTableRequestHandler.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.handler
 * @creation   : 28/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.handler;

import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.xml.dom.DOMIO;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.cms.web.config.CmsEnv;
import org.morozko.java.mod.cms.web.config.StringLegacy;
import org.morozko.java.mod.daogen.helpers.model.BasicModel;
import org.morozko.java.mod.db.dao.query.table.QueryTableModel;
import org.morozko.java.mod.db.dao.query.table.QueryTableXML;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class QueryTableRequestHandler extends BasicCmsRequestHandler {

	private static final BasicModel FORMAT = new BasicModel();
	
	private final static String TAG_TABLE_COL_OPEN = "[table:column]";
	private final static String TAG_TABLE_COL_CLOSE = "[/table:column]";
	
	private String config;
	
	private String resultAttName;
	
	private String resultAttScope;
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.web.handler.BasicCmsRequestHandler#configure(java.lang.String)
	 */
	public void configure(String config) throws Exception {
		this.config = config;
		Element conf = DOMIO.loadDOMDoc( new ByteArrayInputStream( config.getBytes() ) ).getDocumentElement();
		Properties props = DOMUtils.attributesToProperties( conf );
		this.resultAttName = props.getProperty( "att-result-name" );
		this.resultAttScope = props.getProperty( "att-result-scope" );
		this.getLog().info( "resultAttName : "+this.resultAttName );
		this.getLog().info( "resultAttScope : "+this.resultAttScope );
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.web.handler.BasicCmsRequestHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Enumeration e = request.getParameterNames();
		String currentConf = config;
		while ( e.hasMoreElements() ) {
			String k = (String)e.nextElement();
			String v = request.getParameter( k );
			currentConf = currentConf.replaceAll( "\\[cms:req:param:"+k+"\\]" , v );
			this.getLog().info( "currentConf : "+k+" = "+v );
		}
		this.getLog().info( "new config : '"+currentConf+"'" );
		QueryTableXML queryTableXML = new QueryTableXML();
		queryTableXML.configure( DOMIO.loadDOMDoc( new ByteArrayInputStream( currentConf.getBytes() ) ).getDocumentElement() );
		List result = queryTableXML.extract();
		this.getLog().info( "result : "+result.size() );
		CmsEnv cmsEnv = CmsEnv.loadFromSession( request );
		if ( "session".equalsIgnoreCase( this.resultAttScope ) ) {
			cmsEnv.getSessionCmsMap().put( this.resultAttName , result );
		} else {
			cmsEnv.getRequestCmsMap().put( this.resultAttName , result );
		}

		CmsTagLogicRequestHandler.DEFAULT.handle( request , response );
		
		String htmlData = (String)request.getAttribute( CmsPageModel.ATT_NAME_DATA );
		request.setAttribute( CmsPageModel.ATT_NAME_DATA, handleData( htmlData , result ) );
	}
	
	private String handleData( String htmlData, List list ) {
		String result = htmlData;
		//htmlData = htmlData.replaceAll( "'" , "''");
		this.getLog().debug( "htmlData a : "+htmlData );
		String pre = "[table:iterate name='"+this.resultAttName+"']";
		String post = "[/table:iterate]";
		int preIndex = htmlData.indexOf( pre );
		int postIndex = htmlData.indexOf( post );
		this.getLog().info( "preindex : "+preIndex+" : "+pre );
		this.getLog().info( "postindex : "+postIndex );
		if ( preIndex != -1 && postIndex != -1 ) {
			String substituteFrom = result.substring( preIndex+pre.length() , postIndex );
			StringBuffer substituteTo = new StringBuffer();
			Iterator it = list.iterator();
			while ( it.hasNext() ) {
				QueryTableModel queryTableModel = (QueryTableModel)it.next();
				String temp = substituteFrom;
				int index1 = temp.indexOf( TAG_TABLE_COL_OPEN );
				this.getLog().info( "test : "+index1 );
				while ( index1 != -1 ) {
					int index2 = temp.indexOf( TAG_TABLE_COL_CLOSE );
					String column = temp.substring( index1+TAG_TABLE_COL_OPEN.length(), index2 );
					String value = "<span style='color: red;'>Variabile non definita:"+column+"</span>";
					try {
						value = FORMAT.formatObject( queryTableModel.getQueryColumn( column ).getValue() );
					} catch (Exception e) {
						this.getLog().warn( "Variabile non definita : "+column );
					}
					this.getLog().debug( "col : "+column+" value : "+value );
					temp = StringLegacy.replace( temp, TAG_TABLE_COL_OPEN+column+TAG_TABLE_COL_CLOSE, value );
					index1 = temp.indexOf( TAG_TABLE_COL_OPEN );
				}
				substituteTo.append( temp );
			}
			htmlData = StringLegacy.replace( htmlData, pre+substituteFrom+post , substituteTo.toString() );
		}
		preIndex = htmlData.indexOf( pre );
		postIndex = htmlData.indexOf( post );
		if ( preIndex != -1 && postIndex != -1 ) {
			htmlData = handleData( htmlData , list );
		}
		this.getLog().debug( "htmlData b : "+htmlData );
		return htmlData;
	}

}
