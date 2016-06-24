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
 * @(#)ParameterInCmsMapSession.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.handler
 * @creation   : 23/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.morozko.java.core.ent.servlet.util.ServletMapFacade;
import org.morozko.java.mod.cms.web.config.CmsMap;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class ParameterInCmsMapSession extends BasicCmsRequestHandler {

	/* (non-Javadoc)
	 * @see org.morozko.java.mod.cms.web.handler.BasicCmsRequestHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CmsMap cmsMap = CmsMap.loadFromSession( request );
		ServletMapFacade.putParameters( request, cmsMap );
	}

}
