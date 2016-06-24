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
 * @(#)BasicCmsRequestHandler.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.handler
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.log.BasicLogObject;
import org.morozko.java.mod.cms.dg.core.model.CmsPageModel;
import org.w3c.dom.Element;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class BasicCmsRequestHandler extends BasicLogObject implements CmsRequestHandler {

	public void saveHtmlData( HttpServletRequest request, String htmlData ) {
		request.setAttribute( CmsPageModel.ATT_NAME_DATA, htmlData );
	}
	
	public String loadHtmlData( HttpServletRequest request ) {
		return (String)request.getAttribute( CmsPageModel.ATT_NAME_DATA );
	}
	
	public void configure(String config) throws Exception {
		
	}

	public void handle( HttpServletRequest request, HttpServletResponse response ) throws Exception {

	}

	/* (non-Javadoc)
	 * @see org.opinf.jlib.ent.servlet.request.RequestHandler#configure(org.w3c.dom.Element)
	 */
	public void configure(Element config) throws Exception {
		
	}

}
