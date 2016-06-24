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
 * @(#)CmsPageFacade.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.facade
 * @creation   : 18/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.facade;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.request.RequestFacade;
import org.morozko.java.core.ent.servlet.request.RequestHandler;
import org.morozko.java.core.ent.servlet.request.RequestHandlerFactory;
import org.morozko.java.core.lang.helpers.ClassHelper;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsFilterChainDAO;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel;
import org.morozko.java.mod.cms.dg.core.model.CmsFilterHandlerModel;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.cms.web.config.CmsConfig;
import org.morozko.java.mod.cms.web.handler.CmsRequestHandler;
import org.morozko.java.mod.db.dao.DAOID;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsPageFacade {

	public static void setup( List list ) {
		Iterator it = list.iterator();
		while ( it.hasNext() ) {
			CmsPageModel cmsPageModel = (CmsPageModel) it.next();
			setup( cmsPageModel );
		}
	}
	
	public static void setup( CmsPageModel cmsPageModel ) {
		//cmsPageModel.setPageUrl( CmsConfig.getInstance().getPageBaseUrl()+cmsPageModel.getPageUrl() );
	}
	
	public static void handle( HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		String idCmsPage = RequestFacade.findParameter( request, "idCmsPage" );
		String pageUrl = RequestFacade.findParameter( request, "pageUrl" );
		CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
		CmsPageModel cmsPageModel = null;
		if ( idCmsPage != null ) {
			cmsPageModel = cmsPageDAO.loadOneCmsPagePk( DAOID.valueOf( idCmsPage ) );
		} else if ( pageUrl != null ) {
			cmsPageModel = cmsPageDAO.loadOneCmsPageUn2( pageUrl );
		} else {
			cmsPageModel = cmsPageDAO.loadOneCmsPagePk( CmsConfig.getInstance().getPageBase() );
		}
		setup( cmsPageModel );
		request.setAttribute( CmsPageModel.ATT_NAME, cmsPageModel );
		request.setAttribute( CmsPageModel.ATT_NAME_DATA, cmsPageModel.getHtmlData() );		
		handle( cmsPageModel, request, response );
		
	}
	
	public static void handle( CmsPageModel cmsPageModel, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		CmsFilterChainModel cmsFilterChainModel = cmsPageModel.getCmsFilterChain();
		LogFacade.getLog().info( "CmsPageFacade:handle:cmsFilterChainModel : "+cmsFilterChainModel );
		if ( cmsFilterChainModel != null ) {
			CmsFilterChainDAO cmsFilterChainDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsFilterChainDAO();
			cmsFilterChainModel = cmsFilterChainDAO.loadOneCmsFilterChainPk( cmsFilterChainModel.getIdCmsFilterChain() );
			Iterator itChain = cmsFilterChainModel.getListCmsFilterHandler().iterator();
			while ( itChain.hasNext() ) {
				CmsFilterHandlerModel cmsFilterHandlerModel = (CmsFilterHandlerModel)itChain.next();
				LogFacade.getLog().info( "CmsPageFacade:handle:cmsFilterHandlerModel : "+cmsFilterHandlerModel );
				CmsRequestHandler cmsRequestHandler = (CmsRequestHandler)ClassHelper.newInstance( cmsFilterHandlerModel.getHandlerType() );
				cmsRequestHandler.configure( cmsFilterHandlerModel.getHandlerConfig() );
				cmsRequestHandler.handle( request , response );
			}
		}
		// processo la catena di default
		RequestHandlerFactory factory = CmsConfig.getInstance().getCmsFilterList();
		Iterator itFilter = factory.listhandlers();
		while ( itFilter.hasNext() ) {
			RequestHandler handler = (RequestHandler)itFilter.next();
			LogFacade.getLog().info( "CmsPageFacade:handle:handler : "+handler );
			handler.handle( request , response );
		}		
	}	
	
}
