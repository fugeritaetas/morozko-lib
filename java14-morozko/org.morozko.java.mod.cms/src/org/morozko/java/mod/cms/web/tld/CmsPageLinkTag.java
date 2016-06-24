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
 * @(#)CmsPageUrlTag.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.tld
 * @creation   : 08/set/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.tld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.morozko.java.core.ent.tld.helpers.TagLinkHelper;
import org.morozko.java.mod.cms.web.config.CmsConfig;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsPageLinkTag extends TagLinkHelper {

	private String pageUrl;

	
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
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		this.print( "</a>" );
		return EVAL_BODY_INCLUDE;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String tag = "<a"+this.renderLinkHtml();
		tag+= renderAttribute( "href" , ((HttpServletRequest)this.pageContext.getRequest()).getContextPath()+CmsConfig.getInstance().getGeneralProps().getProperty( CmsConfig.PROP_BASE_MAP_URL )+"/page"+this.getPageUrl() );
		tag+=">";
		this.print( tag );
		return EVAL_PAGE;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
