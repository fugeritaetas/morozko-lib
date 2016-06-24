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
 * @(#)CmsFilterHandlerForm.java
 *
 * @project    : org.morozko.java.mod.app.cms
 * @package    : org.morozko.java.mod.cms.web.form
 * @creation   : 25/ago/06
 * @license	   : META-INF/LICENSE.TXT
 */
package org.morozko.java.mod.cms.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.morozko.java.mod.cms.dg.core.bean.CmsFilterHandlerBean;

/**
 * <p></p>
 *
 * @author mfranci
 *
 */
public class CmsFilterHandlerForm extends CmsFilterHandlerBean {

	/**
	 * <p>Creates a new instance of CmsFilterHandlerForm.</p>
	 *
	 */
	public CmsFilterHandlerForm() {
		super();
		this.setIdCmsFilterHandler( "0" );
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5570479692471241929L;

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {	
		ActionErrors errors = new ActionErrors();
		return errors;
	}	
	
}
