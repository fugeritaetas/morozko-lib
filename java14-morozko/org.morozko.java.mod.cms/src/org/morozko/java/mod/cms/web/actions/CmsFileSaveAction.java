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
 * @(#)CmsFileSaveAction.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.actions
 * @creation   : 10/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.core.jvfs.JFile;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsFileDescriptionDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsFileDescriptionModel;
import org.morozko.java.mod.cms.web.config.CmsConfig;
import org.morozko.java.mod.cms.web.form.CmsFileForm;
import org.morozko.java.mod.web.actions.AbstractAction;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsFileSaveAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.web.actions.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = STANDARD_FORWARD_SUCCESS;
		CmsFileDescriptionDAO cmsFileDescriptionDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsFileDescriptionDAO();
		CmsFileForm cmsFileForm = (CmsFileForm)form;
		CmsFileDescriptionModel cmsFileDescriptionModel = cmsFileForm.getModel();
		if ( cmsFileDescriptionModel.getIdCmsFile().longValue() == 0 ) {
			// insert
			// file description
			cmsFileDescriptionModel.setIdCmsFile( CmsDAOFactory.generateId() );
			cmsFileDescriptionDAO.insert( cmsFileDescriptionModel );
			// file data
			JFile file = CmsConfig.getInstance().getJvfs().getJFile( cmsFileDescriptionModel.getFilePath() );
			StreamIO.pipeStream( cmsFileForm.getFileData().getInputStream() , file.getOutputStream(), StreamIO.MODE_CLOSE_BOTH  );
		} else {
			// update
			cmsFileDescriptionDAO.update( cmsFileDescriptionModel );
			// file data
			if ( cmsFileForm.getFileData() != null && cmsFileForm.getFileData().getFileName() != null && !cmsFileForm.getFileData().getFileName().equals( "" ) ) {
				JFile file = CmsConfig.getInstance().getJvfs().getJFile( cmsFileDescriptionModel.getFilePath() );
				StreamIO.pipeStream( cmsFileForm.getFileData().getInputStream() , file.getOutputStream(), StreamIO.MODE_CLOSE_BOTH  );				
			}
		}
		return mapping.findForward( forward );
	}

}
