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
 * @(#)CmsPageExplorerAction.java
 *
 * @project    : jlibWeb
 * @package    : org.morozko.java.mod.cms.web.actions
 * @creation   : 29-mag-2006
 */
package org.morozko.java.mod.cms.web.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.web.config.CmsConfig;
import org.morozko.java.mod.cms.web.facade.CmsPageFacade;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.web.actions.AbstractAction;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsPageExplorerAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		String idCmsPage = request.getParameter( "idCmsPage" );
		CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
		DAOID idCmsPageParent = CmsConfig.getInstance().getPageBase();
		if ( idCmsPage != null ) {
			idCmsPageParent = DAOID.valueOf( idCmsPage );
		}
		List listPages = cmsPageDAO.loadAllByParent( idCmsPageParent );
		CmsPageFacade.setup( listPages );
		request.setAttribute( "listPages", listPages );
		request.setAttribute( "cmsPageModel" , cmsPageDAO.loadOneCmsPagePk( idCmsPageParent ) );
		return mapping.findForward( forward );
	}

}
