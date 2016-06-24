/*****************************************************************
<copyright>
	Morozko Java Library 

	Copyright (c) 2007 Morozko

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
 * @(#)CmsPageCheckTag.java
 *
 * @project     : org.morozko.java.mod.cms
 * @package     : org.morozko.java.mod.cms.web.tld
 * @creation	: 05/nov/07
 * @release		: xxxx.xx.xx
 */
package org.morozko.java.mod.cms.web.tld;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagUtilsHelper;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.db.dao.DAOID;

/**
 * <p>/p>
 *
 * @author Morozko
 *
 */
public class CmsPageCheckTag extends CmsPageTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3162432495507363214L;

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.web.tld.CmsPageTag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			CmsPageModel cmsPageModel = null;
			CmsPageDAO cmsPageDAO = CmsDAOFactory.getInstance().getCmsCoreDAOFactory().getCmsPageDAO();
			if ( this.getIdCmsPage() != null && this.getPageUrl() != null ) {
				throw ( new JspException( "Non puoi specificare entrambi gli attributi idCmsPage e pageUrl" ) );
			} else if ( this.getIdCmsPage() != null && this.getPageUrl() != null ) {
				throw ( new JspException( "Devi specificare uno degli attributi idCmsPage e pageUrl" ) );
			} else {
				if ( this.getPageUrl() != null ) {
					try {
						cmsPageModel = cmsPageDAO.loadOneCmsPageUn2( this.getPageUrl() );
					} catch (Exception e) {
						e.printStackTrace();
						throw ( new JspException( e ) );
					}
				} else if ( this.getIdCmsPage() != null ) {
					try {
						cmsPageModel = cmsPageDAO.loadOneCmsPagePk( DAOID.valueOf( this.getIdCmsPage() ) );
					} catch (Exception e) {
						e.printStackTrace();
						throw ( new JspException( e ) );
					}
				}
			}
			if ( cmsPageModel == null ) {
				TagUtilsHelper.setAttibute( this.pageContext , this.getToScope(), this.getId(), "false" );
			} else {
				TagUtilsHelper.setAttibute( this.pageContext , this.getToScope(), this.getId(), "true" );
			}
		} catch (Throwable me) {
			LogFacade.getLog().warn( "CmsPageCheckTag : No page data found, pageUrl="+this.getPageUrl()+" , idCmsPage="+this.getIdCmsPage() );
		}		
		return EVAL_PAGE;
	}
	
	

}
