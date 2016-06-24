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
 * @(#)CmsPageForm.java
 *
 * @project    : jlibWeb
 * @package    : org.morozko.java.mod.cms.web.form
 * @creation   : 29-mag-2006
 */
package org.morozko.java.mod.cms.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.bean.CmsPageBean;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.db.dao.DAOException;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsPageForm extends CmsPageBean {

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		System.out.println( "A : "+this.getIdCmsFilterChain() );
		if ( "".equalsIgnoreCase( this.getIdCmsFilterChain() ) ) {
			this.setIdCmsFilterChain( null );
			System.out.println( "B : "+this.getIdCmsFilterChain() );
		}
		System.out.println( "C : "+this.getIdCmsFilterChain() );
		if ( this.getPageUrl() != null && !this.getPageUrl().equals( "" ) && "0".equalsIgnoreCase( this.getIdCmsPage() ) ) {
			CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
			try {
				if ( cmsPageDAO.loadOneCmsPageUn2( this.getPageUrl() ) != null ) {
					errors.add( "errors", new ActionMessage( "morozko.java.web.cms.message.error.form.page.urlExist" ) );	
				}
			} catch (DAOException e) {
				Object[] args = { e.toString() };
				errors.add( "errors", new ActionMessage( "morozko.java.web.cms.message.error.form.generic.dbError", args ) );
			}
		}
		return errors;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4326673464863404369L;

}
