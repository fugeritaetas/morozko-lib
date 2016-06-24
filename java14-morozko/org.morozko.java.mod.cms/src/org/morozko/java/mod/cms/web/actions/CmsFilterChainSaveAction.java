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
 * @(#)CmsFilterChainSaveAction.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.actions
 * @creation   : 25/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsFilterChainDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsFilterChainModel;
import org.morozko.java.mod.cms.web.form.CmsFilterChainForm;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.web.actions.AbstractAction;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsFilterChainSaveAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		CmsFilterChainForm cmsFilterChainForm = (CmsFilterChainForm)form;
		CmsFilterChainModel cmsFilterChainModel = cmsFilterChainForm.getModel();
		CmsFilterChainDAO cmsFilterChainDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsFilterChainDAO();
		if ( cmsFilterChainModel.getIdCmsFilterChain().intValue() == 0 ) {
			// insert
			DAOID idCmsFilterChain = CmsDAOFactory.generateId();
			this.getLog().info( "insert, new id generated : "+idCmsFilterChain );
			cmsFilterChainModel.setIdCmsFilterChain( idCmsFilterChain );
			cmsFilterChainDAO.insert( cmsFilterChainModel );
		} else {
			// update
			cmsFilterChainDAO.update( cmsFilterChainModel );
		}
		request.getSession().setAttribute( CmsFilterChainModel.ATT_NAME, cmsFilterChainModel );
		return mapping.findForward( forward );
	}

}
