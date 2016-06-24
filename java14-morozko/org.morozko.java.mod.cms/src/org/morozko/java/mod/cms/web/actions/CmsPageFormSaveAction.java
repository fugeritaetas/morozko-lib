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
 * @(#)CmsPageFormSaveAction.java
 *
 * @project    : jlibWeb
 * @package    : org.morozko.java.mod.cms.web.actions
 * @creation   : 29-mag-2006
 */
package org.morozko.java.mod.cms.web.actions;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.cms.web.form.CmsPageForm;
import org.morozko.java.mod.web.actions.AbstractAction;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsPageFormSaveAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		CmsPageForm cmsPageForm = (CmsPageForm)form;
		CmsPageModel cmsPageModel = cmsPageForm.getModel();
		this.getLog().info( "cmsPageModel : "+cmsPageModel );
		cmsPageModel.setSaveDate( new Timestamp( System.currentTimeMillis() ) );
		CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
		if ( cmsPageModel.getIdCmsPage().longValue() == 0 ) {
			cmsPageModel.setIdCmsPage( CmsDAOFactory.generateId() );
			cmsPageModel.checkPageUrl();
			cmsPageModel.setCmsPageCode( cmsPageModel.getIdCmsPage() );
			cmsPageDAO.insert( cmsPageModel);
			cmsPageDAO.saveData( cmsPageModel );
			request.setAttribute( "cmsPageModel", cmsPageDAO.loadOneCmsPagePk( cmsPageModel.getIdCmsPage() ) );
		} else {
			cmsPageModel.checkPageUrl();
			cmsPageDAO.update( cmsPageModel );
			cmsPageDAO.saveData( cmsPageModel );
			request.getSession().setAttribute( "cmsPageModel", cmsPageDAO.loadOneCmsPagePk( cmsPageModel.getIdCmsPage() ) );
		}
		return mapping.findForward( forward );
	}

}
