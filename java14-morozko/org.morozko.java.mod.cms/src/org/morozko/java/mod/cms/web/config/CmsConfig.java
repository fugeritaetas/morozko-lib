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
 * @(#)CmsConfig.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.config
 * @creation   : 11/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.config;

import java.util.Properties;

import org.morozko.java.core.ent.servlet.request.RequestHandlerFactory;
import org.morozko.java.core.jvfs.JVFS;
import org.morozko.java.core.jvfs.JVFSImpl;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.core.xml.dom.DOMUtils;
import org.morozko.java.core.xml.dom.SearchDOM;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.db.connect.ConnectionFacade;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.connect.ConnectionFactoryImpl;
import org.morozko.java.mod.db.dao.DAOID;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsConfig {
	
	private static CmsConfig instance;
	
	public static CmsConfig getInstance() {
		return instance;
	}
	
	public static final String PROP_BASE_MAP_URL = "base-map-url";
	
	public static final String CF_CMS_NAME = "org.morozko.java.mod.cms.web.config.CmsConfig#CF_CMS_NAME";
	
	public static CmsConfig parse( Document doc ) throws Exception {
		
		LogFacade.getLog().info( "CmsConfig.parse() START" );
		
		CmsConfig cmsConfig = new CmsConfig();
		
		SearchDOM searchDOM = SearchDOM.newInstance( true, true );
		
		LogFacade.getLog().info( "CmsConfig.parse() reading document" );
		Element rootTag = doc.getDocumentElement();
		LogFacade.getLog().info( "CmsConfig.parse() DONE" );
		
		LogFacade.getLog().info( "CmsConfig.parse() setting general props" );
		Element generalProps = searchDOM.findTag( rootTag, "general-props" );
		cmsConfig.generalProps = DOMUtils.attributesToProperties( generalProps );
		LogFacade.getLog().info( "CmsConfig.parse() DONE" );
		
		LogFacade.getLog().info( "CmsConfig.parse() setting page base" );
		cmsConfig.pageBase = DAOID.valueOf( cmsConfig.generalProps.getProperty( "page-base", "1" ) );
		cmsConfig.pageBaseUrl = cmsConfig.generalProps.getProperty( "page-base-url", "" );
		LogFacade.getLog().info( "CmsConfig.parse() setting page base" );
		
		LogFacade.getLog().info( "CmsConfig.parse() setting connection factory" );
		Element cfConfig = searchDOM.findTag( rootTag, "cf-config" );
		cmsConfig.connectionFactory = ConnectionFactoryImpl.newInstance( DOMUtils.attributesToProperties( cfConfig ) );
		ConnectionFacade.registerFactory(  CF_CMS_NAME, cmsConfig.connectionFactory );
		LogFacade.getLog().info( "CmsConfig.parse() DONE" );
		
		
		
		LogFacade.getLog().info( "CmsConfig.parse() initizlizing dao factory" );
		CmsDAOFactory.init( cmsConfig.connectionFactory );
		LogFacade.getLog().info( "CmsConfig.parse() DONE" );
		
		LogFacade.getLog().info( "CmsConfig.parse() setting virtual file system" );
		Element jvfsConfig = searchDOM.findTag( rootTag, "jvfs-config" );
		cmsConfig.jvfs = JVFSImpl.getInstance( jvfsConfig );
		LogFacade.getLog().info( "CmsConfig.parse() DONE" );
		
		LogFacade.getLog().info( "CmsConfig.parse() parse cms-filter-list" );
		Element cmsFilterList = searchDOM.findTag( rootTag , "cms-filter-list" );
		RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();
		requestHandlerFactory.addAll( cmsFilterList );
		cmsConfig.cmsFilterList = requestHandlerFactory;
		LogFacade.getLog().info( "CmsConfig.parse() DONE" );
		
		LogFacade.getLog().info( "CmsConfig.parse() END" );
		
		instance = cmsConfig;
		
		return cmsConfig;
	}	
	
	private JVFS jvfs;
	
	private ConnectionFactory connectionFactory;
	
	private Properties generalProps;
	
	private RequestHandlerFactory cmsFilterList;
	
	private DAOID pageBase;
	
	private String pageBaseUrl;
	
	/**
	 * @return the pageBaseUrl
	 */
	public String getPageBaseUrl() {
		return pageBaseUrl;
	}

	/**
	 * @return the generalProps
	 */
	public Properties getGeneralProps() {
		return generalProps;
	}

	/**
	 * @return the jvfs
	 */
	public JVFS getJvfs() {
		return jvfs;
	}

	/**
	 * @return the connectionFactory
	 */
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * @return the cmsFilterList
	 */
	public RequestHandlerFactory getCmsFilterList() {
		return cmsFilterList;
	}

	/**
	 * @return the pageBase
	 */
	public DAOID getPageBase() {
		return pageBase;
	}
	
}
