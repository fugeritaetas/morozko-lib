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
 * @(#)PageTag.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.tld
 * @creation   : 09/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.tld;

import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagSupportHelper;
import org.morozko.java.core.log.LogFacade;
import org.morozko.java.mod.cms.dg.CmsDAOFactory;
import org.morozko.java.mod.cms.dg.core.dao.CmsPageDAO;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.morozko.java.mod.db.dao.DAOID;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsPageTag extends TagSupportHelper {

	private String idCmsPage;
	
	private String pageUrl;	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9200407364010562143L;

	/**
	 * @return the idCmsPage
	 */
	public String getIdCmsPage() {
		return idCmsPage;
	}

	/**
	 * @param idCmsPage the idCmsPage to set
	 */
	public void setIdCmsPage(String idCmsPage) {
		this.idCmsPage = idCmsPage;
	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * @param pageUrl the pageUrl to set
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
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
			this.print( cmsPageModel.getHtmlData() );
		} catch (Throwable me) {
			LogFacade.getLog().warn( "CmsPageTag : No page data found, pageUrl="+this.getPageUrl()+" , idCmsPage="+this.getIdCmsPage() );
		}		
		return EVAL_PAGE;
	}	
	
}
