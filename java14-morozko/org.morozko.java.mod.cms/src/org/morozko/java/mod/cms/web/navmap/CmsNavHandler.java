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
 * @(#)CmsNavHandler.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.navmap
 * @creation   : 8-giu-2006
 */
package org.morozko.java.mod.cms.web.navmap;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.util.MapUtils;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.navmap.NavEntry;
import org.morozko.java.mod.navmap.NavHandler;
import org.morozko.java.mod.navmap.NavHandlerDefault;
import org.morozko.java.mod.navmap.NavMenu;
import org.morozko.java.mod.navmap.NavNode;


/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsNavHandler extends NavHandlerDefault implements NavHandler {

	private static NavEntry render( NavEntry base, CmsPageModel cmsPageModel ) {
		Properties params = new Properties();
		params.setProperty( "idCmsPage", cmsPageModel.getIdCmsPage().toString() );
		NavEntry navEntry = base.copy();
		navEntry.setDirectDisplay( Boolean.TRUE );
		navEntry.setDirectTitle( Boolean.TRUE );
		navEntry.setTitle( cmsPageModel.getTitle() );
		navEntry.setDisplay( cmsPageModel.getDisplay() );
		navEntry.setParameters( params );		
		return navEntry;
	}
	
	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavHandler#getHandlerVersion()
	 */
	public int getHandlerVersion() {
		return CURRENT_HANDLER_VERSION;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavHandler#isCurrent(org.morozko.java.mod.navmap.NavEntry, javax.servlet.http.HttpServletRequest)
	 */
	public boolean isCurrent(NavEntry navEntry, HttpServletRequest request) throws Exception {
		Properties params1 = null;
		CmsPageModel cmsPageModel = (CmsPageModel)request.getAttribute( "cmsPageModel" );
		if ( cmsPageModel != null ) {
			params1 = new Properties();
			params1.setProperty( "idCmsPage", cmsPageModel.getIdCmsPage().toString() );	
		}
		
		Properties params2 = navEntry.getParameters();
		boolean test1 = super.isCurrent( navEntry, request );
		boolean test2 = MapUtils.equalsAll( params1, params2 );
		return test1 && test2;
	}

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.navmap.NavHandler#handleNavNode(org.morozko.java.mod.navmap.NavNode, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public NavNode handleNavNode(NavNode navNode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.getLog().debug( "handleNavNode START" );
		navNode = navNode.copy();
		String idCmsPage = request.getParameter( "idCmsPage" );
		
		DAOID daoid = new DAOID( 1 );
		
		if ( idCmsPage != null ) {
			daoid = DAOID.valueOf( idCmsPage );
		} 
		
		CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
		CmsPageModel cmsPageModel = cmsPageDAO.loadOneCmsPagePk( daoid );
		List kids = cmsPageModel.getListKids();
		NavMenu menu2 = new NavMenu( "menu-2" );
		menu2.add( render( navNode.getNavEntry(), cmsPageModel ) );
		
		Iterator it = kids.iterator();
		while ( it.hasNext() ) {
			CmsPageModel currentKid = (CmsPageModel)it.next();
			menu2.add( render( navNode.getNavEntry(), currentKid ) );
		}
		navNode.setMenu( "menu-2", menu2 );		
		
		this.getLog().debug( "handleNavNode END" );
		return navNode;
	}

}
